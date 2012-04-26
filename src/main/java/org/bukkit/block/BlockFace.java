package org.bukkit.block;

/**
 * Represents the face of a block
 */
public enum BlockFace {
    
    SELF(0, 0, 0),
    
    // Original Coordinate System (Older)
    NORTH(-1, 0, 0),
    EAST(0, 0, -1),
    SOUTH(1, 0, 0),
    WEST(0, 0, 1),
    UP(0, 1, 0),
    DOWN(0, -1, 0),
    NORTH_EAST(NORTH, EAST),
    NORTH_WEST(NORTH, WEST),
    SOUTH_EAST(SOUTH, EAST),
    SOUTH_WEST(SOUTH, WEST),
    WEST_NORTH_WEST(WEST, NORTH_WEST),
    NORTH_NORTH_WEST(NORTH, NORTH_WEST),
    NORTH_NORTH_EAST(NORTH, NORTH_EAST),
    EAST_NORTH_EAST(EAST, NORTH_EAST),
    EAST_SOUTH_EAST(EAST, SOUTH_EAST),
    SOUTH_SOUTH_EAST(SOUTH, SOUTH_EAST),
    SOUTH_SOUTH_WEST(SOUTH, SOUTH_WEST),
    WEST_SOUTH_WEST(WEST, SOUTH_WEST),
    
    // Sun Based Coordinate System (Newer)
    SUN_NORTH(EAST),
    SUN_EAST(SOUTH),
    SUN_SOUTH(WEST),
    SUN_WEST(NORTH),
    SUN_NORTH_EAST(SOUTH_EAST),
    SUN_NORTH_WEST(NORTH_EAST),
    SUN_SOUTH_EAST(SOUTH_WEST),
    SUN_SOUTH_WEST(NORTH_WEST),
    SUN_WEST_NORTH_WEST(NORTH_NORTH_EAST),
    SUN_NORTH_NORTH_WEST(EAST_NORTH_EAST),
    SUN_NORTH_NORTH_EAST(EAST_SOUTH_EAST),
    SUN_EAST_NORTH_EAST(SOUTH_SOUTH_EAST),
    SUN_EAST_SOUTH_EAST(SOUTH_SOUTH_WEST),
    SUN_SOUTH_SOUTH_EAST(WEST_SOUTH_WEST),
    SUN_SOUTH_SOUTH_WEST(WEST_NORTH_WEST),
    SUN_WEST_SOUTH_WEST(NORTH_NORTH_WEST);

    private final int modX;
    private final int modY;
    private final int modZ;
    
    private boolean sunBased;
    private BlockFace equivalent = this;

    private BlockFace(final int modX, final int modY, final int modZ) {
        this.modX = modX;
        this.modY = modY;
        this.modZ = modZ;
        this.sunBased = false;
    }

    private BlockFace(final BlockFace face1, final BlockFace face2) {
        this.modX = face1.getModX() + face2.getModX();
        this.modY = face1.getModY() + face2.getModY();
        this.modZ = face1.getModZ() + face2.getModZ();
        this.sunBased = false;
    }
    
    private BlockFace(final BlockFace equivalent) {
        this(equivalent.modX, equivalent.modY, equivalent.modZ);
        this.sunBased = true;
        this.equivalent = equivalent;
        equivalent.equivalent = this;
    }

    /**
     * Get the amount of X-coordinates to modify to get the represented block
     *
     * @return Amount of X-coordinates to modify
     */
    public int getModX() {
        return modX;
    }

    /**
     * Get the amount of Y-coordinates to modify to get the represented block
     *
     * @return Amount of Y-coordinates to modify
     */
    public int getModY() {
        return modY;
    }

    /**
     * Get the amount of Z-coordinates to modify to get the represented block
     *
     * @return Amount of Z-coordinates to modify
     */
    public int getModZ() {
        return modZ;
    }

    public BlockFace getOppositeFace() {
        switch (this) {
        case SELF: return BlockFace.SELF;
        
        // Original
        case NORTH: return BlockFace.SOUTH;
        case SOUTH: return BlockFace.NORTH;
        case EAST: return BlockFace.WEST;
        case WEST: return BlockFace.EAST;
        case UP: return BlockFace.DOWN;
        case DOWN: return BlockFace.UP;
        case NORTH_EAST: return BlockFace.SOUTH_WEST;
        case NORTH_WEST: return BlockFace.SOUTH_EAST;
        case SOUTH_EAST: return BlockFace.NORTH_WEST;
        case SOUTH_WEST: return BlockFace.NORTH_EAST;
        case WEST_NORTH_WEST: return BlockFace.EAST_SOUTH_EAST;
        case NORTH_NORTH_WEST: return BlockFace.SOUTH_SOUTH_EAST;
        case NORTH_NORTH_EAST: return BlockFace.SOUTH_SOUTH_WEST;
        case EAST_NORTH_EAST: return BlockFace.WEST_SOUTH_WEST;
        case EAST_SOUTH_EAST: return BlockFace.WEST_NORTH_WEST;
        case SOUTH_SOUTH_EAST: return BlockFace.NORTH_NORTH_WEST;
        case SOUTH_SOUTH_WEST: return BlockFace.NORTH_NORTH_EAST;
        case WEST_SOUTH_WEST: return BlockFace.EAST_NORTH_EAST;
        
        // Sun-Based
        case SUN_NORTH: return BlockFace.SUN_SOUTH;
        case SUN_SOUTH: return BlockFace.SUN_NORTH;
        case SUN_EAST: return BlockFace.SUN_WEST;
        case SUN_WEST: return BlockFace.SUN_EAST;
        case SUN_NORTH_EAST: return BlockFace.SUN_SOUTH_WEST;
        case SUN_NORTH_WEST: return BlockFace.SUN_SOUTH_EAST;
        case SUN_SOUTH_EAST: return BlockFace.SUN_NORTH_WEST;
        case SUN_SOUTH_WEST: return BlockFace.SUN_NORTH_EAST;
        case SUN_WEST_NORTH_WEST: return BlockFace.SUN_EAST_SOUTH_EAST;
        case SUN_NORTH_NORTH_WEST: return BlockFace.SUN_SOUTH_SOUTH_EAST;
        case SUN_NORTH_NORTH_EAST: return BlockFace.SUN_SOUTH_SOUTH_WEST;
        case SUN_EAST_NORTH_EAST: return BlockFace.SUN_WEST_SOUTH_WEST;
        case SUN_EAST_SOUTH_EAST: return BlockFace.SUN_WEST_NORTH_WEST;
        case SUN_SOUTH_SOUTH_EAST: return BlockFace.SUN_NORTH_NORTH_WEST;
        case SUN_SOUTH_SOUTH_WEST: return BlockFace.SUN_NORTH_NORTH_EAST;
        case SUN_WEST_SOUTH_WEST: return BlockFace.SUN_EAST_NORTH_EAST;
        }
        
        return null; // This will never result; it is only for the compiler to feel better about itself
    }
    
    /**
     * Translates between the two coordinate systems, the older original system and the newer sun-based system
     * 
     * @return equivalent in opposite coordinate system; SELF returns SELF 
     */
    public BlockFace getEquivalent() {
        return this.equivalent;
    }
    
    /**
     * Determine if this is referencing the sun-based coordinate system
     * 
     * @return true if this references the newer sun-based coordinate system; false if it references the older original system; null if both apply (SELF)
     */
    public Boolean isSunBased() {
        return (this == BlockFace.SELF ? null : this.sunBased);
    }
    
    public BlockFace toOriginal() {
        if (this == BlockFace.SELF || !this.isSunBased()) return this;
        
        return this.equivalent;
    }
    
    public BlockFace toSunBased() {
        if (this == BlockFace.SELF || this.isSunBased()) return this;
        
        return this.equivalent;
    }
    
}
