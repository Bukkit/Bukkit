package org.bukkit.persistence.dao;

import java.util.Date;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;
import org.bukkit.util.BlockVector;


/**
 * Encapsulate a player in a persitable class.
 * 
 * You can use this class in your own data objects to reference a player, instead of using playerName.
 * 
 * The player name is used an id, so it is still what will ultimately get persisted to your data table.
 * 
 * @author NathanWolf
 *
 */
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
		
		Location location = loggedIn.getLocation();
		update(location);
		
		update(loggedIn);
	}
	
	public void update(Location location)
	{
		position = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockX());
		orientation = new Orientation(location);
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
		update(player, false);
	}
	
	public void login(Player player, boolean allowSU)
	{
		lastLogin = new Date();
		update(player, allowSU);
	}
	
	public void update(Player player, boolean allowSU)
	{		
		update(player.getLocation());
		name = player.getDisplayName();
		online = player.isOnline();
		
		if (!allowSU)
		{
			superUser = false;
		}		
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
	public void setPosition(BlockVector position)
	{
		this.position = position;
	}

	public BlockVector getPosition()
	{
		return position;
	}

	@PersistField(contained=true)
	public void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}

	public Orientation getOrientation()
	{
		return orientation;
	}

	private String 		name;
	private String 		id;
	private boolean 	superUser;
	private Date		firstLogin;
	private Date		lastLogin;
	private	Date		lastDisconnect;
	private boolean		online;
	private BlockVector	position;
	private Orientation orientation;

}
