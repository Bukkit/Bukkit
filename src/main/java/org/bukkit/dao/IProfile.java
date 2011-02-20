package org.bukkit.dao;

/**
 * This should be temporary, I'm hoping once I get persisted inheretence
 * working, this won't be needed.
 * 
 * Right now, it's here to support routing permission requests through
 * Persistence in a generic way, since all my plugins (and other devs' plugins)
 * rely on Persistence for that right now.
 * 
 * @author nathan
 *
 */
public interface IProfile
{
	
	/**
	 * This is the interface for permissions profiles
	 * 
	 * @param key The key to check for
	 * @return True if this profile has this permissions
	 */
	public boolean isSet(String key);
}
