package org.bukkit.plugin.java;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * A ClassLoader that allows referencing classes in other plugins
 */
public class PluginClassLoader extends URLClassLoader {

    private final JavaPluginLoader loader;

    public PluginClassLoader(final JavaPluginLoader loader, final URL[] urls, final ClassLoader parent) {
        super(urls, parent);

        this.loader = loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            return super.findClass(name);
        }
        catch (ClassNotFoundException ex) {
            for (PluginClassLoader neighbor : loader.getClassLoaders()) {
                if (neighbor != this) {
                    try {
                        return neighbor.loadClassDirect(name);
                    }
                    catch (ClassNotFoundException ex2) {}
                }
            }
            throw ex;
        }
    }

    /**
     * Variant of loadClass used to query a neighbor plugin's loader.
     *
     * Delegation is skipped, because ClassLoaders of plugins all have the
     * same parent, and we can assume that has already been checked.
     *
     * This also doesn't recurse into {@link #findClass(String)}, but
     * instead calls directly into URLClassLoader#findClass(String). This is
     * to prevent infinite recursion.
     */
    private Class<?> loadClassDirect(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            c = super.findClass(name);
        }
        return c;
    }

}
