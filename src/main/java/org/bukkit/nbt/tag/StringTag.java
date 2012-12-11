package org.bukkit.nbt.tag;

/**
 * A tag used to store a string value.
 */
public class StringTag extends Tag<String> {

    public StringTag(String name, String value) {
        super(TagType.STRING, name, value);
    }

}
