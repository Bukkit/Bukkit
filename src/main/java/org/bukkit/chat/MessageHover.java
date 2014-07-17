package org.bukkit.chat;

import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

public class MessageHover {

    public static MessageHover of(Achievement achievement) {
        return new MessageHover(Type.SHOW_ACHIEVEMENT, achievement);
    }

    public static MessageHover of(ItemStack item) {
        return new MessageHover(Type.SHOW_ITEM, item.clone());
    }

    public static MessageHover of(String... textLines) {
        return new MessageHover(Type.SHOW_TEXT, textLines.clone());
    }

    public enum Type {
        SHOW_ACHIEVEMENT,
        SHOW_ITEM,
        SHOW_TEXT,
    }

    private final Type type;
    private final Object object;

    private MessageHover(Type type, Object value) {
        this.type = type;
        this.object = value;
    }

    public Type getType() {
        return this.type;
    }

    public Object getValue() {
        // ItemStacks are mutable, so we return a copy.
        return this.type == Type.SHOW_ITEM ? ((ItemStack) this.object).clone() : this.object;
    }


}
