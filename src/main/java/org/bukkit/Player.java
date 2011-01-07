
package org.bukkit;

/**
 * Represents a player, connected or not
 * 
 */
public interface Player extends HumanEntity {
    /**
     * Checks if this player is currently an operator
     *
     * @return true if they are an operator
     */
    public boolean isOp();

    /**
     * Sends this player a message, which will be displayed in their chat
     *
     * @param message Message to be displayed
     */
    public void sendMessage(String message);
    
}
