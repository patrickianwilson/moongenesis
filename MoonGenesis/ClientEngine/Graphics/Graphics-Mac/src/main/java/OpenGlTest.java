

import com.jogamp.opengl.util.FPSAnimator;
import com.patrickwilson.model.*;
import com.patrickwilson.model.util.ObjModelReader;
import com.patrickwilson.moongenesis.resource.types.Face3D;
import com.patrickwilson.moongenesis.resource.types.Scene;
import com.patrickwilson.moongenesis.resource.types.SceneNode;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Created by pwilson on 2/14/15.
 */
public class OpenGlTest implements GLEventListener {

    private GLU glu = new GLU();
    public SceneNode model;

    /* maintain reference for easier rotation*/
    public SceneNode roofPointer;

    public static NativeTransformationAdaptor adaptor = new NativeTransformationAdaptor();
    static {
        GLProfile.initSingleton();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        float[] rotation = this.roofPointer.getRotationTransformation();
        //make the roof spin.
        rotation[0] = (rotation[0] + 1) % 360;
        this.roofPointer.setRotationTransformation(rotation);

        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glShadeModel(GL2.GL_SMOOTH);

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 50.0f);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{ 1.0f, 1.0f, 1.0f, 0.0f }, 0);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        //position the camera off to the side and up.  Looking at origin.
       glu.gluLookAt(10.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, -12.0f);

        //now draw at origin
        drawModelNode(gl, model);





        gl.glFlush();
    }

    public void drawModelNode(GL2 gl, SceneNode node) {
        if (node.getChildren() != null && node.getChildren().length > 0) {
            gl.glPushMatrix();
            for (SceneNode child: node.getChildren()) {
                //recursively draw children
                drawModelNode(gl, child);
            }
            gl.glPopMatrix();
        } else if (node.getFaces() != null && node.getFaces().length > 0){
            drawMeshModel(gl, node);
        }
    }

    private void drawMeshModel(GL2 gl, SceneNode model) {
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
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();


        float aspect = width / height;

        glu.gluPerspective(30.0f, aspect, 3.0, 20);



    }

    public static void main(String... args) {

        //load the test model
        InputStream buildingModelReaderInput = ObjModelReader.class.getResourceAsStream("/cubbuilding.obj");
        InputStream rootModelReaderInput = ObjModelReader.class.getResourceAsStream("/sphere-roof.obj");

        ObjModelReader buildingModelReader = new ObjModelReader(buildingModelReaderInput);
        ObjModelReader roofModelReader = new ObjModelReader(rootModelReaderInput);


        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        OpenGlTest instance = new OpenGlTest();
        SceneNode building = buildingModelReader.load();
//        building.setPositionTransformation(new float[]{0.0f, -3.0f, 0.0f});
        instance.roofPointer = roofModelReader.load();
//        instance.roofPointer.setPositionTransformation(new float[]{ 2.36f, -0.420f, 1.412f});
        instance.roofPointer.setPositionTransformation(new float[]{ -0.420f, 1.412f, 2.36f});
        instance.roofPointer.setRotationTransformation(adaptor.buildPrimitiveRotationTransformation(0.0f, 0.0f, 1.0f, 0.0f));


//        instance.model = building;

        instance.model = Scene.builder().addNode(building).addNode(instance.roofPointer).build();

        canvas.addGLEventListener(instance);
        canvas.setSize(400, 400);
        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);

        animator.start();

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        JFrame mainWindow = new JFrame();
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainWindow.setLocation(0, 0);


        mainWindow.setVisible(true);
        mainWindow.add(canvas);

    }
//
//    private static final void initKeyBindings(JPanel p, final JFrame frame, final OpenGlTest instance) {
//
//        ActionMap actionMap = p.getActionMap();
//        InputMap inputMap = p.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0), "HOME");
//        actionMap.put("HOME", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                fullScreen(frame);
//            }
//        });
//
//    }
//
//    private static boolean inFullScreen = false;
//    private static Dimension windowedSize;
//    private static Point windowedLocation;
//    private static void fullScreen(JFrame frame) {
//        if (!inFullScreen) {
//            //make it full screen
//            frame.dispose();
//            frame.setUndecorated(true);
//            frame.setVisible(true);
//            frame.setResizable(false);
//            windowedSize = frame.getSize();
//            windowedLocation = frame.getLocation();
//            Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            frame.setSize(fullScreenSize);
//            inFullScreen = true;
//        } else {
//            frame.dispose();
//            frame.setUndecorated(false);
//            frame.setVisible(true);
//            frame.setResizable(true);
//
//            frame.setSize(windowedSize);
//            frame.setLocation(windowedLocation);
//            inFullScreen = false;
//        }
//    }

}
