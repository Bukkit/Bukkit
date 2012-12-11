package org.bukkit.nbt.tag;

import java.util.Arrays;

/**
 * A tag used to store a byte array.
 */
public class ByteArrayTag extends Tag<byte[]> {

    public ByteArrayTag(String name, byte[] value) {
        super(TagType.BYTE_ARRAY, name, value);
    }
    
    @Override
    public String toString() {
        return "Tag[type=" + this.getType() + ", name=" + this.getName() + ", value=" + Arrays.toString(this.getValue()) + "]";
    }

}
