package com.patrickwilson.moongenesis.tools.cli.actions;

import java.io.File;

/**
 * Created by pwilson on 2/16/15.
 */
public abstract class ProjectAwareAction {

    private File projectDir;

    protected File getProjectDir() {
        if (projectDir != null) {
            return projectDir;
        }

        File temp = new File("game.json").getAbsoluteFile();
        while (!temp.exists() && temp.getParentFile() != null) {
            //this is not the directory, try the parent.
            temp = new File(temp.getParentFile(), "game.json");
        }
        if (temp == null)
            return null;

        this.projectDir = temp.getParentFile();
        return this.projectDir;
    }
}
