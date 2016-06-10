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

/**
 * Created by pwilson on 6/8/16.
 */
public class Engine3DGraphicsWidget extends JPanel {

    private final GLCanvas canvas;
    private final GLProfile glProfile;
    GLU glu = new GLU();

    float[] qaAmbientLight    = { 0.1f, 0.1f, 0.1f, 1.0f };
    float[] qaDiffuseLight    = {1f, 1f, 1f, 1.0f };
    float[] qaSpecularLight    = {1.0f, 1.0f, 1.0f, 1.0f };
    float[] emitLight = {0.9f, 0.9f, 0.9f, 0.01f };
    float[] Noemit = {0.0f, 0.0f, 0.0f, 1.0f };


    float[] qaLightPosition    = {0, 0, 0, 1 };
    float[] qaLightDirection    = {1, 1, 1, 0} ;
    public Engine3DGraphicsWidget() {
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
                render(drawable.getGL().getGL2(), drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                setup(drawable.getGL().getGL2(), width, height);
            }
        });

        super.add(canvas, BorderLayout.CENTER);
    }


    protected void setup( GL2 gl2, int width, int height ) {

        gl2.glViewport( 0, 0, width, height );

        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        float aspect = (float)width/(float)height;


        glu.gluPerspective(45, aspect, 1, 10);




        //ensure stuff is rendered with a depth test
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glDepthFunc(GL2.GL_LESS);


    }

    protected void render( GL2 gl2, int width, int height ) {
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl2.glEnable(GL2.GL_LIGHTING);

        gl2.glEnable(GL2.GL_LIGHT0);


        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        gl2.glPushMatrix();
        // draw a triangle filling the window
        //setup camera
        glu.gluLookAt(0, 0, 6, 0, 0, 0, 0, 1, 0);

        GLUT glut = new GLUT();

        gl2.glRotated(-45, 1, 0, 0);

        glut.glutSolidTeapot(1, false);
        gl2.glPopMatrix();

        //now do lighting
        gl2.glPushMatrix();
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, qaAmbientLight, 0);
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, qaDiffuseLight, 0);
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, qaLightPosition, 0);
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, qaSpecularLight, 0);
        gl2.glPopMatrix();
    }
}
