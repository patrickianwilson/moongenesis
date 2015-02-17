package com.patrickwilson.moongenesis.launcher.preview;

import com.jogamp.opengl.util.FPSAnimator;
import com.patrickwilson.model.render.Graphics3DService;
import com.patrickwilson.moongenesis.context.ComponentRegistry;
import com.patrickwilson.moongenesis.context.RegistryAware;
import com.patrickwilson.moongenesis.resource.types.Scene;
import com.patrickwilson.moongenesis.resource.types.SceneNode;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;

/**
 * Created by pwilson on 2/16/15.
 */
public class MainWindow implements GLEventListener, RegistryAware {

    private Graphics3DService graphicsService;
    private Scene currentScene;
    private ComponentRegistry registry;

    public MainWindow() {

    }

    @Override
    public void setComponentRegistry(ComponentRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void onRegistryLoaded() {
        this.graphicsService = registry.getObject(Graphics3DService.class);
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 target = glAutoDrawable.getGL().getGL2();
        graphicsService.setTarget(target);
        graphicsService.renderScene(currentScene);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {

        GL2 target = glAutoDrawable.getGL().getGL2();
        graphicsService.setTarget(target);
        graphicsService.resizeView(x, y, w, h);
    }

    public void loadJFrame() {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        canvas.addGLEventListener(this);
        canvas.setSize(400, 400);
        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);

        animator.start();
        JFrame mainWindow = new JFrame();
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainWindow.setLocation(0, 0);

        mainWindow.setVisible(true);
        mainWindow.add(canvas);
    }


}
