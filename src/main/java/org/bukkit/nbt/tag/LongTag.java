package org.bukkit.nbt.tag;

/**
 * A tag used to store a long value.
 */
public class LongTag extends Tag<Long> {

    public LongTag(String name, long value) {
        super(TagType.LONG, name, value);
    }

}
