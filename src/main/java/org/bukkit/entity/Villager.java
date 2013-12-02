package org.bukkit.entity;

import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.MerchantInventory;

/**
 * Represents a villager NPC
 */
public interface Villager extends Ageable, NPC, InventoryHolder {
    /**
     * Gets the current profession of this villager.
     * @return Current profession.
     */
    public Profession getProfession();

    /**
     * Sets the new profession of this villager.
     * @param profession New profession.
     */
    public void setProfession(Profession profession);

    /**
     * Gets the inventory of the villager. Changes to the MerchantInventory will
     * be immediately reflected in the villager.
     * @return the MerchantInventory of this villager
     */
    public MerchantInventory getInventory();

    /**
     * Represents the various different Villager professions there may be.
     */
    public enum Profession {
        FARMER(0),
        LIBRARIAN(1),
        PRIEST(2),
        BLACKSMITH(3),
        BUTCHER(4);

        private static final Profession[] professions = new Profession[Profession.values().length];
        private final int id;

        static {
            for (Profession type : values()) {
                professions[type.getId()] = type;
            }
        }

        private Profession(int id) {
            this.id = id;
        }

        /**
         * Gets the ID of this profession.
         *
         * @return Profession ID.
         * @deprecated Magic value
         */
        @Deprecated
        public int getId() {
            return id;
        }

        /**
         * Gets a profession by its ID.
         *
         * @param id ID of the profession to get.
         * @return Resulting profession, or null if not found.
         * @deprecated Magic value
         */
        @Deprecated
        public static Profession getProfession(int id) {
            return (id >= professions.length) ? null : professions[id];
        }
    }
}
