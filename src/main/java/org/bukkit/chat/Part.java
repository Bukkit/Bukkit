package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

public final class Part {

    public static Part of(String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setText(text);
    }

    public static Part ofLocalized(String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setLocalizedText(id, parameters);
    }

    public static Part of(String text, String... hoverText) {
        Validate.notNull(text, "text can't be null");
        Validate.notEmpty(hoverText, "hoverText can't be empty");
        return new Part().setText(text).setHover(Hover.of(hoverText));
    }

    public static Part ofLocalized(String[] hoverText, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(hoverText)).setLocalizedText(id, parameters);
    }

    public static Part of(ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Part().setHover(Hover.of(item));
    }

    public static Part of(ItemStack item, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setHover(Hover.of(item)).setText(text);
    }

    public static Part ofLocalized(ItemStack item, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(item)).setLocalizedText(id, parameters);
    }

    public static Part of(Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Part().setHover(Hover.of(achievement));
    }

    public static Part of(Achievement achievement, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setHover(Hover.of(achievement)).setText(text);
    }

    public static Part ofLocalized(Achievement achievement, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(achievement)).setLocalizedText(id, parameters);
    }

    public static Part of(Click clickAction, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setText(text);
    }

    public static Part ofLocalized(Click clickAction, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setLocalizedText(id, parameters);
    }

    public static Part of(Click clickAction, String text, String... hoverText) {
        Validate.notNull(text, "text can't be null");
        Validate.notEmpty(hoverText, "hoverText can't be empty");
        return new Part().setClickAction(clickAction).setText(text).setHover(Hover.of(hoverText));
    }

    public static Part ofLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(hoverText)).setLocalizedText(id, parameters);
    }

    public static Part of(Click clickAction, ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item));
    }

    public static Part of(Click clickAction, ItemStack item, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item)).setText(text);
    }

    public static Part ofLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item)).setLocalizedText(id, parameters);
    }

    public static Part of(Click clickAction, Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement));
    }

    public static Part of(Click clickAction, Achievement achievement, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement)).setText(text);
    }

    public static Part ofLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement)).setLocalizedText(id, parameters);
    }

    private String text;

    private boolean localizedText;
    private String[] localizedTextParameters;

    private Hover hover;
    private Click clickAction;

    Part() {}

    public String getText() {
        return this.text;
    }

    public Part setText(String text) {
        Validate.notNull(text, "text can't be null");
        this.text = text;
        this.localizedText = false;
        return this;
    }

    public boolean isLocalizedText() {
        return this.localizedText;
    }

    public Part setLocalizedText(String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        this.text = id;
        this.localizedText = true;
        this.setLocalizedTextParameters(parameters);
        return this;
    }

    public String[] getLocalizedTextParameters() {
        return this.localizedTextParameters;
    }

    public Part setLocalizedTextParameters(String... parameters) {
        this.localizedTextParameters = parameters.length == 0 ? null : parameters;
        return this;
    }

    public Hover getHover() {
        return this.hover;
    }

    public Part setHover(Hover hover) {
        this.hover = hover;
        return this;
    }

    public Click getClickAction() {
        return this.clickAction;
    }

    public Part setClickAction(Click clickAction) {
        this.clickAction = clickAction;
        return this;
    }
}
