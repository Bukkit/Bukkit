package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A RichMessage is a chat message able to contain the different
 * modifiers used by vanilla which include:
 * <p>
 * <li>Chat formatting</li>
 * <li>Localized text</li>
 * <li>Chat actions (executed when the player clicks it)</li>
 * <li>Chat tooltips (displayed when the player hovers it)</li>
 * <p>
 * RichMessage structure and usage is based on the builder pattern
 * (similar to {@link StringBuilder}).
 * <p>
 * Chat formatting is done using {@link org.bukkit.ChatColor} as it is
 * done in every other message:
 * {@code String message = ChatColor.RED + "This message is red"; }
 * The text is localized using the vanilla identifiers (and parameters
 * if needed) with the {@link LocalizedText} class:
 * <pre> {@code
 * // Without parameters
 * new LocalizedText("stream.userinfo.unmod");
 * // or with parameters
 * new LocalizedText("commands.scoreboard.players.reset.success", "Notch");
 * }</pre>
 * All the chat actions are handled by
 * <p>
 * <li>{@link ChatAction}</li>
 * <li>{@link OpenUrlAction}</li>
 * <li>{@link SuggestChatAction}</li>
 * <p>
 * and the chat tooltips are handled by
 * <p>
 * <li>{@link CustomMessagePart}</li>
 * <li>{@link AchievementMessagePart}</li>
 * <li>{@link ItemMessagePart}</li>
 * <p>
 * A message part (which can be appended to a RichMessage) is an
 * instance of RichMessagePart containing text (localized or not),
 * a chat tooltip and a chat action.
 * <p>
 * Here are two examples of the RichMessage class usage:
 * <pre> {@code
 * // Message showing a golden apple tooltip
 * ItemStack is = new ItemStack(Material.GOLDEN_APPLE)
 * new RichMessage(ChatColor.RED + "Here is a golden apple: ")
 *     .append(new ItemMessagePart(is));
 *
 * // Message showing a text tooltip and triggering the "/me clicked" command
 * new RichMessage()
 *     .append(new CustomMessagePart(
 *         "Click me",
 *         new ChatAction("/me clicked"),
 *         "you are hovering"
 *     )
 * );
 * }</pre>
 */
public class RichMessage implements Iterable<RichMessagePart>, ConfigurationSerializable {

    protected final List<RichMessagePart> parts = new LinkedList<RichMessagePart>();

    /**
     * Builds an empty RichMessage.
     */
    public RichMessage() {
    }

    /**
     * Builds a RichMessage with one part.
     *
     * @param firstPart the first part of this RichMessage
     */
    public RichMessage(RichMessagePart firstPart) {
        this.parts.add(firstPart);
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add a {@link CustomMessagePart} containing
     * a text and optionally a tooltip.
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param text the text of the first CustomMessagePart
     * @param tooltipLines the tooltip of the first CustomMessagePart
     */
    public RichMessage(String text, String... tooltipLines) {
        this.append(new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add a {@link CustomMessagePart} containing
     * a localized text and optionally a tooltip.
     *
     * @param text the localized text of the first CustomMessagePart
     * @param tooltipLines the tooltip of the first CustomMessagePart
     */
    public RichMessage(LocalizedText text, String... tooltipLines) {
        this.append(new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add a {@link CustomMessagePart} containing
     * a text, a {@link ClickAction} and optionally a tooltip.
     *
     * @param text the text of the first CustomMessagePart
     * @param clickAction the action executed when the player click on the
     *     first CustomMessagePart
     * @param tooltipLines the tooltip of the first CustomMessagePart
     */
    public RichMessage(String text, ClickAction clickAction, String... tooltipLines) {
        this.append(new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add a {@link CustomMessagePart} containing
     * a localized text, a {@link ClickAction} and optionally a tooltip.
     *
     * @param text the localized text of the first CustomMessagePart
     * @param clickAction the action executed when the player click on the
     *     first CustomMessagePart
     * @param tooltipLines the tooltip of the first CustomMessagePart
     */
    public RichMessage(LocalizedText text, ClickAction clickAction, String... tooltipLines) {
        this.append(new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an ItemStack.
     *
     * @param item the item of the first ItemMessagePart
     */
    public RichMessage(ItemStack item) {
        this.append(new ItemMessagePart(item));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an ItemStack and a {@link ClickAction}.
     *
     * @param item the item of the first ItemMessagePart
     * @param clickAction the action executed when the player click on the
     *     first ItemMessagePart
     */
    public RichMessage(ItemStack item, ClickAction clickAction) {
        this.append(new ItemMessagePart(item, clickAction));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     */
    public RichMessage(ItemStack item, String customText) {
        this.append(new ItemMessagePart(item, customText));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     */
    public RichMessage(ItemStack item, LocalizedText customText) {
        this.append(new ItemMessagePart(item, customText));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text
     * and which triggers an action when clicked client side.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     first ItemMessagePart
     */
    public RichMessage(ItemStack item, String customText, ClickAction clickAction) {
        this.append(new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text and which triggers an action when clicked client side.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     first ItemMessagePart
     */
    public RichMessage(ItemStack item, LocalizedText customText, ClickAction clickAction) {
        this.append(new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat.
     *
     * @param achievement the achievement to display in the chat
     */
    public RichMessage(Achievement achievement) {
        this.append(new AchievementMessagePart(achievement));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat
     * and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param clickAction the action executed when the player click on the
     *     first AchievementMessagePart
     */
    public RichMessage(Achievement achievement, ClickAction clickAction) {
        this.append(new AchievementMessagePart(achievement, clickAction));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom
     * text.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     */
    public RichMessage(Achievement achievement, String customText) {
        this.append(new AchievementMessagePart(achievement, customText));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     */
    public RichMessage(Achievement achievement, LocalizedText customText) {
        this.append(new AchievementMessagePart(achievement, customText));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom text
     * and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     first AchievementMessagePart
     */
    public RichMessage(Achievement achievement, String customText, ClickAction clickAction) {
        this.append(new AchievementMessagePart(achievement, customText, clickAction));
    }

    /**
     * Builds a RichMessage with one part.
     * <p>
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     first AchievementMessagePart
     */
    public RichMessage(Achievement achievement, LocalizedText customText, ClickAction clickAction) {
        this.append(new AchievementMessagePart(achievement, customText, clickAction));
    }

    /**
     * Gets the parts of this RichMessage.
     *
     * @return the parts of this RichMessage
     */
    public List<RichMessagePart> getParts() {
        return this.parts;
    }

    /**
     * Append a {@link RichMessagePart} to the current RichMessage.
     *
     * @param part the part to append to the message
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage append(RichMessagePart part) {
        Validate.notNull(part, "A rich message part can't be null");
        this.parts.add(part);
        return this;
    }

    /**
     * Append some text to the current RichMessage which can be hovered,
     * displaying a text tooltip.
     *
     * @param text the text to append to the message
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage append(String text, String... tooltipLines) {
        return this.append(new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Append some localized text to the current RichMessage which can be
     * hovered, displaying a text tooltip.
     *
     * @param text the localized text to append to the message
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage append(LocalizedText text, String... tooltipLines) {
        return this.append(new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Append some text to the current RichMessage which can be
     * hovered, displaying a text tooltip and which triggers an action
     * when clicked client side.
     *
     * @param text the text to append to the message
     * @param clickAction the action executed when the player click on the
     *     CustomMessagePart
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage append(String text, ClickAction clickAction, String... tooltipLines) {
        return this.append(new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * Append some localized text to the current RichMessage which can be
     * hovered, displaying a text tooltip and which triggers an action
     * when clicked client side.
     *
     * @param text the localized text to append to the message
     * @param clickAction the action executed when the player click on the
     *     CustomMessagePart
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage append(LocalizedText text, ClickAction clickAction, String... tooltipLines) {
        return this.append(new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an ItemStack.
     *
     * @param item the item of the first ItemMessagePart
     */
    public RichMessage append(ItemStack item) {
        return this.append(new ItemMessagePart(item));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an ItemStack and a {@link ClickAction}.
     *
     * @param item the item of the first ItemMessagePart
     * @param clickAction the action executed when the player click on the
     *     first ItemMessagePart
     */
    public RichMessage append(ItemStack item, ClickAction clickAction) {
        return this.append(new ItemMessagePart(item, clickAction));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     */
    public RichMessage append(ItemStack item, String customText) {
        return this.append(new ItemMessagePart(item, customText));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     */
    public RichMessage append(ItemStack item, LocalizedText customText) {
        return this.append(new ItemMessagePart(item, customText));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text
     * and which triggers an action when clicked client side.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     ItemMessagePart
     */
    public RichMessage append(ItemStack item, String customText, ClickAction clickAction) {
        return this.append(new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * This method is a shortcut to add an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text and which triggers an action when clicked client side.
     *
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     ItemMessagePart
     */
    public RichMessage append(ItemStack item, LocalizedText customText, ClickAction clickAction) {
        return this.append(new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat.
     *
     * @param achievement the achievement to display in the chat
     */
    public RichMessage append(Achievement achievement) {
        return this.append(new AchievementMessagePart(achievement));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat
     * and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     */
    public RichMessage append(Achievement achievement, ClickAction clickAction) {
        return this.append(new AchievementMessagePart(achievement, clickAction));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom
     * text.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     */
    public RichMessage append(Achievement achievement, String customText) {
        return this.append(new AchievementMessagePart(achievement, customText));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     */
    public RichMessage append(Achievement achievement, LocalizedText customText) {
        return this.append(new AchievementMessagePart(achievement, customText));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom text
     * and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     */
    public RichMessage append(Achievement achievement, String customText, ClickAction clickAction) {
        return this.append(new AchievementMessagePart(achievement, customText, clickAction));
    }

    /**
     * This method is a shortcut to add an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text and which triggers an action when clicked client side.
     *
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     */
    public RichMessage append(Achievement achievement, LocalizedText customText, ClickAction clickAction) {
        return this.append(new AchievementMessagePart(achievement, customText, clickAction));
    }

    /**
     * Insert a {@link RichMessagePart} to the current RichMessage
     * at the provided position.
     *
     * @param index the position the insert the part
     * @param part the part to insert to the message
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, RichMessagePart part) {
        Validate.notNull(part, "A rich message part can't be null");
        this.parts.add(index, part);
        return this;
    }

    /**
     * Insert some text to the current RichMessage which can be hovered,
     * displaying a text tooltip at the provided position.
     *
     * @param index the position the insert the text
     * @param text the text to insert to the message
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, String text, String... tooltipLines) {
        return this.insert(index, new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Insert some localized text to the current RichMessage which can be
     * hovered, displaying a text tooltip at the provided position.
     *
     * @param index the position the insert the text
     * @param text the localized text to append to the message
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, LocalizedText text, String... tooltipLines) {
        return this.insert(index, new CustomMessagePart(text, tooltipLines));
    }

    /**
     * Insert some text to the current RichMessage which can be
     * hovered, displaying a text tooltip and which triggers an action
     * when clicked client side at the provided position.
     *
     * @param index the position the insert the text
     * @param text the text to insert to the message
     * @param clickAction the action executed when the player click on the
     *     CustomMessagePart
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, String text, ClickAction clickAction, String... tooltipLines) {
        return this.insert(index, new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * Insert some localized text to the current RichMessage which can be
     * hovered, displaying a text tooltip and which triggers an action
     * when clicked client side at the provided position.
     *
     * @param index the position the insert the text
     * @param text the localized text to insert to the message
     * @param clickAction the action executed when the player click on the
     *     CustomMessagePart
     * @param tooltipLines the lines of the tooltip to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, LocalizedText text, ClickAction clickAction, String... tooltipLines) {
        return this.insert(index, new CustomMessagePart(text, clickAction, tooltipLines));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an ItemStack at the provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item) {
        return this.insert(index, new ItemMessagePart(item));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an ItemStack and a {@link ClickAction} at the provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @param clickAction the action executed when the player click on the
     *     first ItemMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item, ClickAction clickAction) {
        return this.insert(index, new ItemMessagePart(item, clickAction));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text
     * at the provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item, String customText) {
        return this.insert(index, new ItemMessagePart(item, customText));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text at the provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item, LocalizedText customText) {
        return this.insert(index, new ItemMessagePart(item, customText));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom text
     * and which triggers an action when clicked client side at the provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @param customText the text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     ItemMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item, String customText, ClickAction clickAction) {
        return this.insert(index, new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * This method is a shortcut to insert an {@link ItemMessagePart} containing
     * an {@link org.bukkit.inventory.ItemStack} which appears when hovering a custom
     * localized text and which triggers an action when clicked client side at the
     * provided position.
     *
     * @param index the position the insert the ItemMessagePart
     * @param item the item of the first ItemMessagePart
     * @param customText the localized text to hover to display the ItemStack
     * @param clickAction the action executed when the player click on the
     *     ItemMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, ItemStack item, LocalizedText customText, ClickAction clickAction) {
        return this.insert(index, new ItemMessagePart(item, customText, clickAction));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat at the
     * provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement) {
        return this.insert(index, new AchievementMessagePart(achievement));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovered in the chat
     * and which triggers an action when clicked client side at the provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement, ClickAction clickAction) {
        return this.insert(index, new AchievementMessagePart(achievement, clickAction));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom
     * text at the provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement, String customText) {
        return this.insert(index, new AchievementMessagePart(achievement, customText));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text at the provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement, LocalizedText customText) {
        return this.insert(index, new AchievementMessagePart(achievement, customText));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom text
     * and which triggers an action when clicked client side at the provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @param customText the text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement, String customText, ClickAction clickAction) {
        return this.insert(index, new AchievementMessagePart(achievement, customText, clickAction));
    }

    /**
     * This method is a shortcut to insert an {@link AchievementMessagePart} containing
     * an {@link org.bukkit.Achievement} which appears when hovering a custom localized
     * text and which triggers an action when clicked client side at the provided position.
     *
     * @param index the position the insert the AchievementMessagePart
     * @param achievement the achievement to display in the chat
     * @param customText the localized text to hover to display the achievement
     * @param clickAction the action executed when the player click on the
     *     AchievementMessagePart
     * @return the RichMessage instance allowing call chaining
     */
    public RichMessage insert(int index, Achievement achievement, LocalizedText customText, ClickAction clickAction) {
        return this.insert(index, new AchievementMessagePart(achievement, customText, clickAction));
    }

    @Override
    public Iterator<RichMessagePart> iterator() {
        return this.parts.iterator();
    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        List<Map<String, Object>> serializedParts = new LinkedList<Map<String, Object>>();
        for (ConfigurationSerializable part : parts) {
            serializedParts.add(part.serialize());
        }
        result.put("parts", serializedParts);

        return result;
    }

    /**
     * Required method for configuration serialization.
     *
     * @param args map to deserialize
     * @return deserialized rich message
     * @see ConfigurationSerializable
     */
    public static RichMessage deserialize(Map<String, Object> args) {
        RichMessage result = new RichMessage();

        List<Map<String, Object>> parts = (List<Map<String, Object>>) args.get("parts");

        for (Map<String, Object> part : parts) {
            result.append(RichMessagePart.deserialize(part));
        }

        return result;
    }
}
