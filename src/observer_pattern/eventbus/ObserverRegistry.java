package observer_pattern.eventbus;


import observer_pattern.normal.Observer;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

public class ObserverRegistry {

    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    public void register(Object observer){
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry: observerActions.entrySet()){
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();
            CopyOnWriteArraySet<ObserverAction> registerEventActions = registry.get(eventType);
            if(registerEventActions == null){
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registerEventActions = registry.get(eventType);
            }
            registerEventActions.addAll(eventActions);
        }

    }

    public List<ObserverAction> getMatchedObserverActions(Object event){
        List<ObserverAction> matchedObserverActions = new ArrayList<>();
        Class<?> postedEventType = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry: registry.entrySet()){
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();
            //if(postedEventType.isAssignableFrom(eventType)){
                if(eventType.isAssignableFrom(postedEventType)){

                    matchedObserverActions.addAll(eventActions);
            }
        }
        return matchedObserverActions;
    }

    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer){
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethods(clazz)){
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            if(!observerActions.containsKey(eventType)){
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer,method));
        }
        return observerActions;
    }

    private List<Method> getAnnotatedMethods(Class<?> clazz){
        List<Method> annotatedMethods = new ArrayList<>();
        for ( Method method : clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(Subscribe.class)){
                Class<?>[] parameterTypes = method.getParameterTypes();
                // TODO PreConditions.checkArgument(blablabla)
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
