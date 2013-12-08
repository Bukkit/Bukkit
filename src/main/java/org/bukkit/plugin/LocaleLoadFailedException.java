package org.bukkit.plugin;

public class LocaleLoadFailedException extends Exception {
    /**
     * Constructs a new LocaleLoadFailedException based on the given Exception
     *
     * @param cause Exception that triggered this Exception
     */
    public LocaleLoadFailedException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidPluginException
     */
    public LocaleLoadFailedException() {

    }

    /**
     * Constructs a new LocaleLoadFailedException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public LocaleLoadFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new LocaleLoadFailedException with the specified detail message
     *
     * @param message The detail message is saved for later retrieval by the getMessage() method.
     */
    public LocaleLoadFailedException(final String message) {
        super(message);
    }
}
