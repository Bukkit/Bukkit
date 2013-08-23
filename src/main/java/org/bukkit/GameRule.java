package org.bukkit;

import com.google.common.collect.Maps;
import org.apache.commons.lang.Validate;

import java.util.Map;

/**
 * Represents the many game rules that can be set with {@link World#setGameRuleValue(GameRule, String)}
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
     * This does not effect mob eggs or mob spawners
     */
    MOB_SPAWNING("doMobSpawning"),

    /**
     * Represents the game rule to toggle the dropping of blocks when broken.
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
     * For example: creepers blowing up blocks or endermen picking up blocks
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
     * @param rule Value to check
     * @return The GameRule with the given value or {@link null} if it does not exist
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
