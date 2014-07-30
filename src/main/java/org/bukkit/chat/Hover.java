package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

public final class Hover {

    public static Hover of(Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Hover(Type.SHOW_ACHIEVEMENT, achievement);
    }

    public static Hover of(ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Hover(Type.SHOW_ITEM, item.clone());
    }

    public static Hover of(String... lines) {
        Validate.notEmpty(lines, "lines can't be empty");
        // TODO Support \n in Strings here if wanted
        return new Hover(Type.SHOW_TEXT, lines.clone());
    }

    public enum Type {
        SHOW_ACHIEVEMENT,
        SHOW_ITEM,
        SHOW_TEXT,
        ;
    }

    private final Type type;
    private final Object object;

    private Hover(Type type, Object object) {
        this.type = type;
        this.object = object;
    }

    public Type getType() {
        return this.type;
    }

    public Achievement getAchievement() {
        return this.type == Type.SHOW_ACHIEVEMENT ? (Achievement) this.object : null;
    }

    public ItemStack getItem() {
        return this.type == Type.SHOW_ITEM ? ((ItemStack) this.object).clone() : null;
    }

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
