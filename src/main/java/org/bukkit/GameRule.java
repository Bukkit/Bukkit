package org.bukkit;

import org.apache.commons.lang.Validate;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * An enum to represent the possible game rule values
 */
public enum GameRule {

    /**
     * Represents the game rule to toggle text output of command block commands
     * to the console
     */
    COMMAND_BLOCK_OUTPUT("commandBlockOutput"),

    /**
     * Represents the game rule to toggle the day/night cycle
     */
    DAYLIGHT_CYCLE("doDaylightCycle"),

    /**
     * Represents the game rule to toggle whether fire spreads and dissipates
     */
    FIRE_TICK("doFireTick"),

    /**
     * Represents the game rule to toggle whether mobs drop loot
     */
    MOB_LOOT("doMobLoot"),

    /**
     * Represents the game rule to toggle the natural spawning of mobs.
     * <p>
     * This does not effect mob eggs or mob spawners
     */
    MOB_SPAWNING("doMobSpawning"),

    /**
     * Represents the game rule to toggle the dropping of blocks when broken.
     * <p>
     * This includes TNT destroying blocks
     */
    TILE_DROPS("doTileDrops"),

    /**
     * Represents the game rule to toggle whether players will keep their
     * inventory on death
     */
    KEEP_INVENTORY("keepInventory"),

    /**
     * Represents the game rule to toggle mobs griefing blocks.
     * <p>
     * Example: Creepers exploding or Endermen picking up blocks
     */
    MOB_GRIEFING("mobGriefing"),

    /**
     * Represents the game rule to toggle players naturally regaining health
     * regardless of their hunger level
     */
    NATURAL_REGENERATION("naturalRegeneration");

    private String rule;
    private static Map<String, GameRule> BY_RULE = Maps.newHashMap();

    private GameRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return this.rule;
    }

    /**
     * Gets the {@link GameRule} represented by the specified string
     *
     * @param rule The rule to look for
     * @return The GameRule with the specified name or null if not found
     */
    public static GameRule getByString(String rule) {
        Validate.notNull(rule, "Rule cannot be null");
        return BY_RULE.get(rule);
    }

    static {
        for(GameRule rule : values()) {
            BY_RULE.put(rule.toString(), rule);
        }
    }
}
