package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a localized text.
 * <p>
 * This may be used to send localized text to Minecraft Client through
 * {@link RichMessage}s.
 * <p>
 * Note that adding new localized texts to Minecraft requires the server to
 * send a resource package to the client containing these texts.
 */
public class LocalizedText implements ConfigurationSerializable {

    private final String id;

    private Object[] parameters;

    /**
     * Builds a LocalizedText based on the id of the localized text
     * and optional parameters.
     *
     * @param id the id of the localized text
     * @param parameters the parameters for this localized text
     */
    public LocalizedText(String id, Object... parameters) {
        Validate.notNull(id, "LocalizedText's id can't be null");
        this.id = id;
        this.parameters = parameters;
    }

    /**
     * Gets the id of this localized text.
     *
     * @return the id of this localized text
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the parameters of this localized text.
     * <p>
     * Note that this will never return null, but an empty array instead.
     *
     * @return the parameters of this localized text
     */
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters of this localized text.
     * <p>
     * Note that providing a <code>null</code> value will set the parameters
     * array to an empty array.
     *
     * @param parameters the new parameters of this localized text
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters == null ? new Object[0] : parameters;
    }

    /**
     * Modify a single parameter of this localized text.
     * <p>
     * Cannot be used to add or remove parameters, only to replace existing
     * parameters.
     *
     * @param i the index of the parameter to change
     * @param parameter the new value of the parameter
     */
    public void setParameter(int i, Object parameter) {
        Validate.notNull(parameter, "Cannot set an existing parameter to null");
        Validate.isTrue(i >= 0 && i < this.parameters.length, "Invalid index '" + i + "'");
        this.parameters[i] = parameter;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (parameters != null ? Arrays.hashCode(parameters) : 0);
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("id", id);
        if (parameters.length > 0) {
            result.put("parameters", Arrays.asList(parameters));
        }
        return result;
    }

    /**
     * Required method for configuration serialization.
     *
     * @param args map to deserialize
     * @return deserialized LocalizedText
     * @see org.bukkit.configuration.serialization.ConfigurationSerializable
     */
    public static LocalizedText deserialize(Map<String, Object> args) {
        LocalizedText result = new LocalizedText((String) args.get("id"));
        if (args.containsKey("parameters")) {
            result.setParameters(((List<Object>) args.get("parameters")).toArray(new Object[0]));
        }
        return result;
    }
}
