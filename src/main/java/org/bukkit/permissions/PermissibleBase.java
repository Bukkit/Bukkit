package org.bukkit.permissions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

/**
 * Base Permissible for use in any Permissible object via proxy or extension
 */
public class PermissibleBase implements Permissible {
    private ServerOperator opable = null;
    private Permissible parent = this;
    private Entity entity = null;
    private final List<PermissionAttachment> attachments = new LinkedList<PermissionAttachment>();
    private final Map<String, Map<String, PermissionAttachmentInfo>> permissions = new HashMap<String, Map<String, PermissionAttachmentInfo>>();

    public PermissibleBase(ServerOperator opable) {
        this.opable = opable;

        if (opable instanceof Permissible) {
            this.parent = (Permissible)opable;
        }
        if (opable instanceof Entity) {
            this.entity = (Entity)opable;
        }

        recalculatePermissions();
    }

    private Map<String, PermissionAttachmentInfo> getPermissions(String worldName) {
        Map<String, PermissionAttachmentInfo> ret = permissions.get(worldName);
        if (ret == null) {
            ret = new HashMap<String, PermissionAttachmentInfo>();
            permissions.put(worldName, ret);
        }
        return ret;
    }
    
    public boolean isOp() {
        if (opable == null) {
            return false;
        } else {
            return opable.isOp();
        }
    }

    public void setOp(boolean value) {
        if (opable == null) {
            throw new UnsupportedOperationException("Cannot change op value as no ServerOperator is set");
        } else {
            opable.setOp(value);
        }
    }

    public boolean isPermissionSet(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }

        boolean set = isPermissionSet(name, null);
        if (entity != null) {
            set |= isPermissionSet(name, entity.getWorld().getName());
        }
        return set;
    }

    public boolean isPermissionSet(String name, String worldName) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }

        name = name.toLowerCase();
        if (worldName != null) worldName = worldName.toLowerCase();
        
        if (!permissions.containsKey(worldName)) return false;
        return getPermissions(worldName).containsKey(name);
    }

    public boolean isPermissionSet(Permission perm) {
        if (perm == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
        
        return isPermissionSet(perm.getName());
    }

    public boolean isPermissionSet(Permission perm, String worldName) {
        if (perm == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
        return isPermissionSet(perm.getName(), worldName);
    }

    public boolean hasPermission(String inName) {
        if (entity != null) {
            return hasPermission(inName, entity.getWorld().getName());
        } else {
            return hasPermission(inName, null);
        }
    }

    public boolean hasPermission(String inName, String worldName) {
        if (inName == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }

        String name = inName.toLowerCase();
        if (worldName != null) worldName = worldName.toLowerCase();

        // More specific overrides less specific
        if (isPermissionSet(name, worldName)) {
            return getPermissions(worldName).get(name).getValue();
        } else if (worldName != null && isPermissionSet(name, null)) {
            return getPermissions(null).get(name).getValue();
        } else {
            Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);

            if (perm != null) {
                return perm.getDefault().getValue(isOp());
            } else {
                return Permission.DEFAULT_PERMISSION.getValue(isOp());
            }
        }
    }

    public boolean hasPermission(Permission perm) {
        if (entity != null) {
            return hasPermission(perm, entity.getWorld().getName());
        } else {
            return hasPermission(perm, null);
        }
    }

    public boolean hasPermission(Permission perm, String worldName) {
        if (perm == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }

        if (worldName != null) worldName = worldName.toLowerCase();
        String name = perm.getName().toLowerCase();

        if (isPermissionSet(name, worldName)) {
            return getPermissions(worldName).get(name).getValue();
        } else if (worldName != null && isPermissionSet(name, null)) {
            return getPermissions(null).get(name).getValue();
        } else if (perm != null) {
            return perm.getDefault().getValue(isOp());
        } else {
            return Permission.DEFAULT_PERMISSION.getValue(isOp());
        }
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        } else if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = addAttachment(plugin);
        result.setPermission(name, value);

        recalculatePermissions();

        return result;
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, String worldName) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        } else if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = addAttachment(plugin);
        result.setPermission(name, value, worldName);

        recalculatePermissions();

        return result;
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = new PermissionAttachment(plugin, parent);

        attachments.add(result);
        recalculatePermissions();

        return result;
    }

    public void removeAttachment(PermissionAttachment attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }

        if (attachments.contains(attachment)) {
            attachments.remove(attachment);
            PermissionRemovedExecutor ex = attachment.getRemovalCallback();

            if (ex != null) {
                ex.attachmentRemoved(attachment);
            }

            recalculatePermissions();
        } else {
            throw new IllegalArgumentException("Given attachment is not part of Permissible object " + parent);
        }
    }

    public void recalculatePermissions() {
        clearPermissions();
        Set<Permission> defaults = Bukkit.getServer().getPluginManager().getDefaultPermissions(isOp());
        Bukkit.getServer().getPluginManager().subscribeToDefaultPerms(isOp(), parent);
        Map<String, PermissionAttachmentInfo> globalPerms = getPermissions(null);
        for (Permission perm : defaults) {
            String name = perm.getName().toLowerCase();
            globalPerms.put(name, new PermissionAttachmentInfo(parent, name, null, true, null));
            Bukkit.getServer().getPluginManager().subscribeToPermission(name, parent);
            calculateChildPermissions(perm.getChildren(), false, null, null);
        }

        for (PermissionAttachment attachment : attachments) {
            Map<String, Map<String, Boolean>> perms = attachment.getPermissions();
            for (Map.Entry<String, Map<String, Boolean>> entry : perms.entrySet()) {
                calculateChildPermissions(entry.getValue(), false, attachment, entry.getKey());
            }
        }
    }

    private synchronized void clearPermissions() {
        for (Map.Entry<String, Map<String, PermissionAttachmentInfo>> entry : permissions.entrySet()) {
            for (String name : entry.getValue().keySet()) {
                Bukkit.getServer().getPluginManager().unsubscribeFromPermission(name, parent);
            }
        }

        Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(false, parent);
        Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(true, parent);

        permissions.clear();
    }

    private void calculateChildPermissions(Map<String, Boolean> children, boolean invert, PermissionAttachment attachment, String worldName) {
        Set<String> keys = children.keySet();
        Map<String, PermissionAttachmentInfo> worldPerms = getPermissions(worldName);

        for (String name : keys) {
            Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);
            boolean value = children.get(name) ^ invert;
            String lname = name.toLowerCase();

            worldPerms.put(lname, new PermissionAttachmentInfo(parent, lname, attachment, value, worldName));
            Bukkit.getServer().getPluginManager().subscribeToPermission(name, parent);

            if (perm != null) {
                calculateChildPermissions(perm.getChildren(), !value, attachment, worldName);
            }
        }
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        } else if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = addAttachment(plugin, ticks);

        if (result != null) {
            result.setPermission(name, value);
        }

        return result;
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks, String worldName) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        } else if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = addAttachment(plugin, ticks);

        if (result != null) {
            result.setPermission(name, value, worldName);
        }

        return result;
    }

    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        } else if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }

        PermissionAttachment result = addAttachment(plugin);

        if (Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new RemoveAttachmentRunnable(result), ticks) == -1) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "Could not add PermissionAttachment to " + parent + " for plugin " + plugin.getDescription().getFullName() + ": Scheduler returned -1");
            result.remove();
            return null;
        } else {
            return result;
        }
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        Set<PermissionAttachmentInfo> ret = getEffectivePermissions(null);
        if (entity != null) {
            ret.addAll(getEffectivePermissions(entity.getWorld().getName()));
        }
        return ret;
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions(String worldName) {
        return new HashSet<PermissionAttachmentInfo>(getPermissions(worldName).values());
    }

    private class RemoveAttachmentRunnable implements Runnable {
        private PermissionAttachment attachment;

        public RemoveAttachmentRunnable(PermissionAttachment attachment) {
            this.attachment = attachment;
        }

        public void run() {
            attachment.remove();
        }
    }
}
