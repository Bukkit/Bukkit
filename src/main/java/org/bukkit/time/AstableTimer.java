package org.bukkit.time;

import org.bukkit.Server;

/**
 * An org.bukkit.Timer that operates with continuous-oscillation semantics.
 * @author thylordroot
 *
 * This Timer is set to periodically fire according to a specified period from the
 * starting time. Upon each update, the target time is increased by the period.
 * In the case where excess events would be generated, the scheduler spends the
 * next cycle completing these events, until the Timer is back on track.
 *
 * @todo should we allow for the possibility of realtime scheduling here?
 */
public class AstableTimer extends AbstractTimer {
    /**
     * The period, in Minecraft ticks, when this timer resets.
     */
    private int m_period;
    
    /**
     * Assert the correctness of a period
     */
    public static void assertPeriod(int period) {
        if (period < 1)
            throw new IllegalArgumentException("Invalid period: " + period);
    }
    
    /**
     * Instantiate a new MonostableTimer from the supplied long timestamp
     */
    public AstableTimer(long target, int period) {
        super(target);
        assertPeriod(period);
        m_period = period;
    }
    
    /**
     * Instantiate a new MonostableTimer from the supplied MinecraftTime
     */
    public AstableTimer(MinecraftTime target, int period) {
        super(target);
        assertPeriod(period);
        m_period = period;
    }
    
    /**
     * Instantiate a new Monostable timer with a target equal to the current
     * server time + offset.
     */
    public AstableTimer(Server server, long offset, int period) {
        super(server.getTime() + offset);
        assertPeriod(period);
        m_period = period;
    }
    
    /**
     * Instantiate a new Monostable timer with a target equal to the current
     * server time.
     */
    public AstableTimer(Server server, int period) {
        super(server.getTime());
        assertPeriod(period);
        m_period = period;
    }
    
    /**
     * Named constructor that constructs a AstableTimer relative to the start
     * of the closest day.
     */
    public static AstableTimer relToDay(long time, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToDay(time, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs a AstableTimer relative to the start
     * of the closest day.
     */
    public static AstableTimer relToDay(Server server, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToDay(server, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs a AstableTimer relative to the
     * day following the closest day.
     */
    public static AstableTimer relToNextDay(long time, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToNextDay(time, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs a AstableTimer relative to the 
     * day following the current day.
     */
    public static AstableTimer relToNextDay(Server server, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToNextDay(server, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs an AstableTimer relative to the start
     * of the closest hour.
     */
    public static AstableTimer relToHour(long time, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToHour(time, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs an AstableTimer relative to the start
     * of the current hour.
     */
    public static AstableTimer relToHour(Server server, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToHour(server, 
            offset), period);
    }
    
    /**
     * Named constructor that constructs an AstableTimer relative to 
     * the hour following the closest hour.
     */
    public static AstableTimer relToNextHour(long time, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToNextHour(time, offset), 
            period);
    }
    
    /**
     * Named constructor that constructs an AstableTimer relative to the hour
     * following the current hour.
     */
    public static AstableTimer relToNextHour(Server server, long offset, int period) {
        return new AstableTimer(MinecraftTime.relToNextHour(server, 
            offset), period);
    }
    
    /**
     * Increase the timer by its period.
     */
    public boolean update(long time) {
        offsetTarget(m_period);
        return true;
    }
};