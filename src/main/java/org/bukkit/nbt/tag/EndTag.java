package org.bukkit.nbt.tag;

/**
 * A tag representing the end of a {@link CompoundTag}'s data.
 */
public class EndTag extends Tag<Object> {

    public EndTag() {
        super(TagType.END, "", null);
    }

}
