package org.bukkit.dao;

import org.bukkit.permission.PermissionProfile;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;

/**
 * 
 * DOA???
 * 
 * We'll see....
 * 
 * @author nathan
 *
 */
@PersistClass(schema="global", name="profile")
public class ProfileData implements IProfile
{
	public ProfileData()
	{
		
	}
	
	public ProfileData(String id)
	{
		this.id = id;
	}

	/*
	public void setProfile(PermissionProfile profile)
	{
		this.profile = profile;
	}
	*/

	/*
	 * Hrm.. this compiles... but Maven doesn't like it.... ???
	 * 
	 * [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:2.0.2:compile (default-compile) on project Persistence: Compilation failure
	 * [ERROR] /Users/nathan/Documents/Code/Eclipse/Bukkit/BukkitPlugins/PersistencePlugin/src/main/java/com/elmakers/mine/bukkit/plugins/persistence/dao/ProfileData.java:[33,25] type parameters of <T>T cannot be determined; no unique maximal instance exists for type variable T with upper bounds T,java.lang.Object
	 * [ERROR] -> [Help 1]
     *
     * Disabling for now, not working yet anyway (at an API level)
	 */
	/*
	public <T> T get(final String key)
	{
		if (this.profile == null)
		{
			return null;
		}

		return this.profile.get(key);
	}
	*/
	
	public boolean isSet(final String key)
	{
		if (this.profile == null)
		{
			return false;
		}

		return profile.isSet(key);
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

	String id;
	
	// transient
	PermissionProfile profile;
}
