package org.bukkit.entity;

import org.bukkit.inventory.HorseInventory;

/**
 * Represents a Horse, Donkey, or Mule.
 * 
 * Horses, donkeys, and mules are all collectively represented as 
 * "horses" and can be accessed through this API.
 */
public interface Horse extends Animals, Vehicle {

    /**
     * Describes the various types of horses.
     */
    public enum HorseType {
        /**
         * Represents a Horse.
         */
        HORSE,
        
        /**
         * Represents a Donkey.
         * 
         * Donkeys cannot use armor, but can be carry with a chest.
         */
        DONKEY,
        
        /**
         * Represents a Mule.
         * 
         * Mules are the result of breeding a horse and donkey.
         */
        MULE,
        
        /**
         * Represents an Undead Horse.
         */
        UNDEAD_HORSE,
        
        /**
         * Represents a Skeletal Horse.
         */
        SKELETAL_HORSE;
        
    }
    
    public enum CoatColor {
        WHITE,
        CREAMY,
        CHESTNUT,
        BROWN,
        BLACK,
        GRAY,
        DARK_BROWN;
    }
    
    public enum CoatPattern {
        NONE,
        WHITE,
        WHITE_FIELD,
        WHITE_DOTS,
        BLACK_DOTS;
    }
    
    /**
     * Returns the type of this horse
     * 
     * @return the type of the horse
     */
    public HorseType getHorseType();
    
    /**
     * Changes the type of this horse
     * 
     * @param type the HorseType to change to
     */
    public void setHorseType(HorseType type);
    
    /**
     * Returns the coat color of this horse.
     * 
     * @return the horse's CoatColor
     */
    public CoatColor getCoatColor();
    
    /**
     * Sets the coat color of this horse.
     * 
     * Only horses of HorseType.HORSE will display this color 
     * in-game.
     * 
     * @param color the new CoatColor
     */
    public void setCoatColor(CoatColor color);
    
    /**
     * Returns the coat pattern of this horse.
     * 
     * @return the horse's CoatPattern
     */
    public CoatPattern getCoatPattern();
    
    /**
     * Sets the coat pattern of this horse.
     * 
     * Only horses of HorseType.HORSE will display this pattern 
     * in-game.
     * 
     * @param pattern the new CoatPattern
     */
    public void setCoatPattern(CoatPattern pattern);
    
    /**
     * Returns the speed of the horse.
     * 
     * @return the horse's speed
     */
    public double getSpeed();
    
    /**
     * Sets the speed of the horse.
     * 
     * @param speed the new speed
     */
    public void setSpeed(double speed);
    
    /**
     * Returns the horse's jump height.
     * 
     * @return the jump height
     */
    public double getJumpHeight();
    
    /**
     * Sets the jump height of the horse.
     * 
     * @param jumpHeight the new jump height
     */
    public void setJumpHeight(double jumpHeight);
    
    /**
     * Gets the temper of this horse.
     * 
     * A high temper makes the horse more resistant to taming.
     * 
     * @return the horse's temper
     */
    public int getTemper();
    
    /**
     * Sets the temper of this horse.
     * 
     * @param temper the new temper
     */
    public void setTemper(int temper);
    
    /**
     * Determines if this horse is tamed.
     * 
     * @return true if tamed, false otherwise
     */
    public boolean isTamed();
    
    /**
     * Sets whether or not this horse is tamed.
     * 
     * @param tamed true if the horse should be tamed, false otherwise
     */
    public void setTamed(boolean tamed);
    
    /**
     * Determines if this horse carries a chest.
     * 
     * Carrying a chest will make 15 inventory slots 
     * available to the rider. In-game, chests can 
     * only be placed on donkeys and mules. However, any 
     * horse type can be given a chest through the API.
     * 
     * @return 
     */
    public boolean hasChest();
    
    /**
     * Sets whether or not this horse has a chest.
     * 
     * @param hasChest true if this horse should have a chest, false otherwise
     */
    public void setHasChest(boolean hasChest);
    
    /**
     * Gets this horse's inventory.
     * 
     * Provides access to the horse's saddle, armor, and 
     * any chest slots.
     * 
     * @return a HorseInventory for this horse
     */
    public HorseInventory getInventory();
}
