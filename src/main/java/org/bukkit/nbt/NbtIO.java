package org.bukkit.nbt;

import java.io.File;
import java.io.IOException;

import org.bukkit.nbt.tag.CompoundTag;

/**
 * The I/O used to save or load an NBT tag.
 */
public interface NbtIO {

    /**
     * Loads an NBT file from the disk and returns its root tag.
     * 
     * @param file File to load from.
     * @return The root tag of the loaded NBT file.
     * @throws IOException if an I/O error occurs while loading.
     */
    public CompoundTag load(String file) throws IOException;
    
    /**
     * Loads an NBT file from the disk and returns its root tag.
     * 
     * @param file File to load from.
     * @return The root tag of the loaded NBT file.
     * @throws IOException if an I/O error occurs while loading.
     */
    public CompoundTag load(File file) throws IOException;
    
    /**
     * Saves an NBT file's root tag to the disk.
     * 
     * @param file File to save to.
     * @param tag Tag to save.
     * @throws IOException if an I/O error occurs while saving.
     */
    public void save(String file, CompoundTag tag) throws IOException;
    
    /**
     * Saves an NBT file's root tag to the disk.
     * 
     * @param file File to save to.
     * @param tag Tag to save.
     * @throws IOException if an I/O error occurs while saving.
     */
    public void save(File file, CompoundTag tag) throws IOException;
    
}
