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
}
