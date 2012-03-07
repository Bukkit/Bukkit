package org.bukkit.conversations;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Conversation class is responsible for tracking the current state of a conversation, displaying prompts to
 * the user, and dispatching the user's response to the appropriate place. Conversation objects are not typically
 * instantiated directly. Instead a {@link ConversationFactory} is used to construct identical conversations on demand.
 *
 * Conversation flow consists of a directed graph of {@link Prompt} objects. Each time a prompt gets input from the
 * user, it must return the next prompt in the graph. Since each Prompt chooses the next Prompt, complex conversation
 * trees can be implemented where the nature of the player's response directs the flow of the conversation.
 *
 * Each conversation has a {@link ConversationPrefix} that prepends all output from the conversation to the player.
 * The ConversationPrefix can be used to display the plugin name or conversation status as the conversation evolves.
 *
 * Each conversation has a timeout measured in the number of inactive seconds to wait before abandoning the conversation.
 * If the inactivity timeout is reached, the conversation is abandoned and the user's incoming and outgoing chat is
 * returned to normal.
 *
 * You should not construct a conversation manually. Instead, use the {@link ConversationFactory} for access to all
 * available options.
 */
public class Conversation {

    private Prompt firstPrompt;
    private boolean abandoned;
    protected Prompt currentPrompt;
    protected ConversationContext context;
    protected boolean modal;
    protected ConversationPrefix prefix;
    protected List<ConversationCanceller> cancellers;
    protected boolean echoInput;

    /**
     * Initializes a new Conversation.
     * @param plugin The plugin that owns this conversation.
     * @param forWhom The entity for whom this conversation is mediating.
     * @param firstPrompt The first prompt in the conversation graph.
     */
    public Conversation(Plugin plugin, Conversable forWhom, Prompt firstPrompt) {
        this(plugin, forWhom, firstPrompt, new HashMap<Object, Object>());
    }

    /**
     * Initializes a new Conversation.
     * @param plugin The plugin that owns this conversation.
     * @param forWhom The entity for whom this conversation is mediating.
     * @param firstPrompt The first prompt in the conversation graph.
     * @param initialSessionData Any initial values to put in the conversation context sessionData map.
     */
    public Conversation(Plugin plugin, Conversable forWhom, Prompt firstPrompt, Map<Object, Object> initialSessionData) {
        this.firstPrompt = firstPrompt;
        this.context = new ConversationContext(plugin, forWhom, initialSessionData);
        this.modal = true;
        this.prefix = new NullConversationPrefix();
        this.cancellers = new ArrayList<ConversationCanceller>();
    }

    /**
     * Gets the entity for whom this conversation is mediating.
     * @return The entity.
     */
    public Conversable getForWhom() {
        return context.getForWhom();
    }

    /**
     * Gets the modality of this conversation. If a conversation is modal, all messages directed to the player
     * are suppressed for the duration of the conversation.
     * @return The conversation modality.
     */
    public boolean isModal() {
        return modal;
    }

    /**
     * Sets the modality of this conversation.  If a conversation is modal, all messages directed to the player
     * are suppressed for the duration of the conversation.
     * @param modal The new conversation modality.
     */
    void setModal(boolean modal) {
        this.modal = modal;
    }

    /**
     * Gets the {@link ConversationPrefix} that prepends all output from this conversation.
     * @return The ConversationPrefix in use.
     */
    public ConversationPrefix getPrefix() {
        return prefix;
    }

    /**
     * Sets the {@link ConversationPrefix} that prepends all output from this conversation.
     * @param prefix The ConversationPrefix to use.
     */
    void setPrefix(ConversationPrefix prefix) {
        this.prefix = prefix;
    }

    /**
     * Adds a {@link ConversationCanceller} to the cancellers collection.
     * @param canceller The {@link ConversationCanceller} to add.
     */
    void addConversationCanceller(ConversationCanceller canceller) {
        canceller.setConversation(this);
        this.cancellers.add(canceller);
    }

    /**
     * Gets the list of {@link ConversationCanceller}s
     * @return The list.
     */
    public List<ConversationCanceller> getCancellers() {
        return cancellers;
    }

    /**
     * Returns the Conversation's {@link ConversationContext}.
     * @return The ConversationContext.
     */
    public ConversationContext getContext() {
        return context;
    }

    /**
     * Displays the first prompt of this conversation and begins redirecting the user's chat responses.
     */
    public void begin() {
        if (currentPrompt == null) {
            abandoned = false;
            currentPrompt = firstPrompt;
            context.getForWhom().beginConversation(this);
        }
    }

    /**
     * Returns Returns the current state of the conversation.
     * @return The current state of the conversation.
     */
    public ConversationState getState() {
        if (currentPrompt != null) {
            return ConversationState.STARTED;
        } else if (abandoned) {
            return ConversationState.ABANDONED;
        } else {
            return ConversationState.UNSTARTED;
        }
    }

    /**
     * Returns whether or not the conversation will echo user input
     * @return true if the user's input will be echoed to them
     */
    public boolean willEchoInput(){
    	return echoInput;
    }
    
    /**
     * Sets whether or not to echo the user's input back to them
     * @param echoInput true to echo the user input
     */
    public void setEchoInput(boolean echoInput){
    	this.echoInput = echoInput;
    }
    
    /**
     * Passes player input into the current prompt. The next prompt (as determined by the current prompt) is then
     * displayed to the user.
     * @param input The user's chat text.
     */
    public void acceptInput(String input) {
        if (currentPrompt != null) {

            // Echo the user's input
            if(echoInput){
            	context.getForWhom().sendRawMessage(prefix.getPrefix(context) + input);
            }

            // Test for conversation abandonment based on input
            for(ConversationCanceller canceller : cancellers) {
                if (canceller.cancelBasedOnInput(context, input)) {
                    abandon();
                    return;
                }
            }

            // Not abandoned, output the next prompt
            currentPrompt = currentPrompt.acceptInput(context, input);
            outputNextPrompt();
        }
    }

    /**
     * Abandons and resets the current conversation. Restores the user's normal chat behavior.
     */
    public void abandon() {
        if (!abandoned) {
            abandoned = true;
            currentPrompt = null;
            context.getForWhom().abandonConversation(this);
        }
    }

    /**
     * Displays the next user prompt and abandons the conversation if the next prompt is null.
     */
    public void outputNextPrompt() {
        if (currentPrompt == null) {
            abandon();
        } else {
            context.getForWhom().sendRawMessage(prefix.getPrefix(context) + currentPrompt.getPromptText(context));
            if (!currentPrompt.blocksForInput(context)) {
                currentPrompt = currentPrompt.acceptInput(context, null);
                outputNextPrompt();
            }
        }
    }

    public enum ConversationState {
        UNSTARTED,
        STARTED,
        ABANDONED
    }
}
