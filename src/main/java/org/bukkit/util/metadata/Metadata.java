package org.bukkit.util.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Represents a metadata collection
 */
public class Metadata {

    private HashMap<String, MetadataGroup> meta;

    public Metadata() {
        meta = new HashMap<String, MetadataGroup>();
    }

    /**
     * Assigns some metadata based on two keys, a plugin instance and a string key
     * <p>
     * If the same plugin assigns a metadata value to a key that already has been assigned by the plugin
     * the former value will be overwriten.
     * The same key can however be used by different plugins, they will be added to a internal list of values.
     * 
     * @param plugin The instance of the plugin calling this function
     * @param key The key that the value should be stored under
     * @param value The value of the metadata
     */
    public void setMetadata(Plugin plugin, String key, String value) {		
        if(meta.containsKey(key)) {
            meta.get(key).setMetadata(plugin,key,value);
        } else {
            MetadataGroup group = new MetadataGroup();
            group.setMetadata(plugin,key,value);
            meta.put(key, group);
        }
    }

    /**
     * Gets a list of MetadataNodes. Each node represents a metadata value assigned by a unique plugin.
     * 
     * @param key The metadata key to get the data.
     * @return A List of MetaDataNode (Empty list of no values found)
     */
    public List<MetadataNode> getMetadata(String key) {
        if(meta.containsKey(key)) {
            MetadataGroup group = meta.get(key);
            return group.getValues();
        } else {
            //Could also return null instead?
            return new ArrayList<MetadataNode>();
        }
    }


    /**
     * Gets a single MetadataNode assigned by a specific plugin
     * 
     * @param key The metadata key
     * @param plugin Instance of the specific plugin
     * @return A MetadataNode object
     */
    public MetadataNode getMetadata(String key, Plugin plugin) {
        if(meta.containsKey(key)) {
            return meta.get(key).getValue(plugin);
        } else {
            return null;
        }
    }

    /**
     * Gets a single MetadataNode assigned by a specific plugin (by plugin name)
     * 
     * @param key The metadata key
     * @param plugin Name of the specific plugin
     * @return A MetadataNode object
     */
    public MetadataNode getMetadata(String key, String plugin) {
        Plugin plug = Bukkit.getServer().getPluginManager().getPlugin(plugin);
        if(plug != null) { 
            return getMetadata(key, plug);
        }
	    return null;
    }
}