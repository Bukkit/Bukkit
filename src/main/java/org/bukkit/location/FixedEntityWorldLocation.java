package org.bukkit.location;

import org.bukkit.World;
import org.bukkit.block.Block;

public class FixedEntityWorldLocation extends FixedEntityLocation implements WorldLocation {

    private final World world;
    
    public FixedEntityWorldLocation(World world, double x, double y, double z) {
        super(x, y, z);
        this.world = world;
    }

    public FixedEntityWorldLocation(World world, Location location) {
        super(location);
        this.world = world;
    }
    
    public FixedEntityWorldLocation(WorldLocation location) {
        this(location.getWorld(), location);
    }
    
    public World getWorld() {
        return this.world;
    }

    public Block getBlock() {
        return this.world.getBlockAt(this);
    }

    public Location getRelative(int modX, int modY, int modZ) {
        return new FixedEntityWorldLocation(this.world, this.x + modX, this.y + modY, this.z + modZ);
    }
}
