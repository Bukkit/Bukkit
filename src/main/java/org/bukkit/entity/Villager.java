package org.bukkit.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * Represents a villager NPC
 */
public interface Villager extends NPC {

    /**
     * Get the profession of a villager
     * 
     * @return The villager's profession
     */
    public Profession getProfession();

    /**
     * Represents a Villager Profession
     */
    public enum Profession {
        FARMER(0),
        LIBRARIAN(1),
        PRIEST(2),
        SMITH(3),
        BUTCHER(4);

        private static final Map<Integer, Profession> BY_ID = new HashMap<Integer, Profession>();
        private static final Map<String, Profession> BY_NAME = new HashMap<String, Profession>();
        private int id;

        private Profession(int id) {
            this.id = id;
        }

        /**
         * Get the ID of this profession
         * 
         * @return The ID of this profession
         */
        public int getId() {
            return id;
        }

        /**
         * Get a villager profession by its numeric ID
         * 
         * @param id The ID
         * @return Villager profession with the given ID
         */
        public static Profession getById(int id) {
            return BY_ID.get(id);
        }
        
        /**
         * Get a profession by its unique name
         * <p />
         * This ignores underscores and capitalization
         *
         * @param name The name of the profession
         * @return The profession with the given name
         */
        public static Profession getByName(String name) {
            Validate.notNull(name, "Name cannot be null");

            return BY_NAME.get(name.toLowerCase().replaceAll("_", ""));
        }

        static {
            for (Profession profession : values()) {
                BY_ID.put(profession.id, profession);
                BY_NAME.put(profession.toString().toLowerCase().replaceAll("_", ""), profession);
            }
        }
    }
}
