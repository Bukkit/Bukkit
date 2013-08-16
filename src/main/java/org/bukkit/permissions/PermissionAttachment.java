package org.bukkit.permissions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.plugin.Plugin;

/**
 * Holds information about a permission attachment on a {@link Permissible}
 * object
 */
public class PermissionAttachment {

    private PermissionRemovedExecutor removed;
    private final Map<String, Boolean> permissions = new LinkedHashMap<String, Boolean>();
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
     * Sets an object to be called for when this attachment is removed from a
     * {@link Permissible}. May be null.
     *
     * @param ex Object to be called when this is removed
     */
    public void setRemovalCallback(PermissionRemovedExecutor ex) {
        removed = ex;
    }

    /**
     * Gets the class that was previously set to be called when this attachment
     * was removed from a {@link Permissible}. May be null.
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
     * Gets a copy of all set permissions and values contained within this
     * attachment.
     * <p>
     * This map may be modified but will not affect the attachment, as it is a
     * copy.
     *
     * @return Copy of all permissions and values expressed by this attachment
     */
    public Map<String, Boolean> getPermissions() {
        return new LinkedHashMap<String, Boolean>(permissions);
    }

    /**
     * Sets a permission to the given value, by its fully qualified name
     * <p>
     * This method will recalculate permissions on the attached
     * {@link Permissible}. If multiple permissions need to be set,
     * {@link #setPermissions(java.util.Map)} or
     * {@link #resetPermissions(java.util.Map)} are preferred as the
     * recalculation is only performed once.
     *
     * @param name Name of the permission
     * @param value New value of the permission
     */
    public void setPermission(String name, boolean value) {
        permissions.put(name.toLowerCase(), value);
        permissible.recalculatePermissions();
    }

    /**
     * Sets a permission to the given value
     * <p>
     * This method will recalculate permissions on the attached
     * {@link Permissible}. If multiple permissions need to be set,
     * {@link #setPermissions(java.util.Map)} or
     * {@link #resetPermissions(java.util.Map)} are preferred as the
     * recalculation is only performed once.
     *
     * @param perm Permission to set
     * @param value New value of the permission
     */
    public void setPermission(Permission perm, boolean value) {
        setPermission(perm.getName(), value);
    }

    /**
     * Adds/changes all the permissions in the supplied map.
     * <p>
     * As the {@link Permissible}'s net permissions are only recalculated once,
     * this is faster than calling
     * {@link #setPermission(java.lang.String, boolean)} repeatedly.
     *
     * @param permissions The permissions and values to be added or altered
     */
    public void setPermissions(Map<String, Boolean> permissions) {
        for (Entry<String, Boolean> entry : permissions.entrySet()) {
            this.permissions.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Sets permissions to the ones in the supplied map, erasing all previously
     * set permissions in this attachment.
     * <p>
     * As the {@link Permissible}'s net permissions are only recalculated once,
     * this is faster than removing the existing attachment and calling
     * {@link #setPermission(java.lang.String, boolean)} repeatedly.
     *
     * @param permissions The permissions and values to be set
     */
    public void resetPermissions(Map<String, Boolean> permissions) {
        this.permissions.clear();
        for (Entry<String, Boolean> entry : permissions.entrySet()) {
            this.permissions.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Removes the specified permission from this attachment.
     * <p>
     * If the permission does not exist in this attachment, nothing will happen.
     * <p>
     * This method will recalculate permissions on the attached
     * {@link Permissible}. If multiple permissions need to be unset,
     * {@link #unsetPermissions(java.util.List)} is preferred as the
     * recalculation is only performed once.
     *
     * @param name Name of the permission to remove
     */
    public void unsetPermission(String name) {
        permissions.remove(name.toLowerCase());
        permissible.recalculatePermissions();
    }

    /**
     * Removes the specified permission from this attachment.
     * <p>
     * If the permission does not exist in this attachment, nothing will happen.
     * <p>
     * This method will recalculate permissions on the attached
     * {@link Permissible}. If multiple permissions need to be unset,
     * {@link #unsetPermissions(java.util.List)} is preferred as the
     * recalculation is only performed once.
     *
     * @param perm Permission to remove
     */
    public void unsetPermission(Permission perm) {
        unsetPermission(perm.getName());
    }

    /**
     * Removes the specified permissions from this attachment.
     * <p>
     * As the {@link Permissible}'s net permissions are only recalculated once,
     * this is faster than calling {@link #unsetPermission(String)} repeatedly.
     *
     * @param permissions The permissions to be removed. Non-existent
     * permissions will be ignored.
     */
    public void unsetPermissions(List<String> permissions) {
        for (String name : permissions) {
            this.permissions.remove(name.toLowerCase());
        }
        permissible.recalculatePermissions();
    }

    /**
     * Removes this attachment from its registered {@link Permissible}
     *
     * @return true if the permissible was removed successfully, false if it did
     * not exist
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
