package org.bukkit.plugin.localization;

public class ResourceNotLoadedException extends Exception {
    /**
     * Constructs a new ResourceNotLoadedException based on the given Exception
     *
     * @param cause Exception that triggered this Exception
     */
    public ResourceNotLoadedException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ResourceNotLoadedException
     */
    public ResourceNotLoadedException() {

    }

    /**
     * Constructs a new ResourceNotLoadedException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ResourceNotLoadedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ResourceNotLoadedException with the specified detail message
     *
     * @param message TThe detail message is saved for later retrieval by the getMessage() method.
     */
    public ResourceNotLoadedException(final String message) {
        super(message);
    }
}
