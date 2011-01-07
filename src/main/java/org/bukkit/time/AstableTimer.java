package org.bukkit.time;

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
	 * Increase the timer by its period.
	 */
	public boolean update(long time) {
		offsetTarget(m_period);
		return true;
	}
};