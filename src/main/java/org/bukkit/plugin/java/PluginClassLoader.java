package org.bukkit.plugin.java;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * A ClassLoader for plugins, to allow shared classes across multiple plugins
 */
public class PluginClassLoader extends URLClassLoader {
	public PluginClassLoader(final ClassLoader parent) {
		super(new URL[0], parent);
	}
	
    public PluginClassLoader(final URL[] urls, final ClassLoader parent) {
        super(urls, parent);
    }

	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}
}
