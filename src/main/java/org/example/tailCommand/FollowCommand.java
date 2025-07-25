package org.example.tailCommand;

import java.io.IOException;

public class FollowCommand implements Command {

    private final FileFollower follower;
    private final String filePath;

    public FollowCommand(FileFollower follower, String filePath) {
        this.follower = follower;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        follower.follow(filePath);
    }
}
