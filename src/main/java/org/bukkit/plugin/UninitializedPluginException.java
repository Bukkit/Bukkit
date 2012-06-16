package org.bukkit.plugin;

/**
 * This exception should be thrown when a plugin's fields are null because
 * it has not been initialized. A common cause is calling a getter method
 * in a field initializer or a static initialization block.
 */
@SuppressWarnings("serial")
public class UninitializedPluginException extends RuntimeException {
    private final String message;

    /**
     * Constructs a new UnresolvedPluginResourceException with the given
     * message.
     * 
     * @param message
     *            Brief message explaining the cause of the exception
     */
    public UninitializedPluginException(final String message) {
        this.message = message;
    }

    /**
     * Constructs a new UnresolvedPluginResourceException with the
     * message "Plugin has not been initialized".
     */
    public UninitializedPluginException() {
        this.message = "Plugin has not been initialized";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
