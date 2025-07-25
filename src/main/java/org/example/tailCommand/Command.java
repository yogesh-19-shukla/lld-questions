package org.example.tailCommand;

import java.io.IOException;

public interface Command {

    void execute() throws IOException, InterruptedException;
}
