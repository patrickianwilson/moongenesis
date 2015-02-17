package com.patrickwilson.moongenesis.context;

/**
 * Created by pwilson on 2/16/15.
 */
public interface ComponentFactory {
    /**
     * do any factory in this method and add the resulting objects to the registry.
     * @param registry
     */
    void configure(ComponentRegistry registry);
}
