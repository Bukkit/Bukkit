package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Utility;

import java.util.Map;

/**
 * Represents a message part with a tooltip like the one which can be seen in
 * the Vanilla <b>"Achievement got"</b> message.
 * <p>
 * Note that the tooltip is fixed to the {@link Achievement} and cannot be
 * altered.
 */
public class AchievementMessagePart extends RichMessagePart {

    private Achievement achievement;

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement}.
     * <p>
     * The representation of the text message defaults to minecraft's
     * <b>"Achievement got"</b> design.
     *
     * @param achievement the {@link Achievement} that should be displayed
     */
    public AchievementMessagePart(Achievement achievement) {
        this(achievement, (String) null);
    }

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement} and the given {@link ClickAction}.
     * <p>
     * The representation of the text message defaults to minecraft's
     * <b>"Achievement got"</b> design.
     *
     * @param achievement the {@link Achievement} that should be displayed
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public AchievementMessagePart(Achievement achievement, ClickAction clickAction) {
        this(achievement, (String) null, clickAction);
    }

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement}.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param achievement the {@link Achievement} that should be displayed
     * @param customText the text of this {@link RichMessagePart}
     */
    public AchievementMessagePart(Achievement achievement, String customText) {
        super(Type.ACHIEVEMENT, customText);
        Validate.notNull(achievement, "AchievementMessagePart's can't be null");
        this.achievement = achievement;
    }

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement} and the given {@link ClickAction}.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param achievement the {@link Achievement} that should be displayed
     * @param customText the text of this {@link RichMessagePart}
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public AchievementMessagePart(Achievement achievement, String customText, ClickAction clickAction) {
        super(Type.ACHIEVEMENT, customText, clickAction);
        Validate.notNull(achievement, "AchievementMessagePart's can't be null");
        this.achievement = achievement;
    }

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement}.
     *
     * @param achievement the {@link Achievement} that should be displayed
     * @param customText the localized text of this {@link RichMessagePart}
     */
    public AchievementMessagePart(Achievement achievement, LocalizedText customText) {
        super(Type.ACHIEVEMENT, customText);
        Validate.notNull(achievement, "AchievementMessagePart's can't be null");
        this.achievement = achievement;
    }

    /**
     * Creates a new AchievementMessagePart with the given
     * {@link Achievement}.
     *
     * @param achievement the {@link Achievement} that should be displayed
     * @param customText the localized text of this {@link RichMessagePart}
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public AchievementMessagePart(Achievement achievement, LocalizedText customText, ClickAction clickAction) {
        super(Type.ACHIEVEMENT, customText, clickAction);
        Validate.notNull(achievement, "AchievementMessagePart's can't be null");
        this.achievement = achievement;
    }

    /**
     * Gets the {@link Achievement} connected to this
     * {@link RichMessagePart}.
     * <p>
     * The {@link Achievement}'s tooltip will be shown when the player hover
     * on the text of this {@link RichMessagePart}.
     *
     * @return the {@link Achievement} connected to this RichMessagePart
     */
    public Achievement getAchievement() {
        return achievement;
    }

    /**
     * Sets the {@link Achievement} connected to this
     * {@link RichMessagePart}.
     * <p>
     * The {@link Achievement}'s tooltip will be shown when the player hover
     * on the text of this {@link RichMessagePart}.
     *
     * @param achievement the new {@link Achievement} connected to this
     *     RichMessagePart
     */
    public void setAchievement(Achievement achievement) {
        Validate.notNull(achievement, "AchievementMessagePart's can't be null");
        this.achievement = achievement;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + achievement.hashCode();
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = super.serialize();
        result.put("achievement", achievement.name());
        return result;
    }
}
