package org.bukkit.plugin.localization;

import java.util.List;

public interface ResourceLoader {
    public List<String> getKeys();
    public String get(String key);
    public List<String> getFormats();
    public void reload() throws ResourceLoadFailedException;
    public void cleanup();
}
