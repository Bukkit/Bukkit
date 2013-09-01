package org.bukkit.permissions;

/**
 * Represents a plugin which handles permission checks for the server.
 *
 * This class should only be used if a plugin is explicitly granting
 * or revoking access to permission nodes. It shouldn't be used for
 * just "checking" permissions.
 *
 * The purpose of this class is to make life easier for permission
 * plugin developers. Instead of using internals-breaking reflection
 * or other hacky code to achieve their purposes, developers can just
 * make their plugin hook into this class.
 */
public interface PermissionManager {

    /**
     * Used to check a permission.
     * @param user The Permissible user to check the permission of.
     * @param permission The permission to check for.
     * @return Whether or not the specified Permissible has the given permission.
     */
    boolean checkPermission(Permissible user, Permission permission);

}