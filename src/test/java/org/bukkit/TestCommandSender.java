package org.bukkit;

import org.bukkit.command.CommandSender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class TestCommandSender implements InvocationHandler {
    private static interface MethodHandler {
        Object handle(CommandSender plugin, Object[] args);
    }

    private static final Constructor<? extends CommandSender> constructor;
    private static final HashMap<Method, MethodHandler> methods = new HashMap<Method, MethodHandler>();

    static {
        try {
            constructor = Proxy.getProxyClass(CommandSender.class.getClassLoader(), CommandSender.class).asSubclass(CommandSender.class).getConstructor(InvocationHandler.class);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    private TestCommandSender() {}

    public static CommandSender getInstance() {
        try {
            return constructor.newInstance(new TestCommandSender());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodHandler handler = methods.get(method);
        if (handler != null) {
            return handler.handle((CommandSender) proxy, args);
        }

        throw new UnsupportedOperationException(String.valueOf(method));
    }
}
