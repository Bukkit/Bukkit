package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;

/**
 * A FixedMetadataValue is a special case metadata item that contains the same value forever after initialization.
 * Invalidating a FixedMetadataValue has no affect.
 */
public class FixedMetadataValue extends LazyMetadataValue{
    /**
     * Initializes a FixedMetadataValue as an int
     * @param owningPlugin
     * @param value
     */
    public FixedMetadataValue(Plugin owningPlugin, final int value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a boolean
     * @param owningPlugin
     * @param value
     */
    public FixedMetadataValue(Plugin owningPlugin, final boolean value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a double
     * @param owningPlugin
     * @param value
     */
    public FixedMetadataValue(Plugin owningPlugin, final double value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a string
     * @param owningPlugin
     * @param value
     */
    public FixedMetadataValue(Plugin owningPlugin, final String value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }
}
