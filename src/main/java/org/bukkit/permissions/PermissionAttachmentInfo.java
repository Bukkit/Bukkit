
package org.bukkit.permissions;

/**
 * Holds information on a permission and which {@link PermissionAttachment} provides it
 */
public class PermissionAttachmentInfo {
    private final Permissible permissible;
    private final String permission;
    private final PermissionAttachment attachment;
    private final boolean value;
    private final String worldName;

    public PermissionAttachmentInfo(Permissible permissible, String permission, PermissionAttachment attachment, boolean value, String worldName) {
        if (permissible == null) {
            throw new IllegalArgumentException("Permissible may not be null");
        } else if (permission == null) {
            throw new IllegalArgumentException("Permissions may not be null");
        }

        this.permissible = permissible;
        this.permission = permission;
        this.attachment = attachment;
        this.value = value;
        this.worldName = worldName;
    }

    /**
     * Gets the permissible this is attached to
     *
     * @return Permissible this permission is for
     */
    public Permissible getPermissible() {
        return permissible;
    }

    /**
     * Gets the permission being set
     *
     * @return Name of the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Gets the attachment providing this permission. This may be null for default
     * permissions (usually parent permissions).
     *
     * @return Attachment
     */
    public PermissionAttachment getAttachment() {
        return attachment;
    }

    /**
     * Gets the value of this permission
     *
     * @return Value of the permission
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Gets the name of the world this permission applies to, or null for all worlds
     *
     * @return Name of the world this permission applies to.
     */
    public String getWorldName() {
        return worldName;
    }
}
