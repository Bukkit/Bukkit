package org.bukkit.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Log class provides a simple way to open log files and write to them
 * with a date/time stamp and various levels of urgency. Log can write to
 * either stdout, or to a file, and if you write to files.
 * <p>
 * Log will automatically maintain log files for you by keeping the file size 
 * and number of files under a configurable amount. Be aware that Log only does
 * a stat() when it opens the file, so if something else is writing to the file
 * it will not rotate it. Log needs to be the exclusive writer to the log.
 * <p>
 * The class allows you to globally set a debug flag on or off, which will cause 
 * messages logged with debug() to be written by all instances of the class
 * <p>
 * Log has no external dependencies, so is a simple drop-in replacement for using
 * System.out.println().
 * 
 * @author matjam
 *
 */

public class Log {

	private static boolean debugging = false; 
	
	private String dir;
	private File file;
	private long max_size;
	private long current_size;
	private int max_count;
	
	private final Lock rotateLock = new ReentrantLock();
	
	/* out defaults to stdout. The reason we set it here is so that we can
	 * print to stdout later on if something goes wrong. 
	 * 
	 * It would also be nice to offer Syslog functionality, but thats really
	 * not terribly needed at this point.
	 */
	
	private PrintStream out = new PrintStream(System.out, true); 
		
	/**
	 * Constructor for Log allowing you to specify all of the possible configuration.
	 * 
	 * @param file 			the file name only, not the full path.
	 * @param dir			the directory the log file will be written to.
	 * @param max_size		maximum size to write before rotating the file.
	 * @param max_count		maximum number of archive logs to keep.
	 */
	
	public Log (String file, String dir, long max_size, int max_count) {
		if (dir != null) {
			this.dir = dir;
			this.file = new File(dir + File.separator + file);
			this.max_size = max_size;
			this.max_count = max_count;
			
			try {
				// Make sure the log directory exists first.
				
				File log_dir = new File(this.dir);
				
				if (!log_dir.exists())
					log_dir.mkdirs();
				
				// Open the printstream to the directory.
				
				out = new PrintStream(new FileOutputStream(this.file, true), true);
				
				current_size = this.file.length();
			} catch (IOException e) {
				// Even if this fails, you can still print to the Log. The output
				// will just end up in stdout.
				
				critical("Unable to open new PrintStream for %s/%s", dir, file);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Constructor for Log allowing you to specify file, dir and max_size.
	 * <p>
	 * Count of logs is defaulted to 5.
	 * 
	 * @param file 			the file name only, not the full path.
	 * @param dir			the directory the log file will be written to.
	 * @param max_size		maximum size to write before rotating the file.
	 */
	
	public Log(String file, String dir, long max_size) {
		this(file, dir, max_size, 5);
	}
	
	/**
	 * Constructor for Log allowing you to specify file and dir.
	 * <p>
	 * max_count is defaulted to 5, max_size defaults to 1048576 bytes (1MB).
	 * 
	 * @param file 			the file name only, not the full path.
	 * @param dir			the directory the log file will be written to.
	 */	
	
	public Log(String file, String dir) {
		this(file, dir, 1048576);
	}
	
	/**
	 * Constructor for Log allowing you to specify file.
	 * <p>
	 * max_count is defaulted to 5, max_size defaults to 1048576 bytes (1MB),
	 * and dir defaults to "logs".
	 * 
	 * @param file 			the file name only, not the full path.
	 */	

	public Log(String file) {
		this(file, "logs");
	}

	/**
	 * Default constructor for Log.
	 * <p>
	 * Does not create a log file, rather it provides and interface to stdout.
	 * This implies that if your stdout is to a file, you will need to manage
	 * that yourself.
	 */
	
	public Log() {
		// do nothing; this allows consumers to just log to stdout if they wish.

		dir = null;
		file = null;
	}
	
	/**
	 * Enables or disables debugging globally for all Log instances.
	 * 
	 * @param debugging 	true or false
	 */
	
	public static void setDebugging (boolean debugging) {
		Log.debugging = debugging;
	}
	
	/**
	 * Returns the current state of the debugging flag.
	 * 
	 * @return boolean
	 */
	public static boolean isDebugging () {
		return Log.debugging;
	}
	
	/** 
	 * The meat of this class, provides all of the log file rotation logic and
	 * output formatting.
	 * 
	 * @param urgency		a string such as "NOTICE"
	 * @param format		a standard String format.
	 * @param args			arguments for the String format.
	 */
	
	private void println (String urgency, String format, Object...args) {
		// Utility method to print the provided line to "out" with a date stamp and a
		// given urgency. Because we use printf(), we need to call println() to end the
		// line.
		
		String line = String.format(format, args);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		
		String finished_line = String.format("%s [%s] %s", date, urgency, line);
		
		out.println(finished_line);
		
		// Multiple threads my update this, thats ok because any race condition
		// would only affect accuracy; some files might end up being a little
		// smaller or a little larger than necessary. I think that might be pretty
		// rare case in reality.
		
		current_size += finished_line.getBytes().length;						
		
		// The next part of the method only applies to actual files, not stdout.
		
		if (file == null)
			return;
	
		// If the current file is over the max_size, we need to rename logfiles and possibly delete an
		// old one.
		
		if (current_size > max_size) {
			
			// We need to rotate the file, but only one thread should be doing this at a time.
			// So, we try to lock it, and if we fail, we immediately bail, as another thread
			// is already doing the file rotations.
			
			if (!rotateLock.tryLock())
				return;
			
			try {
			
				// Remove the last file if we have one.
				
				File old_log = new File(dir + File.separator + file.getName() + "." + max_count);
				
				if (old_log.exists())
					old_log.delete();
				
				// Rename each log file we find to the next number up.
				
				for (int i = max_count; i > 0; i--) {
					old_log = new File(dir + File.separator + file.getName() + "." + i);
					
					if (old_log.exists()) {
						old_log.renameTo(new File(dir + File.separator + file.getName() + "." + (i + 1)));
					}
				}
				
				// Rename the current log file and reopen the new one.
				
				String filename = file.getName();
				File new_file = new File(dir + File.separator + filename + "." + 1);
	
				out.close();
				current_size = 0;
				out = new PrintStream(System.out, true);
	
				System.out.printf("%s [INFO] Rotating log file: %s", date, file.getAbsoluteFile());
				System.out.println();
				
				if (!file.renameTo(new_file)) {
					System.out.printf("%s [WARNING] Unable to rotate log file: %s", date, file.getAbsoluteFile());
					System.out.println();
				}
				
				// Reopen the logfile.
				
				file = new File(dir + File.separator + filename);
				
				try {
					// Open the printstream to the file.
					
					out = new PrintStream(new FileOutputStream(file, true), true);				
				} catch (IOException e) {
					// Even if this fails, you can still print to the Log. The output
					// will just end up in stdout.
					
					System.out.printf("%s [CRITICAL] Unable to open new PrintStream for: %s", date, file.getAbsoluteFile());
					System.out.println();
					
					e.printStackTrace();
				}
			} finally {
				
				// Make sure that we unlock the rotateLock no matter what.
				
				rotateLock.unlock();
			}
		}
	}
	
	// Methods that consumers call to write messages to stdout or to
	// a log file.
	
	/**
	 * Writes a line to the log file or stdout with the DEBUG severity,
	 * only if debugging is enabled.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */
	
	public void debug (String format, Object...args) {
		// This one only gets printed if we're debugging.
		
		if (debugging)
			println("DEBUG", format, args);
	}
	
	/**
	 * Writes a line to the log file or stdout with the INFO severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void info (String format, Object...args) {
		println("INFO", format, args);
	}
	
	/**
	 * Writes a line to the log file or stdout with the NOTICE severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void notice (String format, Object...args) {
		println("NOTICE", format, args);		
	}
	
	/**
	 * Writes a line to the log file or stdout with the WARNING severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void warning (String format, Object...args) {
		println("WARNING", format, args);
	}
	
	/**
	 * Writes a line to the log file or stdout with the ERROR severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void error (String format, Object...args) {
		println("ERROR", format, args);	
	}
	
	/**
	 * Writes a line to the log file or stdout with the CRITICAL severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void critical (String format, Object...args) {
		println("CRITICAL", format, args);
	}
	
	/**
	 * Writes a line to the log file or stdout with the FATAL severity.
	 * 
	 * @param format	standard String format
	 * @param args		args for format
	 */

	public void fatal (String format, Object...args) {
		println("FATAL", format, args);
	}
}

