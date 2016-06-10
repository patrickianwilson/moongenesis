package com.patrickwilsonconsulting.moongenesis.client.desktop.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.patrickwilsonconsulting.moongenesis.client.common.api.graphics.GraphicsRenderer;

/**
 * Created by pwilson on 6/10/16.
 */
public class DesktopGraphicsRenderer implements GraphicsRenderer<GL2> {


    float[] qaAmbientLight    = { 0.1f, 0.1f, 0.1f, 1.0f };
    float[] qaDiffuseLight    = {1f, 1f, 1f, 1.0f };
    float[] qaSpecularLight    = {1.0f, 1.0f, 1.0f, 1.0f };

    float[] qaLightPosition    = {0, 0, 0, 1 };
    GLU glu = new GLU();
    GLUT glut = new GLUT();

    @Override
    public void setupViewport(GL2 gl2, int width, int height) {
        gl2.glViewport( 0, 0, width, height );

        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        float aspect = (float)width/(float)height;


        glu.gluPerspective(45, aspect, 1, 10);




        //ensure stuff is rendered with a depth test
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glDepthFunc(GL2.GL_LESS);

    }

    @Override
    public void renderScene(GL2 gl2) {
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl2.glEnable(GL2.GL_LIGHTING);

        gl2.glEnable(GL2.GL_LIGHT0);


        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        gl2.glPushMatrix();
        // draw a triangle filling the window
        //setup camera
        glu.gluLookAt(0, 0, 6, 0, 0, 0, 0, 1, 0);
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
