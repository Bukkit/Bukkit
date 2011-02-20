package org.bukkit.dao;

import org.bukkit.Location;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;


@PersistClass(schema="global", name="orientation", contained=true)
public class Orientation
{
	public Orientation()
	{
		
	}
	
	public Orientation(Location location)
	{
		yaw = location.getYaw();
		pitch = location.getPitch();
	}
	
	@PersistField
	public float getPitch()
	{
		return pitch;
	}
	
	public void setPitch(float pitch)
	{
		this.pitch = pitch;
	}
	
	@PersistField
	public float getYaw()
	{
		return yaw;
	}
	
	public void setYaw(float yaw)
	{
		this.yaw = yaw;
	}
	
	protected float pitch;
	protected float yaw;
}
