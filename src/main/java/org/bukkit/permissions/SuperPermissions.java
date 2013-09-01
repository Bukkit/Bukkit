package org.bukkit.permissions;

/**
 * Simple integrated permissions checker.
 */
public class SuperPermissions extends PermissionManager {

    public boolean checkPermission(Permissible permissible, Permission permission) {
        return permission.getDefault().getValue(permissible.isOp());
    }

}