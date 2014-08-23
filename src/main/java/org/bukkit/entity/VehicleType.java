package org.bukkit.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

/**
 * An enum of all types of {@link Vehicle vehicles}.
 */
public enum VehicleType {
    /**
     * A placed boat.
     */
    BOAT(EntityType.BOAT),
    /**
     * @see RideableMinecart
     */
    MINECART(EntityType.MINECART),
    /**
     * @see StorageMinecart
     */
    MINECART_CHEST(EntityType.MINECART_CHEST),
    /**
     * @see CommandMinecart
     */
    MINECART_COMMAND(EntityType.MINECART_COMMAND),
    /**
     * @see PoweredMinecart
     */
    MINECART_FURNACE(EntityType.MINECART_FURNACE),
    /**
     * @see ExplosiveMinecart
     */
    MINECART_TNT(EntityType.MINECART_TNT),
    /**
     * @see HopperMinecart
     */
    MINECART_HOPPER(EntityType.MINECART_HOPPER),
    /**
     * @see SpawnerMinecart
     */
    MINECART_MOB_SPAWNER(EntityType.MINECART_MOB_SPAWNER),

    PIG(EntityType.PIG), HORSE(EntityType.HORSE);

    /**
     * The {@link EntityType} that corresponds with this.
     */
    private EntityType type;

    /**
     * Contains a map of these by {@linkplain EntityType#getName() internal save
     * file name}.
     */
    private static final Map<String, VehicleType> NAME_MAP = new HashMap<String, VehicleType>();
    /**
     * Contains a map of these by {@linkplain EntityType#getTypeId() internal
     * Type ID}.
     */
    private static final Map<Short, VehicleType> ID_MAP = new HashMap<Short, VehicleType>();
    /**
     * Contains a map of these by {@linkplain EntityType}.
     */
    private static final Map<EntityType, VehicleType> ENTITYTYPE_MAP = new HashMap<EntityType, VehicleType>();

    // Set up the above maps.
    static {
        for (VehicleType type : values()) {
            if (type.getName() != null) {
                NAME_MAP.put(type.getName().toLowerCase(), type);
            }
            if (type.getTypeId() > 0) {
                ID_MAP.put(type.getTypeId(), type);
            }
            ENTITYTYPE_MAP.put(type.getEntityType(), type);
        }
    }

    private VehicleType(EntityType type) {
        this.type = type;
    }

    /**
     * Checks if an {@link EntityType} is also a VehicleType.
     *
     * @param type
     *            The {@link EntityType} to check.
     * @return true if is a VehicleType
     */
    public static boolean isVehicle(EntityType type) {
        Validate.notNull(type, "Type cannot be null");

        return ENTITYTYPE_MAP.containsKey(type);
    }

    /**
     * Gets the VehicleType version of an {@link EntityType}.
     *
     * @param type
     *            The {@link EntityType} to start with.
     * @return VehicleType if found, or null otherwise
     */
    public static VehicleType getVehicleType(EntityType type) {
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
    public static VehicleType fromName(String name) {
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
    public static VehicleType fromId(int id) {
        if (id > Short.MAX_VALUE) {
            return null;
        }
        return ID_MAP.get((short) id);
    }

    /**
     * Attempts to match the VehicleType with the given name.
     * <p>
     * This is a match lookup; names will be converted to lowercase, then
     * stripped of special characters in an attempt to format it like the enum.
     * <p>
     * Using this for match by ID is deprecated.
     *
     * @param name
     *            Name of the vehicle type to get
     * @return VehicleType if found, or null otherwise
     */
    public static VehicleType matchVehicleType(final String name) {
        Validate.notNull(name, "Name cannot be null");

        VehicleType result = null;

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
                    result = VehicleType.valueOf(filtered.toUpperCase());
                } catch (IllegalArgumentException ex) {
                } // okay to swallow it; we'll just return null
            }
        }

        return result;
    }
}
