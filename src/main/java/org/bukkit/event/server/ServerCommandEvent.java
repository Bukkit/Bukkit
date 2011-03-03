
package org.bukkit.event.server;

import org.bukkit.event.Event;

/**
 * Server Command events
 */
public class ServerCommandEvent extends Event {
	
	private String command;
	private String[] args;
	
    public ServerCommandEvent(final Type type, String command, String[] args) {
        super(type);
        this.command = command;
        this.args = args;
    }
    
    public String getCommand(){
        return this.command;
    }
    
    public String[] getArguments(){
        return this.args;
    }
}