package org.bukkit.plugin;

/**
 * Thrown when attempting to load an invalid Plugin file
 */
public class InvalidPluginException extends Exception {
    private static final long serialVersionUID = -8242141640709409542L;
    private final Throwable cause;
    private final Plugin plugin;

    /**
     * Constructs a new InvalidPluginException based on the given Exception
     *
     * @param throwable Exception that triggered this Exception
     */
    public InvalidPluginException(Throwable throwable) {
        cause = throwable;
        plugin = null;
    }

    /**
     * Constructs a new InvalidPluginException for the given Plugin and Exception
     *
     * Even if a plugin failed to load, a Plugin instance may already have been
     * created, and resources acquired in name of it. Throwing this exception
     * using this constructor allows a manager to release these resources.
     *
     * @param throwable Exception that triggered this Exception
     * @param plugin Plugin that caused the exception
     */
    public InvalidPluginException(Throwable throwable, Plugin plugin) {
        cause = throwable;
        this.plugin = plugin;
    }

    /**
     * Constructs a new InvalidPluginException
     */
    public InvalidPluginException() {
        cause = null;
        plugin = null;
    }

    /**
     * If applicable, returns the Exception that triggered this Exception
     *
     * @return Inner exception, or null if one does not exist
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * If applicable, returns the Plugin instance that caused the Exception
     */
    public Plugin getPlugin() {
        return plugin;
    }
}
