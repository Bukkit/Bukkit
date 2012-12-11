package org.bukkit.nbt.tag;

/**
 * A tag used to store a double value.
 */
public class DoubleTag extends Tag<Double> {

    public DoubleTag(String name, double value) {
        super(TagType.DOUBLE, name, value);
    }

}
