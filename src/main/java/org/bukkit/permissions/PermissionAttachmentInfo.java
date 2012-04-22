package org.bukkit.permissions;

/**
 * Holds information on a permission and which {@link PermissionAttachment} provides it
 */
public class PermissionAttachmentInfo {
    private final Permissible permissible;
    private final String permission;
    private final PermissionAttachment attachment;
    private final boolean value;

    public PermissionAttachmentInfo(Permissible permissible, String permission, PermissionAttachment attachment, boolean value) {
        if (permissible == null) {
            throw new IllegalArgumentException("Permissible may not be null");
        } else if (permission == null) {
            throw new IllegalArgumentException("Permissions may not be null");
        }

        this.permissible = permissible;
        this.permission = permission;
        this.attachment = attachment;
        this.value = value;
    }

    /**
     * Gets the exact permission being set
     * 
     * @return
     */
    public String getExactPermission() {
    	// This will return the permission in its original case
    	return permission;
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
    	// Lower cased so it works like the old code
        return permission.toLowerCase();
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
}
