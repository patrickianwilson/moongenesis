package com.patrickwilson.moongenesis.context;

/**
 * Created by pwilson on 2/16/15.
 */
public interface RegistryAware {

    public void setComponentRegistry(ComponentRegistry registry);
    public void onRegistryLoaded();
}
