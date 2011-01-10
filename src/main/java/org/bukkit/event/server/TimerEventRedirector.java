package org.bukkit.event.server;

/**
 * A ServerListener that accepts TimerEvents and dispatches them to
 * registered Callback objects.
 *
 * @author thylordroot
 *
 * A TimerEventRedirector takes a single TimerEvent and propogates
 * it to an associated collection of Callback objects. 
 */
 
import org.bukkit.Timer;
import org.bukkit.event.Event;
import org.bukkit.event.server.TimerEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event.Priority;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Collection;

public class TimerEventRedirector extends ServerListener {
    private class TimerInfo extends HashSet<Callback> {
        public TimerInfo() {
            super();
        }
    }
    
    private HashMap<Timer, TimerInfo> m_registered; 
    
    /**
     * Look up timer registery information, possibly creating a new
     * one if the timer isn't registered.
     */
    private TimerInfo lookupInfo(Timer t, boolean createIfNotExists) {
        TimerInfo ret = m_registered.get(t);
        
        /* See if we need to create a new entry */
        if (ret == null && createIfNotExists) {
            ret = new TimerInfo();
            m_registered.put(t, ret);
        }
        
        return ret;
    }
    
    /**
     * An object which handles a TimerEvent
     */
    public static interface Callback {
        public void onTimerExpired(TimerEvent event);
    }
    
    /* Constructors */
    
    public TimerEventRedirector() {
        m_registered = new HashMap<Timer, TimerInfo>();
    }
    
    /* Inherited from ServerListener */
    
    /**
     * Dispatch a TimerEvent to its associated Callback objects
     */
    public void onTimerExpired(TimerEvent te) {
        TimerInfo ti = lookupInfo(te.getTimer(), false);
        
        /* Timer isn't registered, discard the event */
        if (ti == null)
            return;
        
        /* Propagate the events to all listening callbacks */
        for (Callback c : ti) {
            c.onTimerExpired(te);
        }
    }
    
    /**
     * Disassociate the associated Timer with this 
     * TimerEventRedirector
     */
    public void onTimerUnregistered(TimerEvent te) {
        /* Remove the timer as it is no longer valid */
        m_registered.remove(te.getTimer());
    }
    
    /* Registry manipulation */
    
    /**
     * Associate a callback with a Timer.
     */
    public void add(Timer timer, Callback response) {
        TimerInfo ti = lookupInfo(timer, true);
        
        /* This uses set semantics so this is OK */
        ti.add(response);
    }
    
    /**
     * Remove the association of response with timer
     */
    public void remove(Timer timer, Callback response) {
        TimerInfo ti = lookupInfo(timer, false);
        if (ti == null)
            throw new IllegalArgumentException(
                "Unregistered timer supplied.");
        
        ti.remove(response);
    }
    
    /**
     * Remove all timer associations with response 
     */
    public void remove(Callback response) {
        for (TimerInfo ti : m_registered.values()) {
            ti.remove(response);
        }
    }
    
    /**
     * Stop listening for a timer
     */
    public void remove(Timer timer) {
        if (m_registered.remove(timer) == null)
            throw new IllegalArgumentException(
                "Unregistered timer supplied.");
    }

    /**
     * Obtain a list of timers associated with the callback.
     */
    public java.util.Collection<Timer>  getAssociatedTimers(
        Callback callback) {
        HashSet ret = new HashSet<Timer>();
        
        /* Build the timer set */
        for (Map.Entry<Timer, TimerInfo> e : 
            m_registered.entrySet()) {
            if (e.getValue().contains(callback))
                ret.add(e.getKey());
        }
            
        return ret;
    }
    
    /**
     * Set up event hooks for this TimerEventRedirector
     */
    public void performRegistration(PluginManager manager, 
        Plugin plugin, Priority onExpire, Priority onUnregister) {
        manager.registerEvent(Event.Type.TIMER_EXPIRE, this,
            onExpire, plugin);
        manager.registerEvent(Event.Type.TIMER_UNREGISTER, 
            this, onUnregister, plugin);
    }
    
    /**
     * Synonymous with performRegistration(manager, plugin,
     * Priority.Normal, Priority.Low)
     */
    public void performRegistration(PluginManager manager,
        Plugin plugin) {
        performRegistration(manager, plugin, Priority.Normal, 
            Priority.Low);
    }
};