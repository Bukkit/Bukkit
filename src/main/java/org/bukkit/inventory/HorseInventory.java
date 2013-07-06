package org.bukkit.inventory;

public interface HorseInventory extends Inventory {
    /**
     * Gets the horse's saddle.
     * 
     * Returns null if the horse lacks a saddle.
     * 
     * @return An ItemStack representing the saddle
     */
    public ItemStack getSaddle();
    
    /**
     * Gets the horse's armor.
     * 
     * Returns null if the horse lacks armor.
     * 
     * @return An ItemStack representing the armor
     */
    public ItemStack getArmor();
    
    /**
     * Sets the horse's saddle.
     * 
     * @param saddle The saddle to set.
     */
    public void setSaddle(ItemStack saddle);
    
    /**
     * Sets the horse's armor.
     * 
     * Adding armor to any horse type besides HORSE may prevent 
     * the client from properly rendering the texture.
     * 
     * @param armor The armor to set
     */
    public void setArmor(ItemStack armor);
}
