package org.bukkit.nbt.tag;

/**
 * A tag used to store a float value.
 */
public class FloatTag extends Tag<Float> {

    public FloatTag(String name, float value) {
        super(TagType.FLOAT, name, value);
    }

}
