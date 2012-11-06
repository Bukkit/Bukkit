package org.bukkit.item;

import java.awt.Color;

public interface Armor extends ItemState {
    
    /**
     * Sets the color of armor
     * 
     * @param color Color
     */
    void setColor(int color);
    
    /**
     * Sets the color of armor
     * 
     * @param color Color
     */
    void setColor(Color color);
    
    /**
     * Gets the color of armor
     * 
     * @return Color
     */
    int getColor();
}
