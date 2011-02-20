package org.bukkit.plugin;

/**
 * Thrown when a plugin requests to inhibit loading or unloading.
 */
public final class PluginInhibitException extends Exception {
    private static final long serialVersionUID = 3857877689996365765L;
    private final Plugin plugin;

    /**
     * Constructor with associated plugin.
     *
     * Specifying a plugin using this constructor allows the PluginManager to
     * do cleanup of any resources the plugin may have already registered.
     *
     * @param plugin The plugin that requested inhibition
     */
    public PluginInhibitException(Plugin plugin) {
        super();
        this.plugin = plugin;
    }

    /**
     * Default constructor.
     *
     * The preferred constructor is
     * {@link PluginInhibitException#PluginInhibitException(Plugin)}.
     * This constructor is only to be used if no cleanup is necessary.
     */
    public PluginInhibitException() {
        super();
        this.plugin = null;
    }

    /**
     * Get the plugin that requested inhibition.
     *
     * @return A Plugin instance
     */
    public Plugin getPlugin() {
        return plugin;
    }
}
