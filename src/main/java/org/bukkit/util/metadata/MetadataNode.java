package org.bukkit.util.metadata;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Metadata node
 */
public class MetadataNode {    	
    private Plugin plugin;
    private String key;
    private Object value;
    
    /**
     * Constructor creating the MetadataNode 
     * 
     * @param plugin Instance of plugin that created this metadata
     * @param key The key the metadata is stored under
     * @param value The value of this MetadataNode.
     */
    public MetadataNode(Plugin plugin, String key, String value)
    {
        this.plugin = plugin;
        this.key = key;
        this.value = value;
    }
    
    /**
     * Overwrite the assigned value
     * @param value
     */
    protected void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Get the value of the MetadataNode
     * @return Value
     */
    public Object getValue() {
        return value;
    }
    
    /**
     * Get the key the MetadataNode
     * @return Key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Get the instance of the plugin that assigned this MetadataNode
     * @return Instance of plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }
    
    /**
     * Get the name of the plugin that assigned this MetadataNode
     * @return Name of plugin
     */
    public String getPluginName() {
        return plugin.getDescription().getName();
    }
}
