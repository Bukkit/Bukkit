package org.bukkit.nbt.tag;

import java.util.Arrays;

/**
 * A tag used to store a integer array.
 */
public class IntArrayTag extends Tag<int[]> {

    public IntArrayTag(String name, int[] value) {
        super(TagType.INT_ARRAY, name, value);
    }
    
    @Override
    public String toString() {
        return "Tag[type=" + this.getType() + ", name=" + this.getName() + ", value=" + Arrays.toString(this.getValue()) + "]";
    }

}
