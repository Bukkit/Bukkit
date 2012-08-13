package org.bukkit.command;

import org.bukkit.Server;
import org.bukkit.permissions.Permissible;

public interface CommandSender extends Permissible {

    /**
     * Sends this sender a message
     *
     * @param message Message to be displayed
     */
    public void sendMessage(String message);

    /**
     * Sends this sender multiple messages
     *
     * @param messages An array of messages to be displayed
     */
    public void sendMessage(String[] messages);

    /**
     * Returns the server instance that this command is running on
     *
     * @return Server instance
     */
    public void sendMessage(int message);
    public void sendMessage(float message);
    public void sendMessage(long message);
    public void sendMessage(double message);
    public void sendMessage(boolean message);
    public void sendMessage(char message);
    public void sendMessage(char[] messages);
    public void sendMessage(Object message);
    
    public Server getServer();

    /**
     * Gets the name of this command sender
     *
     * @return Name of the sender
     */
    public String getName();
}
