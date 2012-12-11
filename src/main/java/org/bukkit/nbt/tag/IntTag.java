package org.bukkit.nbt.tag;

/**
 * A tag used to store a integer value.
 */
public class IntTag extends Tag<Integer> {

    public IntTag(String name, int value) {
        super(TagType.INT, name, value);
    }

}
