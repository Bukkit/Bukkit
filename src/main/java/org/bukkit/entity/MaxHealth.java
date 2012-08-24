package org.bukkit.entity;

/**
 * Bukkit implementation of the Max Health
 */
public enum MaxHealth {
    CREEPER(20), 
    SKELETON(20), 
    SPIDER(16), 
    GIANT(100), 
    ZOMBIE(20), 
    GHAST(10), 
    PIG_ZOMBIE(20),
    ENDERMAN(40), 
    CAVE_SPIDER(12), 
    SILVERFISH(8), 
    BLAZE(20), 
    ENDER_DRAGON(100), 
    PIG(10), 
    SHEEP(8), 
    COW(10), 
    CHICKEN(4), 
    SQUID(10), 
    TAMED_WOLF(20), 
    WILD_WOLF(8), 
    MUSHROOM_COW(10), 
    SNOWMAN(4), 
    HUMAN(20),
    IRON_GOLEM(100),
    OCELOT(10),
    VILLAGER(20); 
    
    private int health;
	private MaxHealth(int health) {
		this.health = health;
	}
	/**
	 * Sets the maximum health of an entity globally
	 * @param health The new health
	 */
	public void setMaxHealth(int health) {
		this.health = health;
	}
	/**
	 * Gets the current maximum health of an entity
	 * @return int The health value
	 */
	public int getMaximumHealth() {
		return this.health;
	}
}
