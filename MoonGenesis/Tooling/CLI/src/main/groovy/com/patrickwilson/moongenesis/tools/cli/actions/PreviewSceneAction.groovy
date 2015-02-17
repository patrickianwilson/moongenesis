package com.patrickwilson.moongenesis.tools.cli.actions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patrickwilson.cli.actions.ApplicationAction
import com.patrickwilson.cli.actions.OptionBuilder
import com.patrickwilson.cli.actions.OutputWriter
import com.patrickwilson.moongenesis.context.ComponentRegistry
import com.patrickwilson.moongenesis.context.ContextListener
import com.patrickwilson.moongenesis.launcher.preview.MainWindow
import com.patrickwilson.moongenesis.launcher.preview.PreviewBootstrapper
import com.patrickwilson.moongenesis.resource.Resource
import com.patrickwilson.moongenesis.resource.ResourceService
import com.patrickwilson.moongenesis.resource.types.Scene
import com.patrickwilson.moongensis.resource.desktop.DesktopResourceService
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Options

/**
 * Created by pwilson on 2/16/15.
 */
class PreviewSceneAction  extends ProjectAwareAction implements ApplicationAction {


    public static final String SCENE_LONG_ARG = "scene";
    public static final String SCENE_SHORT_ARG = "s";


    public Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void execute(CommandLine cmd, OutputWriter view) {
        def sourceSceneId = cmd.getOptionValue(SCENE_SHORT_ARG);

        final String scenePath = "scenes" + File.separator + sourceSceneId + ".json"

        PreviewBootstrapper bootstrapper = new PreviewBootstrapper(this.projectDir);

        bootstrapper.addContextListener(new ContextListener() {
            @Override
            void onContextInitialized(ComponentRegistry context) {
                ResourceService resourceService = context.getObject(ResourceService.class);
                Resource sceneResource = resourceService.getJsonResource(scenePath, Scene.class);
                MainWindow window = context.getObject(MainWindow.class);
                window.setCurrentScene(sceneResource.getResource());
                window.loadJFrame();
            }
        })
        bootstrapper.startContext();


    }

    @Override
    public String getDesription() {
        return "Preview a given scene id.  The scene id must match the scene file name";
    }

    @Override
    public void addCommandlineOption(Options options) {
        options.addOption(new OptionBuilder()
                .withLongArg(SCENE_LONG_ARG)
                .withShortArg(SCENE_SHORT_ARG)
                .setDescription("The location of the scene file")
                .setRequired(true)
                .setHasArg(true)
                .build());

    }
}