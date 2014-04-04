package org.bukkit.plugin.localization;

import org.bukkit.plugin.LocaleManager;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class LocaleTestPlugin implements InvocationHandler {
    private static interface MethodHandler {
        Object handle(Plugin plugin, Object[] args);
    }

    private static final Constructor<? extends Plugin> constructor;
    private static final HashMap<Method, MethodHandler> methods = new HashMap<Method, MethodHandler>();
    private static LocaleManager localeManager = null;

    static {
        try {
            methods.put(
                    Plugin.class.getMethod("getLocaleManager"),
                    new MethodHandler() {
                        public Object handle(Plugin plugin, Object[] args) {
                            if(localeManager == null) {
                                localeManager = new LocaleManager(plugin);
                            }

                            return localeManager;
                        }
                    }
            );

            constructor = Proxy.getProxyClass(Plugin.class.getClassLoader(), Plugin.class).asSubclass(Plugin.class).getConstructor(InvocationHandler.class);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    private LocaleTestPlugin() {}

    public static Plugin getInstance() {
        try {
            localeManager = null;
            return constructor.newInstance(new LocaleTestPlugin());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodHandler handler = methods.get(method);
        if (handler != null) {
            return handler.handle((Plugin) proxy, args);
        }

        throw new UnsupportedOperationException(String.valueOf(method));
    }
}