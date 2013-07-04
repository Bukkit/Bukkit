package org.bukkit.entity;

/**
 * Represents a Horse.
 */
public interface Horse extends Animals, Vehicle {

     /**
     * Gets the current type of this horse.
     *
     * @return Current type
     */
    public HorseType getHorseType();

     /**
     * Sets the new type of this horse.
     *
     * @param type New type
     */
    public void setHorseType(HorseType type);

     /*
     * Represents the various different Horse types.
     */
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

         /**
         * Gets the ID of this horse type.
         *
         * @return Horse type ID
         */
        public int getId() {
            return id;
        }

         /**
         * Gets a horse type by its ID.
         *
         * @param id ID of the horse type to get.
         * @return Resulting horse type, or null if not found.
         */
        public static HorseType getType(int id) {
            return (id >= types.length) ? null : types[id];
        }
    }
  
  
  
  }

