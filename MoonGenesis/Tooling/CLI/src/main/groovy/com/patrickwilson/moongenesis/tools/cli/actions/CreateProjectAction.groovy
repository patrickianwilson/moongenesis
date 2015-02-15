package com.patrickwilson.moongenesis.tools.cli.actions

import com.google.common.base.Strings
import com.google.common.collect.ImmutableMap
import com.patrickwilson.cli.actions.ActionExecutionException
import com.patrickwilson.cli.actions.ApplicationAction
import com.patrickwilson.cli.actions.OutputWriter
import com.patrickwilson.cli.actions.OptionBuilder
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.apache.commons.io.IOUtils

/**
 * Created by pwilson on 2/14/15.
 */
class CreateProjectAction implements ApplicationAction {


    public static final String NAME_ARG = "n"
    public static final String GRAPHIC_MODEL_ASSETS_FOLDER_NAME = "assets" + File.separator + "graphics" + File.separator + "models";
    public static final String GRAPHIC_TEXTURES_ASSETS_FOLDER_NAME = "assets" + File.separator + "graphics" + File.separator + "textures";
    public static final String AUDIO_ASSETS_FOLDER_NAME = "assets" + File.separator + "audio";
    public static final String PROPERTIES_FOLDER_NAME = "properties"
    public static final String SCENES_FOLDER = "scenes"

    public static final String SCRIPTS_MAIN_FOLDER = "scripts" + File.separator + "main"
    public static final String SCRIPTS_TESTS_FOLDER = "scripts" + File.separator + "tests"

    public static final String[] FOLDERS_TO_CREATE =
            [
                GRAPHIC_MODEL_ASSETS_FOLDER_NAME,
                GRAPHIC_TEXTURES_ASSETS_FOLDER_NAME,
                AUDIO_ASSETS_FOLDER_NAME,
                PROPERTIES_FOLDER_NAME,
                SCRIPTS_MAIN_FOLDER,
                SCRIPTS_TESTS_FOLDER,
                SCENES_FOLDER
            ];

    public static final String MAIN_GRADLE_CLASSLOADER_LOCATION = "/gradle/build.gradle"
    public static final String MAIN_SCENE_CLASSLOADER_LOCATION = "/scenes/main-scene.json"
    public static final String MAIN_GAME_CONFIG = "/game/game.json"

    public static final Map<String, String> FILES_TO_COPY = ImmutableMap.<String, String>builder()
            .put(MAIN_GRADLE_CLASSLOADER_LOCATION, "build.gradle")
            .put(MAIN_GAME_CONFIG, "game.json")
            .put(MAIN_SCENE_CLASSLOADER_LOCATION, "scenes/main-scene.json")
            .build();

    @Override
    void execute(CommandLine cmd, OutputWriter view) {
        def projectName = cmd.getOptionValue(NAME_ARG)
        File cwd = new File(".");
        File rootProjectDir = new File(cwd, projectName)

        if (rootProjectDir.exists()) {
            throw new ActionExecutionException("Project directory $projectName already exists!  Please choose another name")
        }

        if (!rootProjectDir.mkdirs()){
            //unable to create the file.  check for errors.
            throw new ActionExecutionException("Unable to create directory: $projectName in directory $cwd.path.  Check that the engine util script has write access!");
        }

        FOLDERS_TO_CREATE.each {
            it -> if (!new File(rootProjectDir, it).mkdirs()) {
                throw new ActionExecutionException("Unable to create directory: $projectName in directory $cwd.path.  Check that the engine util script has write access!");
            }
        }

        FILES_TO_COPY.forEach({key, value ->
            InputStream contents = CreateProjectAction.class.getResourceAsStream(key);
            File output = new File(rootProjectDir, value)
            if (!output.createNewFile()) {
                throw new ActionExecutionException("Unable to create file: $value in directory $rootProjectDir.path.  Check that the engine util script has write access!");
            }
            IOUtils.copy(contents, new FileOutputStream(output))

        })
    }

    @Override
    String getDesription() {
        return "Create a New Game Project"
    }

    @Override
    void addCommandlineOption(Options options) {
        options.addOption(new OptionBuilder()
                .withLongArg("name")
                .withShortArg(NAME_ARG)
                .setDescription("The name of the project")
                .setRequired(true)
                .setHasArg(true)
                .build())




    }
}
