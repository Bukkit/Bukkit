package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Utility;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * The open url action will open an url using the default client's browser
 * when clicked client side.
 */
public class OpenUrlAction extends ClickAction {

    /**
     * The OPEN_URL action only allows <code>http</code> and
     * <code>https</code> links.
     */
    private static final Pattern HTTP_REGEX = Pattern.compile("^https?://.*", Pattern.CASE_INSENSITIVE);

    private String url;

    /**
     * Builds a new OpenUrlAction, which will open an url using the default
     * client's browser when clicked client side.
     *
     * @param url the url that should be opened by the client
     */
    public OpenUrlAction(String url) {
        super(Type.OPEN_URL);
        Validate.notNull(url, "OpenUrlAction's url can't be null");
        if (!HTTP_REGEX.matcher(url).matches()) {
            throw new IllegalArgumentException("Valid http/https URL required");
        }
        this.url = url;
    }

    /**
     * Gets the url that should be opened by the client when clicked client
     * side.
     *
     * @return the url that should be opened by the client
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        Validate.notNull(url, "OpenUrlAction's url can't be null");
        this.url = url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = super.serialize();
        result.put("url", url);
        return result;
    }

    /**
     * Required method for configuration serialization.
     *
     * @param args map to deserialize
     * @return deserialized OpenUrlAction
     * @see org.bukkit.configuration.serialization.ConfigurationSerializable
     */
    public static OpenUrlAction deserialize(Map<String, Object> args) {
        return new OpenUrlAction((String) args.get("url"));
    }
}
