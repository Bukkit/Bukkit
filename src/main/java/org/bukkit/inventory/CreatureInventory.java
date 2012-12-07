package org.bukkit.inventory;

import org.bukkit.entity.Creature;

public interface CreatureInventory {

    /**
     * Gets the ItemStack the entity is currently holding.
     * 
     * @return The currently held ItemStack.
     */
    public ItemStack getItemInHand();

    /**
     * Sets the item the entity is holding.
     * 
     * @param stack The stack to put into the entities hand.
     */
    public void setItemInHand(ItemStack stack);

    /**
     * Gets the helmet currently being worn by the entity.
     * 
     * @return The helmet being worn.
     */
    public ItemStack getHelmet();

    /**
     * Gets the chestplate currently being worn by the entity.
     * 
     * @return The chestplate being worn.
     */
    public ItemStack getChestplate();

    /**
     * Gets the leggings currently being worn by the entity.
     * 
     * @return The leggings being worn.
     */
    public ItemStack getLeggings();

    /**
     * Gets the boots currently being worn by the entity.
     * 
     * @return The boots being worn.
     */
    public ItemStack getBoots();

    /**
     * Sets the helmet worn by the entity.
     * 
     * @param helmet The helmet to put on the entity.
     */
    public void setHelmet(ItemStack helmet);

    /**
     * Sets the chestplate worn by the entity.
     * 
     * @param chestplate The chestplate to put on the entity.
     */
    public void setChestplate(ItemStack chestplate);

    /**
     * Sets the leggings worn by the entity.
     * 
     * @param leggings The leggings to put on the entity.
     */
    public void setLeggings(ItemStack leggings);

    /**
     * Sets the boots worn by the entity.
     * 
     * @param boots The boots to put on the entity.
     */
    public void setBoots(ItemStack boots);

    /**
     * Gets an array of all worn armour.
     * 
     * @return The array of worn armour.
     */
    public ItemStack[] getArmorContents();

    /**
     * Sets the entities armour to an array of ItemStacks.
     * 
     * @param items The items to set the armour as.
     */
    public void setArmorContents(ItemStack[] items);
    
    /**
     * Clears the entity of all armour and held items.
     */
    public void empty();
    
    /**
     * Sets the chance from 0-1 of the helmet being dropped upon this creature's death.
     * 
     * @param the chance of the helmet being dropped.
     */
    public void setHelmetDropChance(float chance);
    
    /**
     * Sets the chance from 0-1 of the chestplate being dropped upon this creature's death.
     * 
     * @param the chance of the chestplate being dropped.
     */
    public void setChestPlateDropChance(float chance);
    
    /**
     * Sets the chance from 0-1 of the leggings being dropped upon this creature's death.
     * 
     * @param the chance of the leggings being dropped.
     */
    public void setLeggingsDropChance(float chance);
    
    /**
     * Sets the chance from 0-1 of the boots being dropped upon this creature's death.
     * 
     * @param the chance of the boots being dropped.
     */
    public void setBootsDropChance(float chance);
    
    /**
     * Sets the chance from 0-1 of the Item currently in this creature's hand being dropped upon this creature's death.
     * 
     * @param the chance of the Item currently in this creature's hand being dropped.
     */
    public void setItemInHandDropChance(float chance);
    
    /**
     * Gets the chance from 0-1 of the helmet being dropped upon this creature's death.
     * 
     * @param the chance of the helmet being dropped.
     */
    public float getHelmetDropChance();
    
    /**
     * Gets the chance from 0-1 of the chestplate being dropped upon this creature's death.
     * 
     * @param the chance of the chestplate being dropped.
     */
    public float getChestPlateDropChance();
    
    /**
     * Gets the chance from 0-1 of the leggings being dropped upon this creature's death.
     * 
     * @param the chance of the leggings being dropped.
     */
    public float getLeggingsDropChance();
    
    /**
     * Gets the chance from 0-1 of the boots being dropped upon this creature's death.
     * 
     * @param the chance of the boots being dropped.
     */
    public float getBootsDropChance();
    
    /**
     * Gets the chance from 0-1 of the Item currently in this creature's hand being dropped upon this creature's death.
     * 
     * @param the chance of the Item currently in this creature's hand being dropped.
     */
    public float getItemInHandDropChance();

    /**
     * Get the entity this EntityEquipment belongs to.
     * 
     * @return the entity this EntityEquipment belongs to.
     */
    public Creature getHolder();
}
