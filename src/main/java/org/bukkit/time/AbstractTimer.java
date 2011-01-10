
package org.bukkit.time;

import org.bukkit.Timer;

/**
 * An abstract implementation of the java.org.Bukkit interface.
 *
 * @author thylordroot
 *
 * This class serves as the basis for Timer objects in Bukkit, and may
 * be extended to make the writing of timers simpler.
 */
public abstract class AbstractTimer implements Timer {
    private long m_target;
    
    /**
     * Instantiate a new AbstractTimer from a timestamp.
     */
    public AbstractTimer(long target) {
        MinecraftTime.assertTime(target);
        m_target = target;
    }
    
    /**
     * Instantiate a new AbstractTimer from a MinecraftTime.
     */
    public AbstractTimer(MinecraftTime ts) {
        m_target = ts.ticksSinceGenesis();
    }
    
    public boolean hasExpired(long time) {
        return time >= m_target;
    }
    
    public long getNextTarget() {
        return m_target;
    }
    
    public void setNextTarget(long target) {
        /* Shouldn't specify a time in the past! */
        MinecraftTime.assertTime(target);
        m_target = target;
    }
    
    /**
     * Offset the target time by an interval.
     */
    public void offsetTarget(long interval) {
        m_target += interval;
        if (m_target < 0)
            m_target = 0;
    }
    
    public abstract boolean update(long curTime);
};