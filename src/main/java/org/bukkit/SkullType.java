package org.bukkit;

import java.util.HashMap;
import java.util.Map;

public enum SkullType {

    SKELETON(0), WITHER(1), ZOMBIE(2), PLAYER(3), CREEPER(4);

    private final int id;
    private static final Map<Integer, SkullType> ID_MAP = new HashMap<Integer, SkullType>();
    
    static {
        for (SkullType type : values()) {
            if (type.id >= 0) {
                ID_MAP.put(type.id, type);
            }
        }
    }

    SkullType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SkullType fromId(int id) {
        if (id > Integer.MAX_VALUE) {
            return null;
        }
        return ID_MAP.get(id);
    }
}
