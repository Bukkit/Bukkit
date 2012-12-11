package org.bukkit.nbt.tag;

/**
 * Represents an NBT tag.
 * 
 * @param <T> The type of value this tag stores.
 */
public abstract class Tag<T> {

    private TagType type;
    private String name;
    private T value;
    
    public Tag(TagType type, String name, T value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
    
    /**
     * Gets the name of this NBT tag.
     * 
     * @return The NBT tag's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the value of this NBT tag.
     * 
     * @return The NBT tag's value.
     */
    public T getValue() {
        return this.value;
    }
    
    /**
     * Gets the type of this this NBT tag.
     * 
     * @return The NBT tag's type.
     */
    public TagType getType() {
        return this.type;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Tag)) return false;
        Tag t = (Tag) o;
        return t.getName().equals(this.getName()) && t.getValue().equals(this.getValue());
    }
    
    @Override
    public String toString() {
        return "Tag[type=" + this.getType() + ", name=" + this.name + ", value=" + this.value + "]";
    }
    
}
