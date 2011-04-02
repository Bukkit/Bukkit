
package org.bukkit.entity;

import java.net.InetSocketAddress;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

/**
 * Represents a player, connected or not
 *
 */
public interface Player extends HumanEntity, CommandSender {
    /**
     * Checks if this player is currently online
     *
     * @return true if they are online
     */
    public boolean isOnline();

    /**
     * Gets the "friendly" name to display of this player. This may include color.
     *
     * Note that this name will not be displayed in game, only in chat and places
     * defined by plugins
     *
     * @return String containing a color formatted name to display for this player
     */
    public String getDisplayName();

    /**
     * Sets the "friendly" name to display of this player. This may include color.
     *
     * Note that this name will not be displayed in game, only in chat and places
     * defined by plugins
     *
     * @return String containing a color formatted name to display for this player
     */
    public void setDisplayName(String name);

    /**
     * Set the target of the player's compass.
     *
     * @param loc
     */
    public void setCompassTarget(Location loc);

    /**
     * Get the previously set compass target.
     *
     * @return location of the target
     */
    public Location getCompassTarget();

    /**
     * Gets the socket address of this player
     * @return the player's address
     */
    public InetSocketAddress getAddress();
    
    /**
     * Sends this sender a message raw
     *
     * @param message Message to be displayed
     */
    public void sendRawMessage(String message);

    /**
     * Kicks player with custom kick message.
     *
     * @return
     */
    public void kickPlayer(String message);

    /**
     * Says a message (or runs a command).
     *
     * @param msg message to print
     */
    public void chat(String msg);

    /**
     * Makes the player perform the given command
     *
     * @param command Command to perform
     * @return true if the command was successful, otherwise false
     */
    public boolean performCommand(String command);

    /**
     * Returns if the player is in sneak mode
     * @return true if player is in sneak mode
     */
    public boolean isSneaking();

    /**
     * Sets the sneak mode the player
     * @param sneak true if player should appear sneaking
     */
    public void setSneaking(boolean sneak);

    /**
     * Closes any dialog windows the client may have open at the time. If no windows are open, it does nothing
     */
    public void closeWindow();

    /**
     * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane;
     * 
     * @param inventory to use in the dialog GUI
     */
    public void openInventoryWindow(Inventory inventory);

    /**
     * Opens an workbench dialog to the player, using the workbench at the given location
     * 
     * @param location of the workbench to use. Must be a valid workbench.
     */
    public void openWorkbenchWindow(Location location);
    
    /**
     * Saves the players current location, health, inventory, motion, and other information into the username.dat file, in the world/player folder
     */
    public void savePlayerData();
    
    /**
     * Loads the players current location, health, inventory, motion, and other information from the username.dat file, in the world/player folder
     * 
     * Note: This will overwrite the players current inventory, health, motion, etc, with the state from the saved dat file.
     */
    public void loadPlayerData();

    /**
     * Forces an update of the player's entire inventory.
     *
     * @return
     *
     * @deprecated This method should not be relied upon as it is a temporary work-around for a larger, more complicated issue.
     */
    public void updateInventory();
}
