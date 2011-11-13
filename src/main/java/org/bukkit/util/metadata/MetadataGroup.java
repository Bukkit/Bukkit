package org.bukkit.util.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.Plugin;

/**
 * Represents a group of metadata from the same key, each entry is unique per plugin
 */
public class MetadataGroup {

    private HashMap<Plugin, MetadataNode> map;

    public MetadataGroup() {
        map = new HashMap<Plugin, MetadataNode>();
    }

    /**
     * Assigns the MetadataNode by either creating a new instance or overwriting the value of the already existing instance
     * 
     * @param plugin Instance of plugin that is used as key in the group
     * @param key Metadata key
     * @param value Metadata value to be assigned
     */
    public void setMetadata(Plugin plugin, String key, String value) {
        if(map.containsKey(plugin)) {
            map.get(plugin).setValue(value);
        } else {
            map.put(plugin, new MetadataNode(plugin,key,value));
        }
    }

    /**
     * Gets all MetadataNodes assigned to this group.
     * 
     * @return A List of MetadataNode
     */
    public List<MetadataNode> getValues() {
        return new ArrayList<MetadataNode>(map.values());
    }

    /**
     * Gets a specific MetadataNode assigned by a plugin from this group.
     * 
     * @param plugin Instance of plugin
     * @return A single MetadataNode instance
     */
    public MetadataNode getValue(Plugin plugin) {
        if(map.containsKey(plugin))	{
            return map.get(plugin);
        } else {
            return null;
        }
    }
}