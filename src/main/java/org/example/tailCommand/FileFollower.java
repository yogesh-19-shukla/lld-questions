package org.example.tailCommand;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileFollower {

    public void follow(String filePath) throws IOException, InterruptedException {
        System.out.println("Following File");
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");

        while (true) {
            String line = raf.readLine();
            if(line != null){
                System.out.println(line);
            }
            else{
                Thread.sleep(2000);
            }
        }
    }

}
