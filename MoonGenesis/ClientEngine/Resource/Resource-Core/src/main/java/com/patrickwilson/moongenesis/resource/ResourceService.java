package com.patrickwilson.moongenesis.resource;

/**
 * Created by pwilson on 2/16/15.
 */
public interface ResourceService {

    Resource getJsonResource(String path, Class<?> type);

}
