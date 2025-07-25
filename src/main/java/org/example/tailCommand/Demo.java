package org.example.tailCommand;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "src/main/java/org/example/tailCommand/logs.txt";

        TailReader reader = new TailReader();
        FileFollower follower = new FileFollower();

        CommandExecutor commandExecutor = new CommandExecutor();

        TailCommand tailCommand = new TailCommand(reader, filePath, 4);
        FollowCommand followCommand = new FollowCommand(follower, filePath);

        commandExecutor.setCommand(tailCommand);
        commandExecutor.run();

        commandExecutor.setCommand(followCommand);
        commandExecutor.run();


    }
}
