package org.bukkit;

import java.util.HashMap;
import java.util.Map;

public enum Enchantment {

    /**
     * Protection
     */

    PROTECTION_ENVIRONMENTAL(0, 4),

    /**
     * Fire Protection
     */

    PROTECTION_FIRE(1, 4),

    /**
     * Feather Falling
     */

    PROTECTION_FALL(2, 4),

    /**
     * Blast Protection
     */

    PROTECTION_EXPLOSIONS(3, 4),

    /**
     * Projectile Protection
     */

    PROTECTION_PROJECTILE(4, 4),

    /**
     * Respiration
     */

    OXYGEN(5, 3),

    /**
     * Aqua Affinity
     */

    WATER_WORKER(6, 1),

    /**
     * Sharpness
     */

    DAMAGE_ALL(16, 5),

    /**
     * Smite
     */

    DAMAGE_UNDEAD(17, 5),

    /**
     * Bane of Arthropods
     */

    DAMAGE_ARTHROPODS(18, 5),

    /**
     * Knockback
     */

    KNOCKBACK(19, 2),

    /**
     * Fire Aspect
     */

    FIRE_ASPECT(20, 2),

    /**
     * Looting
     */

    LOOT_BONUS_MOBS(21, 3),

    /**
     * Efficiency
     */

    DIG_SPEED(32, 5),

    /**
     * Silk Touch
     */

    SILK_TOUCH(33, 1),

    /**
     * Unbreaking
     */

    DURABILITY(34, 3),

    /**
     * Fortune
     */

    LOOT_BONUS_BLOCKS(35, 3);
    
    private final int id;
    private final int maxlevel;
    private final static Map<Integer, Enchantment> enchantmentMap = new HashMap<Integer, Enchantment>();

    Enchantment(int id, int maxlevel) {
        this.id = id;
        this.maxlevel = maxlevel;
    }

    /**
     * Gets the maximum level for this enchantment
     * @return An integer value specifying the enchantment
     */

    public int getMaxLevel() {
        return this.maxlevel;
    }

    /**
     * Gets the id specified by the enchantment
     * @return An integer value specifying the id
     */
    
    public int getId() {
        return this.id;
    }

    /**
     * Returns the Enchantment represented by this id
     * @param id The id to check
     * @return Representative Enchantment {@link org.bukkit.Enchantment} linked with this id, or null otherwise
     */

    public static Enchantment byId(int id) {
        return enchantmentMap.get(id);
    }
    
    static {
        for(Enchantment enc : Enchantment.values()) {
            enchantmentMap.put(enc.getId(), enc);
        }
    }
    
}
