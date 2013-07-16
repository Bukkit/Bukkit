package org.bukkit;

import java.util.Date;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Represents an entry into the bans log (banned-players.txt file)
 */
public interface BanEntry {
    /**
     * Returns the player associated with this entry
     * 
     * @return the banned player as an {@link OfflinePlayer}
     */
    public OfflinePlayer getBannedPlayer();

    /**
     * Returns the {@link Date} when entry was created
     * 
     * @return The creation date
     */
    public Date getDateCreated();

    /**
     * Set the date this entry was created.
     * <p>
     * The creation date is automatically set when the player gets banned. The
     * creation date cannot be null.
     * 
     * @param creationDate the Date this entry was created
     * @throws IllegalArgumentException if the creation date is null
     */
    public void setDateCreated(Date creationDate) throws IllegalArgumentException;

    /**
     * Return the source of the ban as a String name
     * <p>
     * This could potentially be the name of a {@link CommandSender} , or
     * perhaps a custom source name that was set by a Plugin.
     * 
     * @return the source name
     */
    public String getSource();

    /**
     * Attempts to convert the source name to an {@link OfflinePlayer}
     * <p>
     * If the player has not played on the server, this will return null.
     * 
     * @return the player who banned, or null if they could not be found
     */
    public OfflinePlayer getSourceAsOfflinePlayer();

    /**
     * Returns whether the source of this entry is the
     * {@link ConsoleCommandSender}
     * 
     * @return true if the console is the source, otherwise false
     */
    public boolean isConsoleBanned();

    /**
     * Set the source {@link CommandSender} that is associated with this entry
     * <p>
     * If the source is null, this will set to "(Unknown)" in the
     * banned-players.txt file.
     * 
     * @param whoBanned the source sender
     */
    public void setSource(CommandSender whoBanned);

    /**
     * Returns the date when this ban will expire
     * <p>
     * If the date hasn't been set, this will return null.
     * 
     * @return the entry's expiry date, or null if there isn't one
     */
    public Date getExpiryDate();

    /**
     * Set the expiry date for this entry
     * <p>
     * Providing a null date will result in a permanent ban
     * 
     * @param expiryDate the date when the ban will expire
     */
    public void setExpiryDate(Date expiryDate);

    /**
     * Returns whether or not the ban has expired
     * 
     * @return true if the ban has expired
     */
    public boolean hasExpired();

    /**
     * Returns the reason why the player was banned
     * 
     * @return the ban reason
     */
    public String getReason();

    /**
     * Set the reason why this player was banned
     * <p>
     * If the ban reason is null or empty, this will use the default ban reason
     * of "Banned by an operator."
     * 
     * @param banReason the ban reason
     */
    public void setReason(String banReason);
}
