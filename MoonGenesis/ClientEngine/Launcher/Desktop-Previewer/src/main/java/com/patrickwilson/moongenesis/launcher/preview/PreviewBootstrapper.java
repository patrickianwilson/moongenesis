package com.patrickwilson.moongenesis.launcher.preview;

import com.patrickwilson.model.render.Graphics3DService;
import com.patrickwilson.model.render.MacGraphics3DService;
import com.patrickwilson.moongenesis.context.ComponentFactory;
import com.patrickwilson.moongenesis.context.ComponentRegistry;
import com.patrickwilson.moongenesis.context.ContextListener;
import com.patrickwilson.moongenesis.context.EngineContextLauncher;
import com.patrickwilson.moongenesis.resource.ResourceService;
import com.patrickwilson.moongensis.resource.desktop.DesktopResourceService;

import javax.naming.Context;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pwilson on 2/16/15.
 */
public class PreviewBootstrapper extends EngineContextLauncher implements ContextListener, ComponentFactory {

    private File projectDir;
    private List<ContextListener> contextListeners = new LinkedList<>();

    public PreviewBootstrapper(File projectDir) {
        this.projectDir = projectDir;
        contextListeners.add(this);
    }


    @Override
    public void configure(ComponentRegistry registry) {

        registry.registerObject(ResourceService.class, new DesktopResourceService(projectDir));
        registry.registerObject(Graphics3DService.class, new MacGraphics3DService());
        registry.registerObject(MainWindow.class, new MainWindow());

    }

    @Override
    public void onContextInitialized(ComponentRegistry context) {
        System.out.println("Initialized!");
    }

    @Override
    protected ComponentFactory[] getComponentFactories() {
        return new ComponentFactory[] {this};
    }

    @Override
    protected ContextListener[] getContextListeners() {
        return contextListeners.toArray(new ContextListener[]{});
    }

    public void addContextListener(ContextListener listener) {
        this.contextListeners.add(listener);
    }
}
