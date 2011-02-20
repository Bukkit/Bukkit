package org.bukkit.plugin;

/**
 * Thrown when attempting to load an invalid Plugin file
 */
public class InvalidPluginException extends Exception {
    private static final long serialVersionUID = -8242141640709409542L;
    private final Throwable cause;
    private final Plugin plugin;

    /**
     * Constructs a new InvalidPluginException for the given Plugin and Exception
     *
     * Specifying a plugin using this constructor allows the PluginManager to
     * do cleanup of any resources the plugin may have already registered.
     *
     * @param throwable Exception that triggered this Exception
     * @param plugin Plugin that caused the exception
     */
    public InvalidPluginException(Throwable throwable, Plugin plugin) {
        cause = throwable;
        this.plugin = plugin;
    }

    /**
     * Constructs a new InvalidPluginException for the given Plugin
     *
     * Specifying a plugin using this constructor allows the PluginManager to
     * do cleanup of any resources the plugin may have already registered.
     *
     * @param plugin Plugin that caused the exception
     */
    public InvalidPluginException(Plugin plugin) {
        cause = null;
        this.plugin = plugin;
    }

    /**
     * Constructs a new InvalidPluginException based on the given Exception
     *
     * The constructors that accept a Plugin instance are preferred to this
     * constructor. Only use this constructor if no cleanup is necessary.
     *
     * @param throwable Exception that triggered this Exception
     */
    public InvalidPluginException(Throwable throwable) {
        cause = throwable;
        plugin = null;
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
