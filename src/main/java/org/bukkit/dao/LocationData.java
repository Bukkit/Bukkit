package org.bukkit.dao;


import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;
import org.bukkit.util.BlockVector;

/**
 * Persist data from a location. Convenience getter and setters are available
 * for ease of object interaction. Use locationFactory(Server) to generate a new
 * concrete instance of the bukkit Location class.
 * 
 * @author amkeyte
 */
@PersistClass(schema = "global", name = "location", contained = true)
public class LocationData
{
	/**
	 * Default constructor for uninitialized LocationData Use setters for
	 * blockVector,orientation, and worldData before attempting to "put" this
	 * object.
	 */
	public LocationData()
	{
	}

	/**
	 * create a new location to persist
	 * 
	 * @param loc
	 *            location to Persist
	 */
	public LocationData(Location loc)
	{
		blockVector = initBlockVector(loc);
		worldData = initWorldData(loc);
		orientation = initOrientation(loc);
	}

	/**
	 * matches Location class constructor signature
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public LocationData(final World world, final double x, final double y, final double z)
	{
		Location loc = new Location(world, x, y, z);
		blockVector = initBlockVector(loc);
		worldData = initWorldData(loc);
		orientation = initOrientation(loc);
	}

	/**
	 * matches Location class constructor signature
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param yaw
	 * @param pitch
	 */
	public LocationData(final World world, final double x, final double y, final double z, final float yaw,
			final float pitch)
	{
		Location loc = new Location(world, x, y, z, yaw, pitch);
		blockVector = initBlockVector(loc);
		worldData = initWorldData(loc);
		orientation = initOrientation(loc);
	}

	/**
	 * initialize the orientation instance. for use in constructor
	 * 
	 * @return Orientation
	 */
	private Orientation initOrientation(Location loc)
	{
		setOrientation(new Orientation(loc));
		return orientation;
	}

	/**
	 * initialize the block vector instance. for use in constructor
	 * 
	 * @return BlockVector
	 */
	private BlockVector initBlockVector(Location loc)
	{
		setBlockVector(new BlockVector(loc.getX(), loc.getY(), loc.getZ()));
		return blockVector;
	}

	/*
	 * initialize the worldData instance.for use in constructor
	 * 
	 * @return WorldData
	 */
	private WorldData initWorldData(Location loc)
	{
		World world = loc.getWorld();
		setWorldData(new WorldData(world.getName(), world.getEnvironment()));
		return worldData;
	}

	/**
	 * lazy load a created instance
	 * 
	 * @return the blockVector
	 */
	@PersistField(contained = true)
	public BlockVector getBlockVector()
	{

		return blockVector;
	}

	/**
	 * read only
	 * 
	 * @return the orientation
	 */
	@PersistField(contained = true)
	public Orientation getOrientation()
	{
		return orientation;
	}

	/**
	 * get the worldData instance
	 * 
	 * readonly
	 * 
	 * @return the worldData
	 * 
	 */
	@PersistField(contained = true)
	public WorldData getWorldData()
	{
		return worldData;
	}

	/**
	 * @param blockVector
	 *            the blockVector to set
	 */
	public void setBlockVector(BlockVector blockVector)
	{
		this.blockVector = blockVector;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}

	/**
	 * @param worldData
	 *            the worldData to set
	 */
	public void setWorldData(WorldData worldData)
	{
		this.worldData = worldData;
	}

	/**
	 * creates a NEW Location object from the information that has been
	 * persisted. parameters edited in this object will NOT be persisted.
	 * 
	 * @return the location
	 */
	public Location toLocation(Server server)
	{
		if (worldData == null || blockVector == null || orientation == null)
		{
			// attempt to build Location on uninitialized LocationData.
			return null;
		}

		return new Location(worldData.getWorld(server), blockVector.getX(), blockVector.getY(), blockVector.getZ(),
				orientation.getYaw(), orientation.getPitch());
	}

	/**
	 * returns the provided Location object imprinted with information that has
	 * been persisted. parameters edited in this object will NOT be persisted.
	 * 
	 * @return the location
	 */
	public Location toLocation(Location location)
	{
		if (worldData == null || blockVector == null || orientation == null
				|| !location.getWorld().getName().equals(worldData.getName()))
		{
			// attempt to use Location on uninitialized LocationData.
			// or location is from wrong world
			return null;
		}
		location.setX(blockVector.getX());
		location.setY(blockVector.getY());
		location.setZ(blockVector.getZ());
		location.setPitch(orientation.getYaw());
		location.setYaw(orientation.getPitch());

		return location;
	}

	/************ Convenience wrappers ************/
	/**
	 * @return the x
	 */
	public Double getX() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}
		return blockVector.getX();
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(Double x) throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		blockVector.setX(x);
	}

	/**
	 * @return the y
	 */
	public Double getY() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		return blockVector.getY();
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		blockVector.setY(y);
	}

	/**
	 * @return the z
	 */
	public Double getZ() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		return blockVector.getZ();
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(Double z) throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		blockVector.setZ(z);
	}

	/**
	 * @return the pitch
	 */
	public Float getPitch() throws Exception
	{
		if (orientation == null)
		{
			throw new Exception("orientation not set");
		}

		return orientation.getPitch();
	}

	/**
	 * @param pitch
	 *            the pitch to set
	 */
	public void setPitch(Float pitch) throws Exception
	{
		if (orientation == null)
		{
			throw new Exception("orientation not set");
		}

		orientation.setPitch(pitch);
	}

	/**
	 * @return the yaw
	 */
	public Float getYaw() throws Exception
	{
		if (orientation == null)
		{
			throw new Exception("orientation not set");
		}
		return orientation.getYaw();
	}

	/**
	 * @param yaw
	 *            the yaw to set
	 */
	public void setYaw(Float yaw) throws Exception
	{
		if (orientation == null)
		{
			throw new Exception("orientation not set");
		}

		orientation.setYaw(yaw);
	}

	/**
	 * server required for WorldData to operate.
	 * 
	 * @param server
	 * @return
	 */
	public World getWorld(Server server) throws Exception
	{
		if (worldData == null)
		{
			throw new Exception("worldData not set");
		}

		return worldData.getWorld(server);
	}

	/**
	 * set the world
	 * 
	 * @param world
	 */
	public void setWorld(World world) throws Exception
	{
		if (worldData == null)
		{
			throw new Exception("worldData not set");
		}

		worldData.setName(world.getName());
	}

	/**
	 * Block coordinate
	 * 
	 * @return Integer
	 */
	public Integer getBlockX() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		return blockVector.getBlockX();
	}

	/**
	 * Block coordinate
	 * 
	 * @return Integer
	 */
	public Integer getBlockY() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		return blockVector.getBlockY();
	}

	/**
	 * Block coordinate
	 * 
	 * @return Integer
	 */
	public Integer getBlockZ() throws Exception
	{
		if (blockVector == null)
		{
			throw new Exception("blockVector not set");
		}

		return blockVector.getBlockZ();
	}
	

	private BlockVector	blockVector	= null;
	private Orientation	orientation	= null;
	private WorldData	worldData	= null;
}
