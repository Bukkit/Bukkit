package org.bukkit.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CreatureType {
    // These strings MUST match the strings in nms.EntityTypes and are case sensitive.
    CHICKEN("Chicken", Chicken.class, (short) 93),
    COW("Cow", Cow.class, (short) 92),
    CREEPER("Creeper", Creeper.class, (short) 50),
    GHAST("Ghast", Ghast.class, (short) 56),
    GIANT("Giant", Giant.class, (short) 53),
    MONSTER("Monster", Monster.class, null),
    PIG("Pig", Pig.class, (short) 90),
    PIG_ZOMBIE("PigZombie", PigZombie.class, (short) 57),
    SHEEP("Sheep", Sheep.class, (short) 91),
    SKELETON("Skeleton", Skeleton.class, (short) 51),
    SLIME("Slime", Slime.class, (short) 55),
    SPIDER("Spider", Spider.class, (short) 52),
    SQUID("Squid", Squid.class, (short) 94),
    ZOMBIE("Zombie", Zombie.class, (short) 54),
    WOLF("Wolf", Wolf.class, (short) 95),
    CAVE_SPIDER("CaveSpider", CaveSpider.class, (short) 59),
    ENDERMAN("Enderman", Enderman.class, (short) 58),
    SILVERFISH("Silverfish", Silverfish.class, (short) 60),
    ENDER_DRAGON("EnderDragon", EnderDragon.class, (short) 63),
    VILLAGER("Villager", Villager.class, (short) 120),
    BLAZE("Blaze", Blaze.class, (short) 61),
    MUSHROOM_COW("MushroomCow", MushroomCow.class, (short) 96),
    MAGMA_CUBE("LavaSlime", MagmaCube.class, (short) 62),
    SNOWMAN("SnowMan", Snowman.class, (short) 97);

    private String name;
    private Class<? extends Entity> clazz
    private Short typeId;

    private static final Map<String, CreatureType> mapping = new HashMap<String, CreatureType>();
    private static final Map<Short, CreatureType> idMap = new HashMap<Short, CreatureType>();

    static {
        for (CreatureType type : EnumSet.allOf(CreatureType.class)) {
            mapping.put(type.name, type);
            if (type.typeId != null) {
                idMap.put(type.typeId, type);
            }
        }
    }

    private CreatureType(String name, Class<? extends Entity> clazz, Short typeId) {
        this.name = name;
        this.clazz = clazz;
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Entity> getEntityClass() {
        return clazz;
    }
    
    public Short getTypeId() {
        return typeId;
    }

    public static CreatureType fromName(String name) {
        return mapping.get(name);
    }
    
    public static CreatureType fromId(short id) {
        return idMap.get(id);
    }
}
