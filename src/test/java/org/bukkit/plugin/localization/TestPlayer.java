package org.bukkit.plugin.localization;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;


public class TestPlayer implements InvocationHandler {
    public static void setLocale(String locale) {
        TestPlayer.locale = locale;
    }

    private static interface MethodHandler {
        Object handle(TestPlayer player, Object[] args);
    }
    private static final Constructor<? extends Player> constructor;
    private static final HashMap<Method, MethodHandler> methods = new HashMap<Method, MethodHandler>();
    private static String locale = "de_DE";

    static {
        try {

            methods.put(Player.class.getMethod("getLocale"),
                new MethodHandler() {
                    public Object handle(TestPlayer player, Object[] args) {
                        return locale;
                    }
                });

            constructor = Proxy.getProxyClass(Player.class.getClassLoader(), Player.class).asSubclass(Player.class).getConstructor(InvocationHandler.class);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    private TestPlayer() {}

    public static Player getInstance() {
        try {
            locale = "de_DE";
            return constructor.newInstance(new TestPlayer());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodHandler handler = methods.get(method);
        if (handler != null) {
            return handler.handle(this, args);
        }

        throw new UnsupportedOperationException(String.valueOf(method));
    }
}
