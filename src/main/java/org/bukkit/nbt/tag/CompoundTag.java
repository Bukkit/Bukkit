package org.bukkit.nbt.tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A tag used to store many other tags by name in a map.
 */
public class CompoundTag extends Tag<Map<String, Tag<?>>> {

    public CompoundTag(String name) {
        this(name, new HashMap<String, Tag<?>>());
    }
    
    public CompoundTag(String name, Map<String, Tag<?>> value) {
        super(TagType.COMPOUND, name, value);
    }
    
    /**
     * Puts a tag in this compound tag.
     * 
     * @param tag Tag to put.
     * @return The provided tag after being put in.
     */
    public Tag<?> put(Tag<?> tag) {
        return this.getValue().put(tag.getName(), tag);
    }
    
    /**
     * Gets the tag with the given name.
     * 
     * @param name Name to look for.
     * @return The stored tag with the given name.
     */
    public Tag<?> get(String name) {
        return this.getValue().get(name);
    }
    
    /**
     * Removes the tag with the given name.
     * 
     * @param name Name to remove.
     * @return The removed tag.
     */
    public Tag<?> remove(String name) {
        return this.getValue().remove(name);
    }
    
    /**
     * Gets a collection of all the tags in this compound tag.
     * 
     * @return A collection of all the tag's tags.
     */
    public Collection<Tag<?>> getTags() {
        return this.getValue().values();
    }

}
