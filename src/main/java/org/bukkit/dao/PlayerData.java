package org.bukkit.dao;

import java.util.Date;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.persistence.annotation.Migrate;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;


/**
 * Encapsulate a player in a persitable class.
 * 
 * You can use this class in your own data objects to reference a player, instead of using playerName.
 * 
 * The player name is used an id, so it is still what will ultimately get persisted to your data table.
 * 
 * TOOD: common base class between this in PlayerGroup, once i know that won't 
 * break persistence of these objects.
 * 
 * @author NathanWolf
 *
 */
@Migrate(autoReset = true)
@PersistClass(name = "player", schema = "global") 
public class PlayerData
{
	/**
	 * The default constructor, used by Persistence to create new instances.
	 */
	public PlayerData()
	{
	}
	
	/**
	 * Create a new instance based on a logged in Player.
	 * 
	 * Sets the first login time to now, and sets the id from the player name.
	 * 
	 * If the player is an Op, "superUser" is set to true by default, though they will have the
	 * ability to turn this on and off.
	 * 
	 * @param loggedIn the player this data will represent
	 */
	public PlayerData(Player loggedIn)
	{
		id = loggedIn.getName();
		firstLogin = new Date();
		lastDisconnect = null;
		
		// This will eventually be a setting that ops can toggle on and off
		// It is on by default for ops, but will not turn back on automatically
		// if disabled. This allows ops to play "mostly" as a normal user.
		superUser = loggedIn.isOp();
		update(loggedIn);
	}
	
	public void update(Location location)
	{
		this.location = new LocationData(location);
	}
	
	/**
	 * Update data based on a logged-in player.
	 * 
	 * Will update online status, display name, and last login time.
	 * 
	 * @param player The player to use when updating this data.
	 */

	public void update(Player player)
	{		
		this.player = player;
		update(player.getLocation());
		name = player.getDisplayName();
		online = player.isOnline();
		
		if (!player.isOp())
		{
			superUser = false;
		}		
	}
	
	public void login(Player player)
	{
		lastLogin = new Date();
		update(player);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Update this data based on a player disconnecting.
	 * 
	 * Will update online status and last disconnect time.
	 * 
	 * @param player The player that logged out.
	 */
	public void disconnect(Player player)
	{
		online = false;
		lastDisconnect = new Date();
	}

	public boolean isSet(String key)
	{
		if (profile != null)
		{
			return profile.isSet(key);
		}
		return true;
	}
	
	@PersistField(id=true)
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	@PersistField
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@PersistField
	public void setSuperUser(boolean su)
	{
		this.superUser = su;
	}
	
	public boolean isSuperUser()
	{
		return superUser;
	}
		
	@PersistField
	public Date getFirstLogin()
	{
		return firstLogin;
	}

	public void setFirstLogin(Date firstLogin)
	{
		this.firstLogin = firstLogin;
	}

	@PersistField
	public Date getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	@PersistField
	public Date getLastDisconnect()
	{
		return lastDisconnect;
	}

	public void setLastDisconnect(Date lastDisconnect)
	{
		this.lastDisconnect = lastDisconnect;
	}

	@PersistField
	public boolean isOnline()
	{
		return online;
	}

	public void setOnline(boolean online)
	{
		this.online = online;
	}

	@PersistField(contained=true)
	public void setLocation(LocationData location)
	{
		this.location = location;
	}

	public LocationData getLocation()
	{
		return location;
	}
	
	public IProfile getProfile()
	{
		return profile;
	}

	public void setProfile(IProfile profile)
	{
		this.profile = profile;
	}

	private String				name;
	private String				id;
	private boolean				superUser;
	private Date				firstLogin;
	private Date				lastLogin;
	private Date				lastDisconnect;
	private boolean				online;
	private LocationData		location;
	
	// Transient - will be set up by the groups manager
	private IProfile				profile;
	private Player					player;
}
