package org.example.tailCommand;

import java.io.IOException;

public class CommandExecutor {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() throws IOException, InterruptedException {
        if (command != null) {
            command.execute();
        }
    }
}
