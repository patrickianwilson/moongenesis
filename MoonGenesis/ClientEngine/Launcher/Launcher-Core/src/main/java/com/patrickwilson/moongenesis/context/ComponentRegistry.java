package com.patrickwilson.moongenesis.context;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pwilson on 2/16/15.
 */
public class ComponentRegistry {

    Map<String, Object> registry = new TreeMap<>();
    LinkedList<RegistryAware> registryAwareBeans = new LinkedList<>();

    public void registerObject(Object o) {
        registry.put(o.getClass().getCanonicalName(), o);
    }

    public <T> void registerObject(Class<T> type, T o) {
        registry.put(type.getCanonicalName(), o);
        if (o instanceof RegistryAware) {
            ((RegistryAware)o).setComponentRegistry(this);
            registryAwareBeans.add(((RegistryAware)o));
        }
    }

    public <T> void registerObject(Class<T> type, String name, T o) {
        registry.put(type.getCanonicalName() + ":" + name, o);
        if (o instanceof RegistryAware) {
            ((RegistryAware)o).setComponentRegistry(this);
            registryAwareBeans.add(((RegistryAware)o));
        }
    }

    public <T> T getObject(Class<T> type) {
        return (T)registry.get(type.getCanonicalName());
    }

    public <T> T getObject(Class<T> type, String name) {
        return (T)registry.get(type.getCanonicalName() + ":" + name);
    }

    public void notifyContextInitialized() {
        for (RegistryAware bean: registryAwareBeans) {
            bean.onRegistryLoaded();
        }
    }


}
