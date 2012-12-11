package org.bukkit.nbt.tag;

/**
 * A tag used to store a short value.
 */
public class ShortTag extends Tag<Short> {

    public ShortTag(String name, short value) {
        super(TagType.SHORT, name, value);
    }

}
