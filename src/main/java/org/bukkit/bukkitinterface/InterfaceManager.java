package org.bukkit.bukkitinterface;

import java.util.List;

/**
 * Handles all plugin management from the Server
 */
public interface InterfaceManager {

    /**
     * Registers the specified handler to respond to calls to the CustomInterface
     *
     * @param customInterface CustomInterface to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid PluginLoader
     */
    public void registerInterface(CustomInterface customInterface) throws IllegalArgumentException;

    /**
     * Unregisters the specified handler from responding to calls to the CustomInterface
     *
     * @param customInterface CustomInterface to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid CustomInterface
     */
    public void unRegisterInterface(CustomInterface customInterface) throws IllegalArgumentException;

    /**
     * Returns a list of handlers registered to use the CustomInterface
     *
     * Please note that the name of the interface is case-sensitive
     *
     * @param name Name of the custom interface to check
     * @return List of handlers if any have been registered, otherwise null
     */
    public List<CustomInterface> getInterfaces(String name);

    /**
     * Returns the handler registered to use the CustomInterface
     *
     * Please note that the name of the interface is case-sensitive
     *
     * @param name Name of the custom interface to check
     * @return if exactly one handler has been registered for this interface, return the handler, otherwise null
     */
    public CustomInterface getInterface(String name);

    /**
     * Clears all stored interfaces
     */
    public void clearInterfaces();

}
