package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

/**
 * Provide a way to register metadata providers and manage the metadata system.
 */
public interface MetadataManager {
    /**
     * Register a metadata provider.
     * @param clazz The implementation class (scope) of this provider.
     * @param plugin the plugin managing this.
     * @param provider An appropriate generic provider.
     */
    public <T extends Metadatable> void registerProvider(Class<T> clazz, Plugin plugin, MetadataProvider<T> provider);

    /**
     * Unregister a metadata provider.
     * @param clazz The implementation class(scope).
     * @param plugin the plugin unregistering this provider.
     */
    public <T extends Metadatable> void unregisterProvider(Class<T> clazz, Plugin plugin);

    /**
     * Clear all data belonging to a plugin.
     * <p>
     * This includes removing any registered providers as well as any metadata
     * associated with this plugin.
     *
     * @param plugin the plugin whose data which we want to remove.
     */
    public void clearPluginData(Plugin plugin);
}
