
package org.bukkit.event.server;

import org.bukkit.event.Event;

/**
 * Server Command events
 */
public class ServerCommandEvent extends Event {
	
	private String command;
	private String[] args;
	
	/**
	 * Event for the call of server commands through the console
	 * @param type The type of server command
	 * @param command The command name
	 * @param args the command arguments
	 */
    public ServerCommandEvent(final Type type, String command, String[] args) {
        super(type);
        this.command = command;
        this.args = args;
    }
    
    /**
     * The name of this event's command.
     * @return a commandname
     */
    public String getCommand(){
        return this.command;
    }
    
    /**
     * The arguments of this event's command.
     * @return the command arguments
     */
    public String[] getArguments(){
        return this.args;
    }
}