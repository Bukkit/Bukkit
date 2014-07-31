package org.bukkit;

import java.util.Date;

/**
 * A single entry from a ban list. This may represent either a player ban or
 * an IP ban.
 * <p>
 * Ban entries include the following properties:
 * <table border=1>
 * <tr>
 *     <th>Property</th>
 *     <th>Description</th>
 * </tr><tr>
 *     <td>Target Name / IP Address</td>
 *     <td>The target name or IP address</td>
 * </tr><tr>
 *     <td>Creation Date</td>
 *     <td>The creation date of the ban</td>
 * </tr><tr>
 *     <td>Source</td>
 *     <td>The source of the ban, such as a player, console, plugin, etc</td>
 * </tr><tr>
 *     <td>Expiration Date</td>
 *     <td>The expiration date of the ban</td>
 * </tr><tr>
 *     <td>Reason</td>
 *     <td>The reason for the ban</td>
 * </tr>
 * </table>
 * <p>
 * Unsaved information is not automatically written to the implementation's
 * ban list, instead, the {@link #save()} method must be called to write the
 * changes to the ban list. If this ban entry has expired (such as from an
 * unban) and is no longer found in the list, the {@link #save()} call will
 * re-add it to the list, therefore banning the victim specified.
 * <p>
 * Likewise, changes to the associated {@link BanList} or other entries may or
 * may not be reflected in this entry.
 */
public interface BanEntry {

    /**
     * Gets the target involved. This may be in the form of an IP or a player
     * name.
     *
     * @return the target name or IP address
     */
    public String getTarget();

    /**
     * Gets the date this ban entry was created.
     *
     * @return the creation date
     */
    public Date getCreated();

    /**
     * Sets the date this ban entry was created.
     *
     * @param created the new created date, cannot be null
     * @see #save() saving changes
     */
    public void setCreated(Date created);

    /**
     * Gets the source of this ban.
     * <p>
     * Note: A source is considered any String, although this is generally a
     * player name.
     *
     * @return the source of the ban
     */
    public String getSource();

    /**
     * Sets the source of this ban.
     * <p>
     * Note: A source is considered any String, although this is generally a
     * player name.
     *
     * @param source the new source where null values become empty strings
     * @see #save() saving changes
     */
    public void setSource(String source);

    /**
     * Gets the date this ban expires on, or null for no defined end date.
     *
     * @return the expiration date
     */
    public Date getExpiration();

    /**
     * Sets the date this ban expires on. Null values are considered
     * "infinite" bans.
     *
     * @param expiration the new expiration date, or null to indicate an
     *     eternity
     * @see #save() saving changes
     */
    public void setExpiration(Date expiration);

    /**
     * Gets the reason for this ban.
     *
     * @return the ban reason, or null if not set
     */
    public String getReason();

    /**
     * Sets the reason for this ban. Reasons must not be null.
     *
     * @param reason the new reason, null values assume the implementation
     *     default
     * @see #save() saving changes
     */
    public void setReason(String reason);

    /**
     * Saves the ban entry, overwriting any previous data in the ban list.
     * <p>
     * Saving the ban entry of an unbanned player will cause the player to be
     * banned once again.
     */
    public void save();
}
