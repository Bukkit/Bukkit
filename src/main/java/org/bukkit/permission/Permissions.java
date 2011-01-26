/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bukkit.permission;

/**
 * Represents a structure which may contain permissions, such as a profile or a user
 */
public interface Permissions {
    <T> T get(final String key);

    boolean isSet(final String key);

    void set(final String key, final Object value);
}
