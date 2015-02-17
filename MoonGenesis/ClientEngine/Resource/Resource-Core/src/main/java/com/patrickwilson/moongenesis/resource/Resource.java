package com.patrickwilson.moongenesis.resource;

/**
 * This is a platform agnostic mechanism for dealing with resources.
 * Created by pwilson on 2/16/15.
 */
public class Resource {

    private String path;
    private Object resource;

    public Resource(String path) {
        this.path = path;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
