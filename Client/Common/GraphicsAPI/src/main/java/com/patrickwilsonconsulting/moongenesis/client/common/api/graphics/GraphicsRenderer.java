package com.patrickwilsonconsulting.moongenesis.client.common.api.graphics;

import com.patrickwilsonconsulting.moongenesis.client.common.api.Component;

/**
 * Created by pwilson on 6/10/16.
 */
public interface GraphicsRenderer<GL2> extends Component {

    void setupViewport(GL2 gl, int width, int height);

    void renderScene(GL2 gl);
}
