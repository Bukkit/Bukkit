package org.bukkit.plugin;

import java.util.ArrayList;
import java.util.Hashtable;

public class PluginDebugger {
    private Hashtable<Plugin, ListenerLog> pluginTicks = new Hashtable<Plugin, ListenerLog>();
    private boolean countLogs = false;

    /**
     * Add's a Count to a ListenerLog for the given Plugin
     * 
     * @param plugin The called Plugin
     * @param listener The called Listener
     * @param value The used time in nanoseconds
     */
    public void add(Plugin plugin, String listener, Long value) {
        if (countLogs) {
            if (getPluginDebugger(plugin) == null) {
                addPlugin(plugin);
            }
            getPluginDebugger(plugin).addCount(listener, value);
        }
    }

    /**
     * Returns the ListenerLog with all data's of the given Plugin (or null)
     * 
     * @param plugin The Plugin 
     * @return ListenerLog of the Plugin
     */
    public ListenerLog getPluginDebugger(Plugin plugin) {
        if (pluginTicks.containsKey(plugin)) {
            return pluginTicks.get(plugin);
        }
        return null;
    }

    /**
     * Return the logged data
     * 
     * @return ArrayList with all logged data
     */
    public ArrayList<ListenerLog> getAll() {
    	ArrayList<ListenerLog> returnList = new ArrayList<ListenerLog>();
    	returnList.addAll(pluginTicks.values());
    	return returnList;
    }
    
    /**
     * Returns full used time for all plugins with all listeners
     * 
     * @return long with time in nanoseconds
     */
    public Long getFullUsedTime() {
        long count = 0;
        for (ListenerLog listenerLog : pluginTicks.values()) {
            count += listenerLog.getFullCount();
        }
        return count;
    }
    
    /**
     * Return all called Listeners (for each Plugin)
     * 
     * @return The Count as int
     */
    public int getCalledListenersCount() {
        int count = 0;
        for (ListenerLog listenerLog : pluginTicks.values()) {
            count += listenerLog.getRegisteredListeners().size();
        }
        return count;
    }

    /**
     * Creates and adds a new ListenerLog for the given Plugin
     * 
     * @param plugin the Plugin
     */
    private void addPlugin(Plugin plugin) {
        this.pluginTicks.put(plugin, new ListenerLog(plugin));
    }

    /**
     * Clear's all data
     */
    public void clearAll() {
        this.pluginTicks.clear();
    }

    /**
     * Deactivate or activate the Debug for all Plugins
     * 
     * @param bool the new state of the Debugger (on or off)
     */
    public void setDebugging(Boolean bool) {
        this.countLogs = bool;
    }
    
    /**
     * Returns Current state of the Debugger
     * 
     * @return boolean for current state
     */
    public boolean getDebugging() {
    	return countLogs;
    }
}