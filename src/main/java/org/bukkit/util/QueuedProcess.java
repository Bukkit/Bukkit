package org.bukkit.util;

/**
 * A process to be called later which takes an argument. Typically, the
 * provided object will be created by an asynchronous procedure, and the
 * QueuedProcess will be ran when the object is ready for use.
 */
public interface QueuedProcess<T> {
    /**
     * Called when the object requested has been fetched
     *
     * @param object The object which was queued to be fetched
     */
    public void accept(T object);
}
