package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;

/**
 * The LazyMetadataValue class implements a type of metadata that is not computed until another plugin asks for it.
 * By making metadata values lazy, no computation is done by the providing plugin until absolutely necessary (if ever).
 * Additionally, LazyMetadataValue objects cache their values internally unless overridden by a {@link CacheStrategy}
 * or invalidated at the individual or plugin level. Once invalidated, the LazyMetadataValue will recompute its value
 * when asked.
 */
public class LazyMetadataValue implements MetadataValue {
    private Callable<Object> lazyValue;
    private CacheStrategy cacheStrategy;
    private String internalValue;
    private boolean internalValueEvaluated;
    private Plugin owningPlugin;

    /**
     * Initialized a LazyMetadataValue object with the default CACHE_AFTER_FIRST_EVAL cache strategy.
     * @param owningPlugin
     * @param lazyValue
     */
    public LazyMetadataValue(Plugin owningPlugin, Callable<Object> lazyValue) {
        this(owningPlugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, lazyValue);
    }

    /**
     * Initializes a LazyMetadataValue object with a specific cache strategy.
     * @param owningPlugin
     * @param cacheStrategy
     * @param lazyValue
     */
    public LazyMetadataValue(Plugin owningPlugin, CacheStrategy cacheStrategy, Callable<Object> lazyValue) {
        this.lazyValue = lazyValue;
        this.owningPlugin = owningPlugin;
        this.cacheStrategy = cacheStrategy;
        internalValueEvaluated = false;
    }

    /**
     * Converts the metadata value into an int and returns it.
     * @return
     * @throws MetadataConversionException Thrown if the value cannot be converted to an int. Ex: String => int
     */
    public int asInt() throws MetadataConversionException {
        try {
            eval();
            return Integer.parseInt(internalValue);
        } catch (NumberFormatException e) {
            throw new MetadataConversionException("Could not convert metadata value of " + internalValue + " to type int.");
        }
    }

    /**
     * Converts the metadata value into a double and returns it.
     * @return
     * @throws MetadataConversionException Thrown if the value cannot be converted to a double. Ex: String => double
     */
    public double asDouble() throws MetadataConversionException {
        try {
            eval();
            return Double.parseDouble(internalValue);
        } catch (NumberFormatException e) {
            throw new MetadataConversionException("Could not convert metadata value of " + internalValue + " to type double.");
        }
    }

     /**
     * Converts the metadata value into a boolean and returns it.
     * @return
     * @throws MetadataConversionException Thrown if the value cannot be converted to a double. Ex: String => double
     */
    public boolean asBoolean() throws MetadataConversionException {
        try {
            eval();
            return internalValue != null && (internalValue.equalsIgnoreCase("true") || internalValue.equalsIgnoreCase("1") || internalValue.equalsIgnoreCase("1.0"));
        } catch (NumberFormatException e) {
            throw new MetadataConversionException("Could not convert metadata value of " + internalValue + " to type double.");
        }
    }

    /**
     * Returns the metadata value as a string. This method will always succeed.
     * @return
     */
    public String asString() {
        eval();
        return internalValue;
    }

    /**
     * Returns the {@link Plugin} that created this metadata item.
     * @return
     */
    public Plugin getOwningPlugin() {
        return owningPlugin;
    }

    /**
     * Lazially evaluates the value of this metadata item.
     * @throws MetadataEvaluationException
     */
    private synchronized void eval() throws MetadataEvaluationException {
        if(cacheStrategy == CacheStrategy.NEVER_CACHE || !internalValueEvaluated) {
            try {
                internalValue = lazyValue.call().toString();
                internalValueEvaluated = true;
            } catch (Exception e) {
                throw new MetadataEvaluationException(e);
            }
        }
    }

    /**
     * Invalidates this metadata item's value. The next time the value is requested it will be recomputed.
     */
    public synchronized void invalidate() {
        if(cacheStrategy != CacheStrategy.CACHE_ETERNALLY) {
            internalValueEvaluated = false;
        }
    }

    public enum CacheStrategy {
        CACHE_AFTER_FIRST_EVAL,
        NEVER_CACHE,
        CACHE_ETERNALLY
    }
}
