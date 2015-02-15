

import com.jogamp.opengl.util.FPSAnimator;
import com.patrickwilson.model.Face3D;
import com.patrickwilson.model.MeshModel;
import com.patrickwilson.model.Point3D;
import com.patrickwilson.model.util.ModelReader;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.nio.FloatBuffer;

/**
 * Created by pwilson on 2/14/15.
 */
public class OpenGlTest implements GLEventListener {

    private GLU glu = new GLU();
    public MeshModel model;
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
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glShadeModel(GL2.GL_SMOOTH);

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 50.0f);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{ 1.0f, 1.0f, 1.0f, 0.0f }, 0);
        //move the camera back 6 units
        gl.glTranslatef(0.0f, 0.0f, -12.0f);

        //now draw at origin
        gl.glBegin(GL2.GL_TRIANGLES);
        for (Face3D face: model.faces) {
            for (int i = 0; i < face.vertices.length; i++)  {
                //it seems to be important to specify the normal BEFORE the vertex.
                gl.glNormal3f(face.normals[i].vX, face.normals[i].vY, face.normals[i].vZ);
                gl.glVertex3f(face.vertices[i].x, face.vertices[i].y, face.vertices[i].z);

            }
        }
        gl.glEnd();


        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);




//        gl.glBegin(GL2.GL_TRIANGLES);
//        {
//            gl.glVertex3f(0.0f, 1.0f, 0.0f);
//            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
//            gl.glVertex3f(1.0f, -1.0f, 0.0f);
//        }
//        gl.glEnd();
//        gl.glTranslatef(3.0f, 0.0f, 0.0f);
//        gl.glBegin(GL2.GL_QUADS);
//        {
//            gl.glVertex3f(-1.0f, 1.0f, 0.0f);
//            gl.glVertex3f(1.0f, 1.0f, 0.0f);
//            gl.glVertex3f(1.0f, -1.0f, 0.0f);
//            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
//        }
//        gl.glEnd();
        gl.glFlush();
    }



    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();


        float aspect = width / height;

        glu.gluPerspective(45.0f, aspect, 1.0, 200.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);


    }

    public static void main(String... args) {

        //load the test model
        InputStream sampleModelStream = ModelReader.class.getResourceAsStream("/cube.obj");

        ModelReader reader = new ModelReader(sampleModelStream);



        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        OpenGlTest instance = new OpenGlTest();
        instance.model = reader.load();
        canvas.addGLEventListener(instance);
        canvas.setSize(400, 400);
        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);
//        JFrame frame = new JFrame("My Window");
//        frame.getContentPane().add(canvas);
//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                  if (animator.isAnimating()) {
//                      animator.stop();
//                  }
//            }
//        });
//
//        frame.setSize(frame.getContentPane().getPreferredSize());

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        JFrame mainWindow = new JFrame();
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainWindow.setLocation(0, 0);
//        Window window = env.getDefaultScreenDevice().getFullScreenWindow();
//        //this can help with better support for multiple monitors.
//        GraphicsDevice mainScreen = env.getDefaultScreenDevice();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int winX = (int)Math.max(0, (screenSize.getWidth() - frame.getWidth())/2);
//        int winY = (int)Math.max(0, (screenSize.getHeight() - frame.getHeight())/2);
//
//        frame.setLocation(winX, winY);
//        frame.setVisible(true);

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
