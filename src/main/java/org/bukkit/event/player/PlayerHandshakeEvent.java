package org.bukkit.event.player;

import org.bukkit.event.Event;

import java.net.InetAddress;

/**
 * 
 * @author zml2008
 */
public class PlayerHandshakeEvent extends Event {
    private String name;
    private InetAddress addr;
    private boolean checkAuth;

    public PlayerHandshakeEvent(String name, InetAddress addr, boolean checkAuth) {
        super(Type.PLAYER_HANDSHAKE);
        this.name = name;
        this.addr = addr;
        this.checkAuth = checkAuth;
    }


    /**
     * Gets the player's IP address.
     *
     * @return the player's name
     */
    public InetAddress getAddress() {
        return addr;
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets if authentication checks will be performed with minecraft.net
     * @return
     */
    public boolean getCheckAuth() {
        return checkAuth;
    }

    /**
     * Sets if authentication checks will be performed with minecraft.net
     * @param checkAuth
     */
    public void setCheckAuth(boolean checkAuth) {
        this.checkAuth = checkAuth;
    }
}
