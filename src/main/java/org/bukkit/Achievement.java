package org.bukkit;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents an achievement, which may be given to players
 */
public enum Achievement {
    OPEN_INVENTORY(0),
    MINE_WOOD(1),
    BUILD_WORKBENCH(2),
    BUILD_PICKAXE(3),
    BUILD_FURNACE(4),
    ACQUIRE_IRON(5),
    BUILD_HOE(6),
    MAKE_BREAD(7),
    BAKE_CAKE(8),
    BUILD_BETTER_PICKAXE(9),
    COOK_FISH(10),
    ON_A_RAIL(11),
    BUILD_SWORD(12),
    KILL_ENEMY(13),
    KILL_COW(14),
    FLY_PIG(15),
    SNIPE_SKELETON(16),
    MINE_DIAMOND(17),
    BUILD_ENCHANTER(18),
    OVERKILL(19),
    BUILD_BOOKSHELF(20),
    BUILD_NETHER_PORTAL(21),
    KILL_GHAST_WITH_FIREBALL(22),
    GET_BLAZE_ROD(23),
    BREW_PORTION(24),
    ENTER_END(25),
    KILL_ENDER_DRAGON(26);

    /**
     * The offset used to distinguish Achievements and Statistics
     */
    public final static int STATISTIC_OFFSET = 0x500000;
    private final static Map<Integer, Achievement> BY_ID = Maps.newHashMap();
    private final int id;

    private Achievement(int id) {
        this.id = STATISTIC_OFFSET + id;
    }

    /**
     * Gets the ID for this achievement.
     * <p />
     * Note that this is offset using {@link #STATISTIC_OFFSET}
     *
     * @return ID of this achievement
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the achievement associated with the given ID.
     * <p />
     * Note that the ID must already be offset using {@link #STATISTIC_OFFSET}
     *
     * @param id ID of the achievement to return
     * @return Achievement with the given ID
     */
    public static Achievement getById(int id) {
        return BY_ID.get(id);
    }

    static {
        for (Achievement achievement : values()) {
            BY_ID.put(achievement.id, achievement);
        }
    }
}
