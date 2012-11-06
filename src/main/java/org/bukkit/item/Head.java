package org.bukkit.item;

public interface Head extends ItemState {
    
    /**
     * Sets skin of head to given nick
     * 
     * @param nick Nick
     */
    void setSkin(String nick);
    /**
     * Gets skin of skull
     * 
     * @return Nick 
     */
    String getSkin();
}
