package org.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class TestCommandSender implements CommandSender {
    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void sendMessage(String[] messages) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isPermissionSet(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean hasPermission(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean hasPermission(Permission perm) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isOp() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
