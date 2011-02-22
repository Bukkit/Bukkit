package org.bukkit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;


/**
 * 
 * Represents a group of players
 * 
 * * TOOD: common base class between this in PlayerGroup, once i know that won't 
 * break persistence of these objects.
 * 
 * @author NathanWolf
 *
 */
@PersistClass(schema="global", name="group")
public class Group 
{
	public Group()
	{
		
	}
	
	public Group(String id)
	{
		this.id = id;
	}
	
	public boolean isSet(String key)
	{
		// Check for deny first
		for (ProfileData profile : deny)
		{
			if (profile.isSet(key))
			{
				return false;
			}
		}
		
		for (ProfileData profile : grant)
		{
			if (profile.isSet(key))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public <T> T get(final String key)
	{
		/*
		 * TODO : implement this
		 * 
		T result = null;
		
		// Check for deny first
		// Any kind of data returned by a deny profile
		// will get returned as null.
		for (ProfileData profile : deny)
		{
			
		}
		*/
		
		return null;
	}
	

	public void grantPermission(ProfileData profile)
	{
		if (grantMap == null)
		{
			grantMap = new HashMap<String, ProfileData>();
		}
		if (grant == null)
		{
			grant = new ArrayList<ProfileData>();
		}
		
		if (grantMap.get(profile.getId()) == null)
		{
			grantMap.put(profile.getId(), profile);
			grant.add(profile);
		}
		
		// Now, make sure to remove from the deny map also
		// This is more for inherited permissions, we don't
		// want to block ourselves here.
		if (denyMap != null)
		{
			ProfileData denyProfile = denyMap.get(profile.getId());
			if (denyProfile != null)
			{
				denyMap.remove(denyProfile.getId());
				if (deny != null)
				{
					deny.remove(denyProfile);
				}
			}
		}
	}
	
	public void denyPermission(ProfileData profile)
	{
		if (denyMap == null)
		{
			denyMap = new HashMap<String, ProfileData>();
		}
		if (deny == null)
		{
			deny = new ArrayList<ProfileData>();
		}
		
		if (denyMap.get(profile.getId()) == null)
		{
			denyMap.put(profile.getId(), profile);
			deny.add(profile);
		}
		
		// Remove from the allow map if present, since we'd block it anyway.
		if (grantMap != null)
		{
			ProfileData allowProfile = grantMap.get(profile.getId());
			if (allowProfile != null)
			{
				grantMap.remove(allowProfile.getId());
				if (deny != null)
				{
					grant.remove(allowProfile);
				}
			}
		}
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
	public List<ProfileData> getGrant()
	{
		return grant;
	}

	public void setGrant(List<ProfileData> grant)
	{
		this.grant = grant;

		grantMap = new HashMap<String, ProfileData>();
		for (ProfileData profile : grant)
		{
			grantMap.put(profile.getId(), profile);
		}
	}

	@PersistField
	public List<ProfileData> getDeny()
	{
		return deny;
	}

	public void setDeny(List<ProfileData> deny)
	{
		this.deny = deny;

		denyMap = new HashMap<String, ProfileData>();
		for (ProfileData profile : deny)
		{
			denyMap.put(profile.getId(), profile);
		}
	}

	@PersistField
	public Group getParent()
	{
		return parent;
	}

	public void setParent(Group parent)
	{
		this.parent = parent;
	}
	
	protected List<ProfileData> grant;
	protected List<ProfileData> deny;
	protected String id;
	protected Group parent;
	
	// Transient
	private HashMap<String, ProfileData> grantMap;
	private HashMap<String, ProfileData> denyMap;
}
