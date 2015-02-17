package com.patrickwilson.model.render;

import com.patrickwilson.moongenesis.resource.types.Scene;

/**
 * Created by pwilson on 2/16/15.
 */
public interface Graphics3DService {

    /*
    set the platform appropriate rendering target.  In Desktop this is a GL2 object.
     */
    void setTarget(Object o);

    void renderScene(Scene scene) throws RenderException;

    void resizeView(int x, int y, int width, int height);
}
