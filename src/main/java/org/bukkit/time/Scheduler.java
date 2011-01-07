package org.bukkit.time;

import org.bukkit.Timer;

/**
 * An object that manages the update of a collection of org.bukkit.Timer.
 *
 * @author thylordroot
 *
 * Schedulers are objects that, in some implementation-dependent manner, update
 * a set of associated timers.  Timers update their schedule at some time greater
 * than equal to the Timer's minecraft time. 
 *
 * A Scheduler optionally supports binding identifiers to timers. If this option is
 * implemented, then a timer may have at least one identifier. A timer with no
 * identifiers bound to it is called an anonymous timer. In the absence of support
 * for identifier binding, only anonymous timerrs are supported.
 *
 * A Scheduler optionally supports prioritization of timers. Priorities are assigned
 * based on "niceness". The "nicer" a timer is, (the greater the nice value) the
 * lower priority the timer. The order in which timers of the same niceness
 * are evaluated is nondeterministic. In the absence of support for timer 
 * prioritization, their niceness is equivalent.
 */
public interface Scheduler {
	/**
	 * Register a Timer with the Scheduler
	 *
	 * This method shall add a timer to be managed by the scheduler. The
	 * scheduler henceforth has ownership of the timer, unless remove() has
	 * been called. 
	 *
	 * @param timer the Timer to add
	 * @param mustLock whether or not this operation should constitute a
	 * critical section.
	 *
	 * @post A call to this method shall be thread-safe when mustLock is true.
	 * @post A call to this method shall be exception-safe.
	 * @post Subsequent calls to add() with time shall not result in Timer being
	 * scheduled more than once.
	 *
	 */
	public void add(Timer timer, boolean mustLock);
	
	/**
	 * Unregister a Timer from the Scheduler
	 * 
	 * If the Timer is managed by this Scheduler, it is removed from the set of
	 * scheduled timers. The Scheduler henceforth releases the ownership it
	 * had over the Timer in question. 
	 *
	 * If mustLock is true, then this operation constitutes a critical section.
	 *
	 * @param timer the Timer to add
	 * @param mustLock whether or not this operation should constitute a
	 * critical section.
	 *
	 * @pre Timer must have been previously registered 
	 *
	 * @post A call to this method shall be thread-safe when mustLock is true.
	 * @post A call to this method shall be exception-safe.
	 * @post Subsequent calls to add() with time shall not result in Timer being
	 * scheduled more than once.
	 *
	 * @throws IllegalArgumentException if Timer is not currently owned by
	 * this Scheduler.
	 */
	public void remove(Timer timer, boolean mustLock);
	
	/**
	 * Forcibly update the timers managed by this Scheduler.
	 *
	 * @post A call to this method shall be thread-safe when mustLock is true.
	 * @post A call to this method shall be exception-safe.
	 * @post 
	 */
	public void poll();
	
	/**
	 * Modify the priority of timer to nice.
	 *
	 * At the implementing class's discretion, the provided timer shall have its
	 * priority set to nice.
	 *
	 * @note A valid Scheduler is permitted to ignore this operation.
	 *
	 * @post A call to this method shall be thread-safe.
	 * @post A call to this method shall be exception-safe.
	 * @post The callee shall normalize nice to be acceptable, and shall clamp
	 * the supplied niceness to acceptable bounds.
	 * @post A Scheduler that does not trivially handle renice() also has nontrivial
	 * handling of niceOf().
	 *
	 * @see niceOf()
	 *
	 */
	public void renice(Timer timer, int nice);
	
	/**
	 * Obtain the priority of timer
	 * 
	 * Calling this method shall obtain the priority of the supplied timer.
	 *
	 * @post A call to this method shall be thread-safe.
	 * @post A call to this method shall be exception-safe.
	 *
	 * @return Zero when priority is not implemented; otherwise the priority of
	 * timer.
	 */
	public int niceOf(Timer timer);
	
	/**
	 * Associate a timer with a string identifier.
	 *
	 * At the implementing class's discretion, the provided timer shall be
	 * associated with the String identifier. 
	 *
	 * @note A valid Scheduler is permitted to ignore this operation.
	 *
	 * @post A call to this method shall be thread-safe.
	 * @post A call to this method shall be exception-safe.
	 * @post A Scheduler that does not trivially handle bind() also has nontrivial
	 * handling of unbind() and lookup().
	 *
	 */
	public void bind(String identifier, Timer timer);
	
	/**
	 * Remove a previously bound identifier from identifier 
	 *
	 * @note A valid Scheduler is permitted to ignore this operation.
	 *
	 * @post A call to this method shall be thread-safe.
	 * @post A call to this method shall be exception-safe.
	 * @post A Scheduler that does not trivially handle unbind() also has nontrivial
	 * handling of bind() and lookup().
	 */
	public void unbind(String identifier);
	
	/**
	 * Find a timer associated with an id using bind().
	 *
	 * @pre A scheduler is not required to 
	 * @post A call to this method shall be thread-safe.
	 *
	 * @return The timer associated with the identifier if a binding exists. 
	 * Otherwise, null.
	 */
	public Timer lookup(String identifier);
};
