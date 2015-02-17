package com.patrickwilson.moongenesis.tools.cli.actions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patrickwilson.cli.actions.ActionExecutionException;
import com.patrickwilson.cli.actions.ApplicationAction;
import com.patrickwilson.cli.actions.OptionBuilder;
import com.patrickwilson.cli.actions.OutputWriter
import com.patrickwilson.moongenesis.resource.types.SceneNode
import com.patrickwilson.model.util.ObjModelReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Created by pwilson on 2/16/15.
 */
public class ImportObjModelAction extends ProjectAwareAction implements ApplicationAction {


    public static final String FILE_LONG_ARG = "file";
    public static final String FILE_SHORT_ARG = "f";
    public static final String NAME_LONG_ARG = "name";
    public static final String NAME_SHORT_ARG = "n";

    public Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void execute(CommandLine cmd, OutputWriter view) {
        def sourceFilePath = cmd.getOptionValue(FILE_SHORT_ARG);
        def targetModelName = cmd.getOptionValue(NAME_SHORT_ARG);

        FileInputStream objModelSource = new FileInputStream(sourceFilePath);

        ObjModelReader modelReader = new ObjModelReader(objModelSource);

        SceneNode model = modelReader.load()

        SceneNode serializable = new SceneNode();
        serializable.setName(targetModelName);
        serializable.faces = model.faces
        serializable.pointIndex = model.pointIndex;
        serializable.positionTransformation = model.positionTransformation;
        serializable.rotationTransformation = model.rotationTransformation;
        serializable.scaleTransformation = model.scaleTransformation;

        File targetAsset = new File(getProjectDir(), "assets"
                + File.separator
                + "graphics"
                + File.separator
                + "models"
                + File.separator
                + targetModelName
                + ".json");

        if (targetAsset.exists() || !targetAsset.createNewFile()) {
            throw new ActionExecutionException("Unable to write model asset: " + targetAsset.getName());
        }

        String json = gson.toJson(serializable);
        PrintWriter output = new PrintWriter(new FileOutputStream(targetAsset));
        output.println(json);
        output.close();

    }

    @Override
    public String getDesription() {
        return "Import a standard OBJ mesh model into the project.";
    }

    @Override
    public void addCommandlineOption(Options options) {
        options.addOption(new OptionBuilder()
                .withLongArg(FILE_LONG_ARG)
                .withShortArg(FILE_SHORT_ARG)
                .setDescription("The location of the obj file")
                .setRequired(true)
                .setHasArg(true)
                .build());

        options.addOption(new OptionBuilder()
                .withLongArg(NAME_LONG_ARG)
                .withShortArg(NAME_SHORT_ARG)
                .setDescription("The name of the model (will setup metadata and target filename)")
                .setRequired(true)
                .setHasArg(true)
                .build());
    }
}
