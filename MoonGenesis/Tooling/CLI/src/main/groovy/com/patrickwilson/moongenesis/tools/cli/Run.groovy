package com.patrickwilson.moongenesis.tools.cli

import com.google.common.collect.ImmutableMap
import com.patrickwilson.cli.actions.ApplicationAction
import com.patrickwilson.cli.actions.Main
import com.patrickwilson.moongenesis.tools.cli.actions.CreateProjectAction

/**
 * Created by pwilson on 2/14/15.
 */
class Run {

    public static void main(String... args) {

        com.patrickwilson.cli.actions.Main runner = new Main("engine", new ImmutableMap.Builder<String, ApplicationAction>()

                .put("new-project", new CreateProjectAction())

                .build(),
                ImmutableMap.builder()
                    .put("interactive", "run this utility in interactive mode")
                    .build());


        //we have been instructed to loop the process and continue to read input from the std in.
        if (args.length == 1 && "--interactive".equals(args[0])) {
            Scanner scanner = new Scanner(System.in)



            while (true) {
                println("Enter Your Command: ")
                String[] lineArgs = scanner.nextLine().split(" ");
                if (lineArgs != null && lineArgs.length >=1 && "quit".equals(lineArgs[0])) {
                    System.exit(0);
                } else if (lineArgs == null || lineArgs.length < 1) {
                    runner.executeApplicationNoStop();
                } else {
                    runner.executeApplicationNoStop(lineArgs);
                }

            }


        } else {
            //execute a single command from the arguments
            runner.executeApplication(args);
        }


    }
}
