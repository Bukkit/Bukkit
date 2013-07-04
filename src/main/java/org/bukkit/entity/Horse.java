package org.bukkit.entity;

/**
 * Represents a Horse.
 */
public interface Horse extends Animals, Vehicle {

    //Gets The Horses Type
    public HorseType getHorseType();

    //Sets The Horses Type
    public void setHorseType(HorseType type);

    //Horse Types
    public enum HorseType {
        NORMAL(0),
        DONKEY(1),
        MULE(2),
        UNDEAD(3),
        SKELETAL(4);

        private static final HorseType[] types = new HorseType[HorseType.values().length];
        private final int id;

        static {
            for (HorseType type : values()) {
                types[type.getId()] = type;
            }
        }

        private HorseType(int id) {
            this.id = id;
        }

     
        public int getId() {
            return id;
        }

      
        public static HorseType getType(int id) {
            return (id >= types.length) ? null : types[id];
        }
    }
  
  
  
  }

