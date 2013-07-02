package org.bukkit;

import java.util.Map;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Maps;

/**
 * Represents the many game rules that can be set with {@link World#setGameRuleValue(GameRule, String)}
 */
public enum GameRule {
    /**
     * Used to toggle text output of command block commands to the console
     */
    COMMAND_BLOCK_OUTPUT("commandBlockOutput"),

    /**
     * Used to toggle the day/night cycle
     */
    DO_DAYLIGHT_CYCLE("doDaylightCycle"),

    /**
     * Used to toggle whether fire spreads and dissipates
     */
    DO_FIRE_TICK("doFireTick"),

    /**
     * Used to toggle whether mobs drop loot
     */
    DO_MOB_LOOT("doMobLoot"),

    /**
     * Used to toggle the natural spawning of mobs (does not effect mob eggs or mob spawners)
     */
    DO_MOB_SPAWNING("doMobSpawning"),

    /**
     * Used to toggle the dropping of blocks when broken (includes TNT destroying blocks)
     */
    DO_TILE_DROPS("doTileDrops"),

    /**
     * Used to toggle whether players will keep their inventory on death
     */
    KEEP_INVENTORY("keepInventory"),

    /**
     * Used to toggle mobs griefing blocks (e.g creepers blowing up blocks, endermen picking up blocks, etc)
     */
    MOB_GRIEFING("mobGriefing"),

    /**
     * Used to toggle players naturally regaining health regardless of their hunger level
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
    public static GameRule getByRule(String rule) {
        Validate.notNull(rule, "Rule cannot be null");
        return BY_RULE.get(rule);
    }

    static {
        for(GameRule rule : values()) 
            BY_RULE.put(rule.toString(), rule);
    }

}
