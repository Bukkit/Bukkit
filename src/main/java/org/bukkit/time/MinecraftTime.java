package org.bukkit.time;

import org.bukkit.Server;

/**
 * A thin wrapper around a Minecraft Timestamp.
 *
 * @author thylordroot
 *
 * Like the java.lang.Date class, this class is a thin wrapper around Minecraft
 * timestamp intervals. A MinecraftTime is an unsigned quantity with a range
 * of about 3.84 * 10^14 days. A timestamp of exactly zero corresponds to the
 * event, herein referred to as "Genesis", when the Minecraft clock started ticking.
 */
public class MinecraftTime implements Comparable {
	/**
	 * Constants expressing units of time used by Minecraft.
	 */
	public static enum Metric {
		/** The duration of a single Minecraft day. */
		DAY(24000),
		/** The duration of a single Minecraft hour. */
		HOUR(1000),
		/** The duration of a single Minecraft tick. */
		TICK(1);
		
		private int m_ticks;
		
		Metric(int ticks) {
			m_ticks = ticks < 0 ? 0 : ticks;
		}
		
		public int ticks() {
			return m_ticks;
		}
	};
	
	/**
	 * Constants expressing the exact time (in ticks) of certain events in 
	 * Minecraft.
	 */
	public static enum DailyEvent {
		/** The relative time when noon occurs in standard Minecraft time */
		NOON(6000),
		/** The relative time when midnight occurs in standard Minecraft time */
		MIDNIGHT(18000),
		/** The relative time when sunrise occurs in standard Minecraft time */
		SUNRISE(22800),
		/** The relative time when sunset occurs in standard Minecraft time */
		SUNSET(13200);
		
		private int m_ticks;
		
		DailyEvent(int ticks) {
			m_ticks = ticks < 0 ? 0 : ticks;
		}
		
		public int ticks() {
			return m_ticks;
		}
	};
	
	/**
	 * The actual time (in ticks) encapsulated by this MinecraftTime.
	 */
	private long m_time;
	
	/**
	 * @brief Instantiate a new time value.
	 */
	public MinecraftTime(long time) {
		assertTime(time);
		m_time = time;
	}
	
	/**
	 * Instantiate a new time value from absolute time
	 */
	public MinecraftTime(long day, int hour, int ticks) {
		assertTime(day, hour, ticks);
		m_time = day * Metric.DAY.ticks() + hour * Metric.HOUR.ticks() + ticks; 
	};
	
	/**
	 * Instantiate a new time value by using the Server's local time
	 */
	public MinecraftTime(Server server) {
		m_time = server.getTime();
	}
	
	/**
	 * Named constructor that constructs a Minecraft time relative to the start
	 * of the closest day.
	 *
	 * This method divides the curTime by the number of ticks in a day
	 * and rounds the result down. Offset is then added to this value
	 * to generate the final MinecraftTime.
	 *
	 * @note If you want a MinecraftTime relative to the day after
	 * the time calculated here, use relToNextDay()
	 *
	 * @see relToNextDay
	 * @see relToHour
	 */
	public static MinecraftTime relToDay(long curTime, long offset) {
		/* See if we were given a valid time */
		assertTime(curTime);
		
		/* Find closest day */
		curTime = curTime/Metric.DAY.ticks() * Metric.DAY.ticks();
		
		return new MinecraftTime(curTime + offset);
	}
	
	/**
	 * Named constructor that constructs a Minecraft time relative to the start
	 * of current day.
	 */
	public static MinecraftTime relToDay(Server server, long offset) {
		return relToDay(server.getTime(), offset);
	}
	
	/**
	 * Named constructor that constructs a Minecraft time relative to the start
	 * of the day after the closest day.
	 *
	 * This method is like relToDay(), except that is counts one day
	 * ahead. It can be useful when setting times that you do not want
	 * to expire immediately after being set.
	 *
	 * @note If you want a MinecraftTime relative to the day before
	 * the time calculated here, use relToDay()
	 *
	 * @see relToDay
	 * @see relToHour
	 */
	public static MinecraftTime relToNextDay(long curTime, 
		long offset) {
		/* See if we were given a valid time */
		assertTime(curTime);
		int t = Metric.DAY.ticks();
		
		/* Find closest day */
		long effectiveTime = curTime/t;
		if (curTime % t > 0)
			effectiveTime++;
		
		effectiveTime *= t;
		
		return new MinecraftTime(effectiveTime + offset);
	}
	
	/**
	 * Named constructor that constructs a Minecraft time relative to the start
	 * of the day after the closest day.
	 */
	public static MinecraftTime relToNextDay(Server server, 
		long offset) {
		return relToNextDay(server.getTime(), offset);
	}	
	
	/**
	 * Named constructor that instantiates a MinecraftTime relative to the start
	 * of the closest hour.
	This method divides the curTime by the number of ticks in an hour
	 * and rounds the result down. Offset is then added to this value
	 * to generate the final MinecraftTime.
	 *
	 * @note If you want a MinecraftTime relative to the day after
	 * the time calculated here, use relToNextHour()
	 *
	 * @see relToNextHour
	 * @see relToDay
	 */
	public static MinecraftTime relToHour(long curTime, long offset) {
		/* See if we were given a valid time */
		assertTime(curTime);
		
		/* Find closest day */
		curTime = curTime/Metric.HOUR.ticks() * Metric.HOUR.ticks();
		
		return new MinecraftTime(curTime + offset);
	}
	
	/**
	 * Named constructor that instantiates a MinecraftTime relative to the start
	 * of the current hour.
	 */
	public static MinecraftTime relToHour(Server server, long offset) {
		return relToHour(server.getTime(), offset);
	}
	
	/**
	 * Named constructor that constructs a Minecraft time relative to the start
	 * of start of hour the following the current one.
	 *
	 * This method is like relToHour(), except that is counts one hour
	 * ahead. It can be useful when setting times that you do not want
	 * to expire immediately after being set.
	 *
	 * @note If you want a MinecraftTime relative to the hour before
	 * the time calculated here, use relToHour()
	 *
	 * @see relToDay
	 * @see relToDay
	 */
	public static MinecraftTime relToNextHour(long curTime, 
		long offset) {
		/* See if we were given a valid time */
		assertTime(curTime);
		int t = Metric.HOUR.ticks();
		
		/* Find closest day */
		long effectiveTime = curTime/t;
		if (curTime % t > 0)
			effectiveTime++;
		
		effectiveTime *= t;
		
		return new MinecraftTime(effectiveTime + offset);
	}
	
	/**
	 * Named constructor that instantiates a MinecraftTime relative to
	 * the start of the hour following the current one.
	 */
	public static MinecraftTime relToNextHour(Server server, 
		long offset) {
		return relToNextHour(server.getTime(), offset);
	}
	
	/**
	 * Assert the correctness of a timestamp
	 *
	 * @throws IllegalArgumentException when time < 0
	 */
	public static void assertTime(long time) {
		if (time < 0)
			throw new IllegalArgumentException("Invalid timestamp: " +
				time);
	}
	
	/**
	 * Assert the correctness of an absolute time
	 *
	 * @throws IllegalArgumentException when day < 0
	 * @throws IllegalArgumentException when hour not in [0, 23]
	 * @throws IllegalArgumentException when ticks not in [0, 999]
	 */
	public static void assertTime(long day, int hour, int ticks) {
		if (day < 0)
			throw new IllegalArgumentException("Invalid day value: " +
				day);
		else if (hour < 0 || hour > 23) {
			throw new IllegalArgumentException("Invalid hour value: " +
				hour);
		}
		else if (ticks < 0 && ticks > 999) {
			throw new IllegalArgumentException("Invalid ticks value: " +
				ticks);
		}
	}
	
	/**
	 * Obtain the number of ticks from this MinecraftTime that have ellapsed 
	 * since the Genesis.
	 */
	public long ticksSinceGenesis() {
		return m_time;
	}
	
	/**
	 * Obtain the number of days from this MinecraftTime that have ellapsed 
	 * since Genesis.
	 */
	public long daysSinceGenesis() {
		return m_time/Metric.DAY.ticks();
	}
	
	/**
	 * Obtain the number of hours from this MinecraftTime that have currently 
	 * ellapsed since Genesis.
	 *
	 * @note This function does not adjust for the relative time of noon.
	 */
	public long hoursSinceGenesis() {
		return m_time/Metric.HOUR.ticks();
	}
	
	/**
	 * Obtain the current hour in the day.
	 *
	 * @post The value will be bounded in the interval [0, 23].
	 */
	public int hourOfDay() {
		return (int) (m_time/Metric.HOUR.ticks()) % 24;
	}
	
	/**
	 * Obtain the current number of ticks ellapsed in the given hour.
	 * 
	 * @post The value will be bounded in the interval [0, 999]
	 */
	public int ticksOfHour() {
		return (int) (m_time % Metric.HOUR.ticks());
	}
	
	/**
	 * @brief Convert a MinecraftTime to a string value.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		/* Build the time string */
		s.append(daysSinceGenesis());
		s.append(':');
		s.append(hourOfDay());
		s.append(':');
		s.append(ticksOfHour());
		
		return s.toString();
	}
	
	/* Methods inherited from Comparable */
	
	public int compareTo(Object o) {
		long rhs;
		/* Figure out what kind of object we got */
		if (o instanceof MinecraftTime) {
			rhs = ((MinecraftTime) o).m_time;
		}
		else if (o instanceof Long) {
			rhs = (Long) o;
		}
		else {
			return -1;
		}
		
		return (int) (m_time - rhs);
	}
}