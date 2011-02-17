package org.bukkit.persistence.core;

class CachedObject
{
	private Object object;
	private boolean cached;
	private boolean dirty;
	private long cacheTime;
	
	public CachedObject(Object o)
	{
		object = o;
		cached = true;
		dirty = false;
		cacheTime = System.currentTimeMillis();
		updateCacheTime();
	}
	
	public void setCached(boolean c)
	{
		cached = c;
	}
	
	public Object getObject()
	{
		return object;
	}
	
	public boolean isCached()
	{
		return cached;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	public long getCacheTime()
	{
		return cacheTime;
	}
	
	public void setObject(Object o)
	{
		object = o;
		dirty = true;
		updateCacheTime();
	}
	
	public void setSaved()
	{
		dirty = false;
		updateCacheTime();
	}
	
	protected void updateCacheTime()
	{
		cacheTime = System.currentTimeMillis();
	}
	
}
