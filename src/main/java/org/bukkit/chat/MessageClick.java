package org.bukkit.chat;

import org.apache.commons.lang.Validate;

import java.util.regex.Pattern;

public class MessageClick {
    
    private static final Pattern HTTP_REGEX = Pattern.compile("^https?://.*", Pattern.CASE_INSENSITIVE);

    public static MessageClick ofOpenURL(String url) {
        Validate.isTrue(HTTP_REGEX.matcher(url).matches(), "Provided url is invalid: " + url);
        return forType(Type.OPEN_URL, url);
    }

    public static MessageClick ofSendText(String text) {
        return forType(Type.SEND_TEXT, text);
    }

    public static MessageClick ofSetText(String text) {
        return forType(Type.SET_TEXT, text);
    }

    private static MessageClick forType(Type type, String action) {
        Validate.notEmpty(action);
        return new MessageClick(type, action);
    }

    public enum Type {
        OPEN_URL,
        SEND_TEXT,
        SET_TEXT,
    }

    private final Type type;
    private final String text;

    private MessageClick(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }
}
