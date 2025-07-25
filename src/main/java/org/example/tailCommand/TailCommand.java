package org.example.tailCommand;

import java.io.IOException;

public class TailCommand implements Command {

    private final TailReader reader;
    private final String filePath;
    private final int numberOfLines;

    public TailCommand(TailReader reader, String filePath, int numberOfLines) {
        this.reader = reader;
        this.filePath = filePath;
        this.numberOfLines = numberOfLines;
    }

    @Override
    public void execute() throws IOException {
        reader.tail(filePath, numberOfLines);
    }
}
