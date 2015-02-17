package com.patrickwilson.moongenesis.context;

/**
 * Created by pwilson on 2/16/15.
 */
public abstract class EngineContextLauncher {

    public void startContext() {
        ComponentRegistry registry = new ComponentRegistry();
        for (ComponentFactory factory: getComponentFactories()) {
            factory.configure(registry);
        }

        registry.notifyContextInitialized();

        for (ContextListener listener: getContextListeners()) {
            listener.onContextInitialized(registry);
        }

    }

    protected abstract ComponentFactory[] getComponentFactories();

    protected abstract ContextListener[] getContextListeners();
}
