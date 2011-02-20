package org.bukkit.dao;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;
import org.bukkit.util.BlockVector;


@PersistClass(schema="global", name="world")
public class WorldData
{
	public WorldData()
	{
		
	}
	
	public WorldData(String name, Environment type)
	{
		this.name = name;
		setEnvironmentType(type);
	}
	
	public void update(World world)
	{
		name = world.getName();
		id = world.getId();
		Location location = world.getSpawnLocation();
		spawn = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		setEnvironmentType(world.getEnvironment());
	}
	
	public World getWorld(Server server)
	{
		if (world != null) return world;
		
		List<World> worlds = server.getWorlds();
		for (World checkWorld : worlds)
		{
			if (checkWorld.getName().equalsIgnoreCase(name))
			{
				this.world = checkWorld;
				return world;
			}
		}
		
		world = server.createWorld(name, getEnvironmentType());
		return world;
	}
	
	@PersistField(id=true)
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@PersistField
	public BlockVector getSpawn()
	{
		return spawn;
	}
	
	public void setSpawn(BlockVector spawn)
	{
		this.spawn = spawn;
	}
	
	@PersistField
	public Environment getEnvironmentType()
	{
		return environmentType;
	}
	
	public void setEnvironmentType(Environment environmentType)
	{
		this.environmentType = environmentType;
	}

	protected String		name;
	protected long			id;
	protected BlockVector	spawn;
	protected Environment	environmentType;
	
	protected World			world;
}
