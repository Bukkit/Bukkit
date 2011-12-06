
package org.bukkit.permissions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;

/**
 * Holds information about a permission attachment on a {@link Permissible} object
 */
public class PermissionAttachment {
    private PermissionRemovedExecutor removed;
    private final Map<String, Map<String, Boolean>> permissions = new HashMap<String, Map<String, Boolean>>();
    private final Permissible permissible;
    private final Plugin plugin;

    public PermissionAttachment(Plugin plugin, Permissible Permissible) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        this.permissible = Permissible;
        this.plugin = plugin;
    }

    /**
     * Gets the plugin responsible for this attachment
     *
     * @return Plugin responsible for this permission attachment
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Sets an object to be called for when this attachment is removed from a {@link Permissible}. May be null.
     *
     * @param ex Object to be called when this is removed
     */
    public void setRemovalCallback(PermissionRemovedExecutor ex) {
        removed = ex;
    }

    /**
     * Gets the class that was previously set to be called when this attachment was removed from a {@link Permissible}. May be null.
     *
     * @return Object to be called when this is removed
     */
    public PermissionRemovedExecutor getRemovalCallback() {
        return removed;
    }

    /**
     * Gets the Permissible that this is attached to
     *
     * @return Permissible containing this attachment
     */
    public Permissible getPermissible() {
        return permissible;
    }

    /**
     * Gets a copy of all set permissions and values contained within this attachment.
     *
     * This map may be modified but will not affect the attachment, as it is a copy.
     *
     * @return Copy of all permissions and values expressed by this attachment
     */
    public Map<String, Map<String, Boolean>> getPermissions() {
        return new HashMap<String, Map<String, Boolean>>(permissions);
    }

    /**
     * Returns the permissions information
     *
     * This map may be modified and will affect the attachment.
     *
     * @param worldName The world to retrieve values for
     * @return All permissions and values expressed for a world by this attachment
     */
    private Map<String, Boolean> getPermissions(String worldName) {
        if (worldName != null) worldName = worldName.toLowerCase();
        Map<String, Boolean> ret = permissions.get(worldName);
        if (ret == null) {
            ret = new LinkedHashMap<String, Boolean>();
            permissions.put(worldName, ret);
        }
        return ret;
    }

    /**
     * Sets a permission to the given value, by its fully qualified name
     *
     * @param name Name of the permission
     * @param value New value of the permission
     */
    public void setPermission(String name, boolean value) {
        setPermission(name.toLowerCase(), value, null);
    }

    /**
     * Sets a permission to the given value, by its fully qualified name
     *
     * @param name Name of the permission
     * @param value New value of the permission
     * @param worldName World this permission applies for, or null for global
     */
    public void setPermission(String name, boolean value, String worldName) {
        getPermissions(worldName).put(name.toLowerCase(), value);
        permissible.recalculatePermissions();
    }

    /**
     * Sets a permission to the given value
     *
     * @param perm Permission to set
     * @param value New value of the permission
     */
    public void setPermission(Permission perm, boolean value) {
        setPermission(perm.getName(), value);
    }

    /**
     * Sets a permission to the given value
     *
     * @param perm Permission to set
     * @param value New value of the permission
     */
    public void setPermission(Permission perm, boolean value, String worldName) {
        setPermission(perm.getName(), value, worldName);
    }

    /**
     * Removes the specified permission from this attachment.
     *
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param name Name of the permission to remove
     */
    public void unsetPermission(String name) {
        unsetPermission(name, null);
    }

    /**
     * Removes the specified permission from this attachment.
     *
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param name Name of the permission to remove
     */
    public void unsetPermission(String name, String worldName) {
        getPermissions(worldName).remove(name.toLowerCase());
        permissible.recalculatePermissions();
    }

    /**
     * Removes the specified permission from this attachment in the global namespace.
     *
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param perm Permission to remove
     */
    public void unsetPermission(Permission perm) {
        unsetPermission(perm.getName());
    }

    /**
     * Removes the specified permission from this attachment in the global namespace.
     *
     * If the permission does not exist in this attachment, nothing will happen.
     *
     * @param perm Permission to remove
     */
    public void unsetPermission(Permission perm, String worldName) {
        unsetPermission(perm.getName(), worldName);
    }

    /**
     * Removes this attachment from its registered {@link Permissible}
     *
     * @return true if the permissible was removed successfully, false if it did not exist
     */
    public boolean remove() {
        try {
            permissible.removeAttachment(this);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
