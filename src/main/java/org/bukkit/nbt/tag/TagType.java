package org.bukkit.nbt.tag;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the type of an NBT tag.
 */
public enum TagType {
    /**
     * Represents an {@link EndTag}
     */
    END(0),
    /**
     * Represents a {@link ByteTag}
     */
    BYTE(1),
    /**
     * Represents a {@link ShortTag}
     */
    SHORT(2),
    /**
     * Represents a {@link IntTag}
     */
    INT(3),
    /**
     * Represents a {@link LongTag}
     */
    LONG(4),
    /**
     * Represents a {@link FloatTag}
     */
    FLOAT(5),
    /**
     * Represents a {@link DoubleTag}
     */
    DOUBLE(6),
    /**
     * Represents a {@link ByteArrayTag}
     */
    BYTE_ARRAY(7),
    /**
     * Represents a {@link StringTag}
     */
    STRING(8),
    /**
     * Represents a {@link ListTag}
     */
    LIST(9),
    /**
     * Represents a {@link CompoundTag}
     */
    COMPOUND(10),
    /**
     * Represents a {@link IntArrayTag}
     */
    INT_ARRAY(11);
    
    private final byte id;
    private final static Map<Byte, TagType> BY_ID = Maps.newHashMap();

    private TagType(int id) {
        this.id = (byte) id;
    }
    
    /**
     * Gets the ID of this tag type.
     * @return The tag type's ID.
     */
    public byte getId() {
        return this.id;
    }
    
    /**
     * Gets the TagType with the given ID.
     * @param id ID to look for.
     * @return The TagType with the given ID.
     */
    public static TagType byId(int id) {
        return BY_ID.get((byte) id);
    }
    
    static {
        for(TagType type : values()) {
            BY_ID.put(type.getId(), type);
        }
    }
}
