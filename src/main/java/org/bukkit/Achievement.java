package org.bukkit;

/**
 * Represents an achievement, which may be given to players.
 */
public enum Achievement {
    OPEN_INVENTORY("openInventory"),
    MINE_WOOD ("mineWood", OPEN_INVENTORY),
    BUILD_WORKBENCH ("buildWorkBench", MINE_WOOD),
    BUILD_PICKAXE ("buildPickaxe", BUILD_WORKBENCH),
    BUILD_FURNACE ("buildFurnace", BUILD_PICKAXE),
    ACQUIRE_IRON ("acquireIron", BUILD_FURNACE),
    BUILD_HOE ("buildHoe", BUILD_WORKBENCH),
    MAKE_BREAD ("makeBread", BUILD_HOE),
    BAKE_CAKE ("bakeCake", BUILD_HOE),
    BUILD_BETTER_PICKAXE ("buildBetterPickaxe", BUILD_PICKAXE),
    COOK_FISH ("cookFish", BUILD_FURNACE),
    ON_A_RAIL ("onARail", ACQUIRE_IRON),
    BUILD_SWORD ("buildSword", BUILD_WORKBENCH),
    KILL_ENEMY ("killEnemy", BUILD_SWORD),
    KILL_COW ("killCow", BUILD_SWORD),
    FLY_PIG ("flyPig", KILL_COW),
    SNIPE_SKELETON ("snipeSkeleton", KILL_ENEMY),
    GET_DIAMONDS ("diamonds", ACQUIRE_IRON),
    NETHER_PORTAL ("portal", GET_DIAMONDS),
    GHAST_RETURN ("ghast", NETHER_PORTAL),
    GET_BLAZE_ROD ("blazeRod", NETHER_PORTAL),
    BREW_POTION ("potion", GET_BLAZE_ROD),
    END_PORTAL ("theEnd", GET_BLAZE_ROD),
    THE_END ("theEnd2", END_PORTAL),
    ENCHANTMENTS ("enchantments", GET_DIAMONDS),
    OVERKILL ("overkill", ENCHANTMENTS),
    BOOKCASE ("bookcase", ENCHANTMENTS),
    EXPLORE_ALL_BIOMES ("exploreAllBiomes", END_PORTAL),
    SPAWN_WITHER ("spawnWither", THE_END),
    KILL_WITHER ("killWither", SPAWN_WITHER),
    FULL_BEACON ("fullBeacon", KILL_WITHER),
    BREED_COW ("breedCow", KILL_COW),
    DIAMONDS_TO_YOU ("diamondsToYou", GET_DIAMONDS),
    ;

    private final Achievement parent;
    private final String id;

    private Achievement(String id) {
        parent = null;
        this.id = id;
    }

    private Achievement(String id, Achievement parent) {
        this.parent = parent;
        this.id = id;
    }

    /**
     * Returns whether or not this achievement has a parent achievement.
     * 
     * @return whether the achievement has a parent achievement
     */
    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Returns the parent achievement of this achievement, or null if none.
     * 
     * @return the parent achievement or null
     */
    public Achievement getParent() {
        return parent;
    }
    
    /**
     * Returns the Minecraft id string of this achievement
     * 
     * @return the Minecraft id of the achievement
     */
    public String getID(){
    	return "minecraft:" + id;
    }
}

