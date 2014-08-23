package org.bukkit.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * An enum of all types of {@link Hanging hangings}.
 */
public enum HangingType {
    /**
     * A leash attached to a fencepost.
     */
    LEASH_HITCH(EntityType.LEASH_HITCH),
    /**
     * A painting on a wall.
     */
    PAINTING(EntityType.PAINTING),
    /**
     * An item frame on a wall.
     */
    ITEM_FRAME(EntityType.ITEM_FRAME);

    /**
     * The {@link EntityType} that corresponds with this.
     */
    private EntityType type;

    /**
     * Contains a map of these by {@linkplain EntityType#getName() internal save
     * file name}.
     */
    private static final Map<String, HangingType> NAME_MAP = new HashMap<String, HangingType>();
    /**
     * Contains a map of these by {@linkplain EntityType#getTypeId() internal
     * Type ID}.
     */
    private static final Map<Short, HangingType> ID_MAP = new HashMap<Short, HangingType>();
    /**
     * Contains a map of these by {@linkplain EntityType}.
     */
    private static final Map<EntityType, HangingType> ENTITYTYPE_MAP = new HashMap<EntityType, HangingType>();

    // Set up the above maps.
    static {
        for (HangingType type : values()) {
            if (type.getName() != null) {
                NAME_MAP.put(type.getName().toLowerCase(), type);
            }
            if (type.getTypeId() > 0) {
                ID_MAP.put(type.getTypeId(), type);
            }
            ENTITYTYPE_MAP.put(type.getEntityType(), type);
        }
    }

    private HangingType(EntityType type) {
        this.type = type;
    }

    /**
     * Checks if an {@link EntityType} is also a HangingType.
     *
     * @param type
     *            The {@link EntityType} to check.
     * @return true if is a HangingType
     */
    public static boolean isHanging(EntityType type) {
        Validate.notNull(type, "Type cannot be null");

        return ENTITYTYPE_MAP.containsKey(type);
    }

    /**
     * Gets the HangingType version of an {@link EntityType}.
     *
     * @param type
     *            The {@link EntityType} to start with.
     * @return HangingType if found, or null otherwise
     */
    public static HangingType getHangingType(EntityType type) {
        return ENTITYTYPE_MAP.get(type);
    }

    /**
     * Gets the {@link EntityType} that corresponds with this.
     */
    public EntityType getEntityType() {
        return this.type;
    }

    // Methods ported from EntityType...
    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public String getName() {
        return type.getName();
    }

    public Class<? extends Entity> getEntityClass() {
        return type.getEntityClass();
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public short getTypeId() {
        return type.getTypeId();
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public static HangingType fromName(String name) {
        if (name == null) {
            return null;
        }
        return NAME_MAP.get(name.toLowerCase());
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public static HangingType fromId(int id) {
        if (id > Short.MAX_VALUE) {
            return null;
        }
        return ID_MAP.get((short) id);
    }

    /**
     * Attempts to match the HangingType with the given name.
     * <p>
     * This is a match lookup; names will be converted to lowercase, then
     * stripped of special characters in an attempt to format it like the enum.
     * <p>
     * Using this for match by ID is deprecated.
     *
     * @param name
     *            Name of the hanging type to get
     * @return HangingType if found, or null otherwise
     */
    public static HangingType matchHangingType(final String name) {
        Validate.notNull(name, "Name cannot be null");

        HangingType result = null;

        try {
            result = fromId(Integer.parseInt(name));
        } catch (NumberFormatException ex) {
        }

        if (result == null) {
            String filtered = name.toLowerCase();

            filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
            result = fromName(filtered);
            if (result == null) {
                try {
                    result = HangingType.valueOf(filtered.toUpperCase());
                } catch (IllegalArgumentException ex) {
                } // okay to swallow it; we'll just return null
            }
        }

        return result;
    }
}
