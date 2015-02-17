package com.patrickwilson.moongensis.resource.desktop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.patrickwilson.moongenesis.resource.Resource;
import com.patrickwilson.moongenesis.resource.ResourceNotFoundException;
import com.patrickwilson.moongenesis.resource.ResourceService;

import java.io.*;

/**
 * Created by pwilson on 2/16/15.
 */
public class DesktopResourceService implements ResourceService {

    private Gson gson = new GsonBuilder().create();
    private File resourceDir;

    public DesktopResourceService(File resourceDir) {
        this.resourceDir = resourceDir;
    }

    @Override
    public Resource getJsonResource(String path, Class<?> type) {

        File resourceFile = new File(this.resourceDir, path);
        if (!resourceFile.exists()) {
            throw new ResourceNotFoundException("Unable to locate resource: " + resourceFile.getAbsolutePath());
        }

        try {
            Object obj =  gson.fromJson(new JsonReader(new FileReader(resourceFile)), type);

            Resource result = new Resource(path);
            result.setResource(obj);
            return result;
        } catch (FileNotFoundException e) {
            //not possible - already check it.
        }
        //should be possible to reach this.
        return null;
    }
}
