
package org.bukkit;

/**
 * An interface that specifies a schedule in which a org.bukkit.event.server.TimerEvent
 * should be fired.
 *
 * @author thylordroot
 *
 * Timers by themselves do very little: they simply determine when
 * the current time has exceeded a specific point in time, 
 * (the target time) and provide a mechanism for performing timer 
 * updates. However, in conjunction with an org.bukkit.time.Scheduler, 
 * Timers can be used to coordinate events that should happen in the 
 * future.
 *
 * When the current time exceeds or matches the target time, it is said
 * to expire. Upon timer expiration, the thread that checks the timer
 * may respond in a variety of ways: for instance, dispatching an
 * org.bukkit.event.server.TimerEvent. Generally, no action is taken
 * if a timer has not expired, and it is typical for the timer to be
 * updated if it does.
 *
 * Timers also exhibit some form of stability. Stability is the property of
 * a timer that determines its effective lifetime. Timers fall into two
 * distinct stability categories: monostable and astable. A monostable
 * Timer fires only once, while an astable Timer fires continously. When
 * a Timer stabilizes, it asks the Scheduler to disassociate itself with it.
 * This request is made by returning "false" in the update() method.
 *
 * @see org.bukkit.time.AstableTimer
 * @see org.bukkit.time.MonostableTimer
 * @see org.bukkit.time.MinecraftTime
 * @see org.bukkit.time.Scheduler
 * @see org.bukkit.event.server.TimerEvent
 */
public interface Timer {
    
    /**
     * Determine whether or not the Timer has expired
     *
     * @param time A timestamp in minecraft time.
     *
     * @return True if the timer has expired based on the supplied time. 
     * Otherwise, false.
     */
    public boolean hasExpired(long time);
    
    /**
     * @param Get the time when the timer is next projected to expire.
     */
    public long getNextTarget();
    
    /**
     * @param Set the time when the target should next fire.
     */
    public void setNextTarget(long time);
    
    /**
     * Update the time.
     *
     * @warning This function should only be called internally; otherwise,
     * Really Bad Stuff (TM) could happen.
     *
     * @return True if the timer is permitted to be kept. Otherwise, false.
     *
     */
    public boolean update(long curTime);
};