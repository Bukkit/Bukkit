package org.bukkit.plugin.localization;

import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class InMemoryResourceLoader implements ResourceLoader {
    private String translateString;

    public InMemoryResourceLoader() {}
    public InMemoryResourceLoader(Plugin plugin, String load) {
        translateString = load.split("\\:")[0];
    }

    @Override
    public List<String> getKeys() {
        return Arrays.asList("test");
    }

    @Override
    public String get(String key) {
        return key.equals("test") ? translateString : null;
    }

    @Override
    public List<String> getFormats() {
        return Arrays.asList(":inmemory");
    }

    @Override
    public void reload() throws ResourceLoadFailedException {
        return;
    }

    @Override
    public void cleanup() {
        return;
    }
}
