package org.bukkit.plugin;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListenerLog {
    private Hashtable<String, Long> listenerLog = new Hashtable<String, Long>();
    private ArrayList<String> registeredListeners = new ArrayList<String>();
    private Plugin plugin;

    /**
     * Creates a new instance of this Class
     * 
     * @param instance of a Plugin
     */
    public ListenerLog(Plugin instance) {
        this.plugin = instance;
    }

    /**
     * Adds a value to the value of the listener
     * 
     * @param listener The called listener
     * @param count value to add to the old value
     */
    public void addCount(String listener, long count) {
        if (this.registeredListeners.contains(listener)) {
            this.listenerLog.put(listener, (Long) this.listenerLog.get(listener).longValue() + count);
        } else {
            this.listenerLog.put(listener, count);
            this.registeredListeners.add(listener);
        }
    }
    
    /**
     * Returns a Copy of this ListenerLog
     * 
     * @return a Copy of this element or null
     */
    public ListenerLog getCopy() {
        try {
            return (ListenerLog) this.clone(); 
        } catch (CloneNotSupportedException e) {
    	    return null;
        }
    }
    
    /**
     * Clears all data of this ListenerLog
     */
    public void ClearAll() {
        this.listenerLog.clear();
        this.registeredListeners.clear();
    }
    
    /**
     * Returns the Time to execute the given Listener in nanoseconds
     * 
     * @param listener The listener
     * @return the used time in nanoseconds to execute
     */
    public Long getCount(String listener) {
        return this.listenerLog.get(listener);
    }
    
    /**
     * Get's all Listeners that was added in the Debug Time
     * 
     * @return ArrayListList of all Listeners
     */
    public ArrayList<String> getRegisteredListeners() {
        return this.registeredListeners;
    }
    
    /**
     * Get's the time to execute in nanoseconds for all used Listeners
     * 
     * @return Hashtable with all Listeners an the used time
     */
    public Hashtable<String, Long> getListenerLog() {
        return this.listenerLog;
    }   
    
    /**
     * Returns the complete time for all Listeners to execute in nanoseconds
     * 
     * @return Long Time in Nanoseconds or Null
     */
    public Long getFullCount() {
       long val = 0;
       for (Long value : this.listenerLog.values()) {
           val = Long.valueOf(val + value.longValue());
        }
        return val;
    }

     
    /**
     * Returns the Plugin of this ListenerLog
     * 
     * @return the Plugin
     */
    public Plugin getPlugin() {
        return this.plugin;
    }
}