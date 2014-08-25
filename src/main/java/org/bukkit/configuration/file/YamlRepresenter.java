package org.bukkit.configuration.file;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

public class YamlRepresenter extends Representer {

    public YamlRepresenter() {
        Map<Class<?>, Represent> reordered = new LinkedHashMap<Class<?>, Represent>();
        reordered.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable());
        reordered.put(ConfigurationSection.class, new RepresentConfigurationSection());
        reordered.putAll(this.multiRepresenters);
        this.multiRepresenters.clear();
        this.multiRepresenters.putAll(reordered);
    }

    private class RepresentConfigurationSection extends RepresentMap {
        @Override
        public Node representData(Object data) {
            return super.representData(((ConfigurationSection) data).getValues(false));
        }
    }

    private class RepresentConfigurationSerializable extends RepresentMap {
        @Override
        public Node representData(Object data) {
            ConfigurationSerializable serializable = (ConfigurationSerializable) data;
            Map<String, Object> values = new LinkedHashMap<String, Object>();
            values.put(ConfigurationSerialization.SERIALIZED_TYPE_KEY, ConfigurationSerialization.getAlias(serializable.getClass()));
            values.putAll(serializable.serialize());

            return super.representData(values);
        }
    }
}
