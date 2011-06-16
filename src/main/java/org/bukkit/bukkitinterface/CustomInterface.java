package org.bukkit.bukkitinterface;

/**
 * Represents a CustomInterface
 */
public interface CustomInterface {

   /**
     * Gets the name of the custom interface.  All implementations of the same custom interface should use the same name.
     *
     * @return Name of the custom interface
     */
    public String getName();

   /**
     * Gets the implementation name of the custom interface.  These names should be unique for each implementation.
     *
     * @return Name of the custom interface
     */
    public String getImplementationName();

   /**
     * Returns true if the interface is exclusive.  Exclusive custom interfaces can only have one handler registered.
     *
     * @return returns true if the interface is exclusive
     */
    public boolean isExclusive();

}
