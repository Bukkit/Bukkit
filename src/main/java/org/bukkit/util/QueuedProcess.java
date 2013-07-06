package org.bukkit.util;


/**
 * To be used when you wish an object to be queued for loading at a later time
 */
public interface QueuedProcess<T> {
    /**
     * Called when the object requested has been fetched
     *
     * @param object The object which was queued to be fetched
     */
    public void accept(T object);
    
    /**
     * Checks if the process should be cancelled.
     * Implementations of this method should return false if the
     * queued process object becomes invalid or you no longer
     * want to receive the object
     *
     * @return True if the process is cancelled
     */
    public boolean isCancelled();
}
