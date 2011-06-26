package org.bukkit.event;

import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.util.AnnotationHelper;

/**
 * 
 * @author t3hk0d3
 */
public class EventExecutorFactory {
    
    protected static Map<Class<? extends Listener>, Map<Event.Type, Method>> eventHandlers =
            new ConcurrentHashMap<Class<? extends Listener>, Map<Event.Type, Method>>();
    
    public static EventExecutor getEventExecutor(Event.Type type, Listener listener){
        if(!eventHandlers.containsKey(listener.getClass())){
            eventHandlers.put(listener.getClass(), getEventHandlerMap(listener));
        }
        
        Map<Event.Type, Method> handlers = eventHandlers.get(listener.getClass());

        if(!handlers.containsKey(type)){ // No handler for this event type found. Fallback to onEvent
            return null;
        }
        
        final Method method = handlers.get(type);
                
        return new EventExecutor() {
            public void execute(Listener listener, Event event) {
                try {
                    method.invoke(listener, event);
                } catch (Exception e) {
                    throw new EventHandlerException(event.getType(), e);
                }
            }
        };   
    }
    
    public static void clearHandlers(){
        eventHandlers.clear();
    }
    
    protected static Map<Event.Type, Method> getEventHandlerMap(Listener listener){
        Map<Event.Type, Method> eventMap = new EnumMap<Event.Type, Method>(Event.Type.class);
        
        Map<EventHandler, Method> methods = AnnotationHelper.<EventHandler>getAnnotatedMethods(EventHandler.class, listener.getClass());
        
        for(Map.Entry<EventHandler, Method> entry : methods.entrySet()){
            eventMap.put(entry.getKey().value(), entry.getValue());
        }
        
        return eventMap;
    }
    
}
