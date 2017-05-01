package org.bukkit.command;

import org.bukkit.Server;
import org.bukkit.permissions.Permissible;

public interface CommandSender extends Permissible {

    /**
     * Sends this sender any amount of messages.
     * Using this method without adding any arguments is equivalent to doing {@code sender.sendMessage("")}.
     *
     * @param message message(s) to be displayed
     */
    public void sendMessage(String... message);

    /**
     * Returns the server instance that this command is running on
     *
     * @return Server instance
     */
    public Server getServer();

    /**
     * Gets the name of this command sender
     *
     * @return Name of the sender
     */
    public String getName();
}
