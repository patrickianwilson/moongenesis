package com.patrickwilsonconsulting.moongenesis.client.desktop.graphics;


import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.patrickwilsonconsulting.moongenesis.client.DesktopComponentFactory;
import com.patrickwilsonconsulting.moongenesis.client.common.api.graphics.GraphicsRenderer;

/**
 * Created by pwilson on 6/8/16.
 */
public class Engine3DGraphicsWidget extends JPanel {

    private final GLCanvas canvas;
    private final GLProfile glProfile;
    private final DesktopGraphicsRenderer renderer;

    public Engine3DGraphicsWidget(DesktopGraphicsRenderer r) {
        this.renderer = r;
        glProfile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(glProfile);
        this.setLayout(new BorderLayout());
        canvas = new GLCanvas(capabilities);

        canvas.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable drawable) {

            }

            @Override
            public void dispose(GLAutoDrawable drawable) {

            }

            @Override
            public void display(GLAutoDrawable drawable) {
                renderer.renderScene(drawable.getGL().getGL2());
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                renderer.setupViewport(drawable.getGL().getGL2(), width, height);
            }
        });

        super.add(canvas, BorderLayout.CENTER);
    }

}
