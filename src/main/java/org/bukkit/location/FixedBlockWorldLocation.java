package org.bukkit.location;

import org.bukkit.World;
import org.bukkit.block.Block;

public class FixedBlockWorldLocation extends FixedBlockLocation implements WorldLocation {

    private final World world;

    public FixedBlockWorldLocation(World world, int x, int y, int z) {
        super(x, y, z);
        this.world = world;
    }

    public FixedBlockWorldLocation(World world, Location location) {
        super(location);
        this.world = world;
    }

    public FixedBlockWorldLocation(WorldLocation location) {
        this(location.getWorld(), location);
    }

    public World getWorld() {
        return this.world;
    }

    public Block getBlock() {
        return this.world.getBlockAt(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FixedBlockWorldLocation other = (FixedBlockWorldLocation) obj;
        if (world == null) {
            if (other.world != null)
                return false;
        } else if (!world.equals(other.world))
            return false;
        return true;
    }

    @Override
    public Location getRelative(int modX, int modY, int modZ) {
        return new FixedBlockWorldLocation(this.world, this.x + modX, this.y + modY, this.z + modZ);
    }
}
