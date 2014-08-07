package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a tooltip that will appear on the Client screen as soon as he
 * hovers the {@link Part} this Hover is attached to.
 */
public final class Hover {

    /**
     * Builds a new Hover of type {@link Type#SHOW_ACHIEVEMENT}.
     *
     * @param achievement the Achievement
     * @return a new Hover of type SHOW_ACHIEVEMENT
     */
    public static Hover of(Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Hover(Type.SHOW_ACHIEVEMENT, achievement);
    }

    /**
     * Builds a new Hover of type {@link Type#SHOW_ITEM}.
     *
     * @param item the ItemStack
     * @return a new Hover of type SHOW_ITEM
     */
    public static Hover of(ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Hover(Type.SHOW_ITEM, item.clone());
    }

    /**
     * Builds a new Hover of type {@link Type#SHOW_TEXT}.
     *
     * @param lines the text
     * @return a new Hover of type SHOW_TEXT
     */
    public static Hover of(String... lines) {
        Validate.notEmpty(lines, "lines can't be empty");
        Validate.noNullElements(lines, "lines can't contain null elements");
        // TODO Support \n in Strings here if wanted
        return new Hover(Type.SHOW_TEXT, lines.clone());
    }

    /**
     * An enum listing all possible types of Hover.
     */
    public enum Type {
        /**
         * Will show the player a tooltip containing the provided
         * {@link Achievement}'s name and description when he hovers the
         * text.
         */
        SHOW_ACHIEVEMENT,

        /**
         * Will show the player the same tooltip that appeard when hovering
         * the provided {@link ItemStack} in an inventory.
         */
        SHOW_ITEM,

        /**
         * Will show the player a tooltip containing the provided lines.
         * These lines can contain {@link org.bukkit.ChatColor} codes.
         */
        SHOW_TEXT,
        ;
    }

    /**
     * The Type of this Hover
     */
    private final Type type;

    /**
     * The object attached to this Hover
     */
    private final Object object;

    /**
     * Builds a Hover from the provided Type and object.
     *
     * @param type   the Type
     * @param object the object
     */
    private Hover(Type type, Object object) {
        this.type = type;
        this.object = object;
    }

    /**
     * Gets the Type of this Hover.
     *
     * @return the Type of this Hover
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets the Achievement attached to this Hover.
     * Returns null if the attached object isn't an Achievement.
     *
     * @return an Achievement if the attached object is an Achievement,
     * null otherwise
     */
    public Achievement getAchievement() {
        return this.type == Type.SHOW_ACHIEVEMENT ? (Achievement) this.object : null;
    }

    /**
     * Gets a copy of the ItemStack attached to this Hover.
     * Returns null if the attached object isn't an ItemStack.
     *
     * @return an ItemStack if the attached object is an ItemStack,
     * null otherwise
     */
    public ItemStack getItem() {
        return this.type == Type.SHOW_ITEM ? ((ItemStack) this.object).clone() : null;
    }

    /**
     * Gets a copy of the tooltip lines attached to this Hover.
     * Returns null if the attached object isn't a String array.
     *
     * @return the tooltip lines if the attached object is a String array,
     * null otherwise
     */
    public String[] getText() {
        return this.type == Type.SHOW_TEXT ? ((String[]) this.object).clone() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hover hover = (Hover) o;

        if (!object.equals(hover.object)) {
            return false;
        }
        if (type != hover.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + object.hashCode();
        return result;
    }
}
