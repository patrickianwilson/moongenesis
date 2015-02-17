package com.patrickwilson.model.render;

import com.patrickwilson.moongenesis.resource.types.Face3D;
import com.patrickwilson.moongenesis.resource.types.Scene;
import com.patrickwilson.moongenesis.resource.types.SceneNode;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Created by pwilson on 2/16/15.
 */
public class MacGraphics3DService implements Graphics3DService {
    private GL2 gl;
    private GLU glu = new GLU();

    @Override
    public void setTarget(Object o) {
        if (!(o instanceof GL2)) {
            throw new RenderException("Invalid GL Render Target: " + o.getClass());
        }

        gl = (GL2) o;
    }


    public void renderScene(Scene scene) throws RenderException {

        for (SceneNode node: scene.getNodes()) {
            renderSceneNode(node);
        }
    }

    public void renderSceneNode(SceneNode node) {
        gl.glScalef(node.getScaleTransformation()[0],node.getScaleTransformation()[1],node.getScaleTransformation()[2]);
        gl.glTranslatef(node.getPositionTransformation()[0], node.getPositionTransformation()[1], node.getPositionTransformation()[2]);
        gl.glRotatef(node.getRotationTransformation()[0],node.getRotationTransformation()[1],node.getRotationTransformation()[2], node.getRotationTransformation()[3]);
        gl.glPushMatrix();
        if (node.getChildren() != null && node.getChildren().length > 0) {
            for (SceneNode child: node.getChildren()) {
                renderSceneNode(child);
            }
        }

        if (node.getFaces() != null && node.getFaces().length > 0) {
            //this is a mesh.  Initiate render.
         drawMeshModel(node);
        }

        gl.glPopMatrix();
    }

    private void drawMeshModel(SceneNode model) {
        //setup transformations

        gl.glScalef(model.getScaleTransformation()[0],model.getScaleTransformation()[1],model.getScaleTransformation()[2]);
        gl.glTranslatef(model.getPositionTransformation()[0], model.getPositionTransformation()[1], model.getPositionTransformation()[2]);
        gl.glRotatef(model.getRotationTransformation()[0],model.getRotationTransformation()[1],model.getRotationTransformation()[2], model.getRotationTransformation()[3]);
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_TRIANGLES);
        for (Face3D face: model.faces) {
            for (int i = 0; i < face.vertices.length; i++)  {
                //it seems to be important to specify the normal BEFORE the vertex.
                gl.glNormal3f(face.normals[i].vX, face.normals[i].vY, face.normals[i].vZ);
                gl.glVertex3f(face.vertices[i].x, face.vertices[i].y, face.vertices[i].z);

            }
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    @Override
    public void resizeView(int x, int y, int width, int height) {

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = width / height;

        glu.gluPerspective(30.0f, aspect, 3.0, 20);

    }
}
