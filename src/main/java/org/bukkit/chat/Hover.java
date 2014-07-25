package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

public final class Hover {
    
    public static Hover of(Achievement achievement) {
        return new Hover().setAchievement(achievement);
    }
    
    public static Hover of(ItemStack item) {
        return new Hover().setItem(item);
    }
    
    public static Hover of(String... lines) {
        return new Hover().setText(lines);
    }
    
    public enum Type {
        SHOW_ACHIEVEMENT,
        SHOW_ITEM,
        SHOW_TEXT,
        ;
    }
    
    private Type type;
    private Object object;
    
    Hover() {}

    public Type getType() {
        return this.type;
    }

    public Achievement getAchievement() {
        return this.type == Type.SHOW_ACHIEVEMENT ? (Achievement) this.object : null;
    }
    
    public ItemStack getItem() {
        return this.type == Type.SHOW_ITEM ? (ItemStack) this.object : null;
    }
    
    public String[] getText() {
        return this.type == Type.SHOW_TEXT ? (String[]) this.object : null;
    }

    public Hover setAchievement(Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        this.type = Type.SHOW_ACHIEVEMENT;
        this.object = achievement;
        return this;
    }
    
    public Hover setItem(ItemStack item) {
        Validate.notNull(item, "item can't be null");
        this.type = Type.SHOW_ITEM;
        this.object = item;
        return this;
    }
    
    public Hover setText(String... lines) {
        Validate.notEmpty(lines, "lines can't be empty");
        // TODO Support \n in Strings here if wanted
        this.type = Type.SHOW_TEXT;
        this.object = lines;
        return this;
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
