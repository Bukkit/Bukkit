package org.bukkit.nbt.tag;

/**
 * A tag used to store a byte value.
 */
public class ByteTag extends Tag<Byte> {

    public ByteTag(String name, byte value) {
        super(TagType.BYTE, name, value);
    }

}
