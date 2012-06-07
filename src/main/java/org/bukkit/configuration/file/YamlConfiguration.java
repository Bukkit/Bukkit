package org.bukkit.configuration.file;

import java.io.*;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

/**
 * An implementation of {@link Configuration} which saves all files in Yaml.
 * Note that this implementation is not synchronized.
 */
public class YamlConfiguration extends FileConfiguration {

    protected static final String COMMENT_PREFIX = "# ";
    protected static final String BLANK_CONFIG = "{}\n";
    private final DumperOptions yamlOptions = new DumperOptions();
    private final Representer yamlRepresenter = new YamlRepresenter();
    private final Yaml yaml = new Yaml(new YamlConstructor(), yamlRepresenter, yamlOptions);

    @Override
    public void save(Writer out) throws IOException {
        yamlOptions.setIndent(options().indent());
        yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        // write header
        out.write(buildHeader());

        // write configuration body
        Map<String, Object> data = getValues(false);
        if(!data.isEmpty()) {
            yaml.dump(data, out);
        }

        out.flush();
    }

    @Override
    public void load(Reader in) throws IOException, InvalidConfigurationException {
        if (in == null) {
            throw new IllegalArgumentException("Input reader cannot be null");
        }

        BufferedReader reader = new BufferedReader(in);

        String header = parseHeader(reader);
        if (!header.isEmpty()) {
            options().header(header);
        }

        try {
            convertMapsToSections((Map<Object, Object>) yaml.load(reader), this);
        } catch (YAMLException e) {
            throw new InvalidConfigurationException(e);
        } catch (ClassCastException e) {
            throw new InvalidConfigurationException("Top level is not a Map.");
        }
    }

    @SuppressWarnings("unchecked")
    protected void convertMapsToSections(Map<Object, Object> input, ConfigurationSection section) {
        if (input == null) {
            return;
        }    

        for (Map.Entry<Object, Object> entry : input.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if (value instanceof Map<?, ?>) {
                convertMapsToSections((Map<Object, Object>) value, section.createSection(key));
            } else {
                section.set(key, value);
            }
        }
    }

    protected String parseHeader(BufferedReader reader) throws IOException {
        StringBuilder result = new StringBuilder();
        boolean foundHeader = false;

        reader.mark(512);
        
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(COMMENT_PREFIX)) {    
                if (line.length() > COMMENT_PREFIX.length()) {
                    result.append(line.substring(COMMENT_PREFIX.length()));
                }

                result.append("\n");

                foundHeader = true;
            } else if (foundHeader && line.isEmpty()) { // whitespace
                result.append("\n");
            } else if (foundHeader || (!foundHeader && !line.isEmpty())) { // header is already readed or actual yaml data begins
                reader.reset(); // return to state before reading line with actual data
                break;
            }

            reader.mark(512);
        }

        return result.toString();
    }

    @Override
    protected String buildHeader() {
        String header = options().header();

        if (options().copyHeader()) {
            Configuration def = getDefaults();

            if ((def != null) && (def instanceof FileConfiguration)) {
                FileConfiguration filedefaults = (FileConfiguration) def;
                String defaultsHeader = filedefaults.buildHeader();

                if ((defaultsHeader != null) && (defaultsHeader.length() > 0)) {
                    return defaultsHeader;
                }
            }
        }

        if (header == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        String[] lines = header.split("\r?\n", -1);
        boolean startedHeader = false;

        for (int i = lines.length - 1; i >= 0; i--) {
            if (i < lines.length - 1) {
                builder.insert(0, "\n");
            }

            if ((startedHeader) || (lines[i].length() != 0)) {
                builder.insert(0, lines[i]);
                builder.insert(0, COMMENT_PREFIX);
                startedHeader = true;
            }
        }

        return builder.toString();
    }

    @Override
    public YamlConfigurationOptions options() {
        if (options == null) {
            options = new YamlConfigurationOptions(this);
        }

        return (YamlConfigurationOptions) options;
    }

    /**
     * Creates a new {@link YamlConfiguration}, loading from the given file.
     * <p />
     * Any errors loading the Configuration will be logged and then ignored.
     * If the specified input is not a valid config, a blank config will be returned.
     *
     * @param file Input file
     * @return Resulting configuration
     * @throws IllegalArgumentException Thrown if file is null
     */
    public static YamlConfiguration loadConfiguration(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        } catch (InvalidConfigurationException ex) {
            if (ex.getCause() instanceof YAMLException) {
                Bukkit.getLogger().severe("Config file " + file + " isn't valid! " + ex.getCause());
            } else if ((ex.getCause() == null) || (ex.getCause() instanceof ClassCastException)) {
                Bukkit.getLogger().severe("Config file " + file + " isn't valid!");
            } else {
                Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file + ": " + ex.getCause().getClass(), ex);
            }
        }

        return config;
    }

    /**
     * Creates a new {@link YamlConfiguration}, loading from the given stream.
     * <p />
     * Any errors loading the Configuration will be logged and then ignored.
     * If the specified input is not a valid config, a blank config will be returned.
     *
     * @param stream Input stream
     * @return Resulting configuration
     * @throws IllegalArgumentException Thrown if stream is null
     */
    public static YamlConfiguration loadConfiguration(InputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream cannot be null");
        }

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(stream);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration", ex);
        } catch (InvalidConfigurationException ex) {
            if (ex.getCause() instanceof YAMLException) {
                Bukkit.getLogger().severe("Config file isn't valid! " + ex.getCause());
            } else if ((ex.getCause() == null) || (ex.getCause() instanceof ClassCastException)) {
                Bukkit.getLogger().severe("Config file isn't valid!");
            } else {
                Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration: " + ex.getCause().getClass(), ex);
            }
        }

        return config;
    }
}
