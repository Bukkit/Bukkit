package org.bukkit.nbt.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A tag containing a list of other tags.
 */
public class ListTag extends Tag<List<Tag<?>>> implements Iterable<Tag<?>> {

    public ListTag(String name) {
        this(name, new ArrayList<Tag<?>>());
    }
    
    public ListTag(String name, List<Tag<?>> value) {
        super(TagType.LIST, name, value);
    }
    
    /**
     * Adds a tag to the list.
     * 
     * @param tag Tag to add.
     * @return Whether the tag was added or not.
     */
    public boolean add(Tag<?> tag) {
        return this.getValue().add(tag);
    }
    
    /**
     * Sets the tag at the given index to the given tag.
     * 
     * @param index Index to set the tag at.
     * @param tag Tag to set.
     * @return The tag after being set.
     */
    public Tag<?> set(int index, Tag<?> tag) {
        return this.getValue().set(index, tag);
    }
    
    /**
     * Gets the tag at the given index.
     * 
     * @param index Index to get from.
     * @return The tag at the given index.
     */
    public Tag<?> get(int index) {
        return this.getValue().get(index);
    }
    
    /**
     * Removes the tag at the given index.
     * 
     * @param index Index of the tag.
     * @return The removed tag.
     */
    public Tag<?> remove(int index) {
        return this.getValue().remove(index);
    }
    
    /**
     * Removes a tag from this list.
     * 
     * @param tag Tag to remove.
     * @return Whether the tag was removed.
     */
    public boolean remove(Tag<?> tag) {
        return this.getValue().remove(tag);
    }
    
    /**
     * Gets the size of this list.
     * 
     * @return The list's size.
     */
    public int size() {
        return this.getValue().size();
    }

    @Override
    public Iterator<Tag<?>> iterator() {
        return this.getValue().iterator();
    }

}
