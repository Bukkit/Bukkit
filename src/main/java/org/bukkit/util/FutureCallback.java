package org.bukkit.util;

import org.bukkit.plugin.Plugin;

/**
 * Provided when an object is requested and will be fetched at an unknown time
 * 
 * Objects returned from such methods do not need to be kept unless you wish
 * to cancel the callback for any reason.
 */
public interface FutureCallback {

    /**
     * Fetches the plugin which this callback belongs to
     * @return A plugin
     */
    public Plugin getPlugin();

    /**
     * Fetches the runnable which will be called when this task is finished
     * @return A runnable object
     */
    public Runnable getRunnable();

    /**
     * Checks if the runnable will be called once the callback is complete
     * @return true if canceled
     */
    public boolean isCancelled();

    /**
     * Sets whether or not the runnable will be called once the callback
     * completes
     * @param isCanceled True to cancel the callback
     */
    public void setCancelled(boolean isCanceled);

    /**
     * Checks if the callback has been completed
     * @return True if completed
     */
    public boolean isFinished();

    /**
     * Returns true if:
     * <li>The object is the same callback</li>
     * <li>The object is the runnable which this callback holds</li>
     * @return
     */
    @Override
    public boolean equals(Object obj);
}
