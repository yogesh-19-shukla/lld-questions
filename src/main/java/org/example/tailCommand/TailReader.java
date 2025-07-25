package org.example.tailCommand;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TailReader {

    public void tail(String filePath, int numberOfLines) throws IOException {
        System.out.println("Tailing File");
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");

        long fileSize = raf.length();
        raf.seek(fileSize-1);
        long linesRead = 0;

        StringBuilder sb = new StringBuilder();
        for(long i=fileSize-1;i>=1;i--){
            raf.seek(i);
            int readByte = raf.readByte();
            char c = (char) readByte;
            if( c == '\n'){
                linesRead++;
                if(linesRead > numberOfLines){
                    break;
                }
            }
            sb.append(c);
        }
        sb.reverse();
        System.out.println(sb.toString());
        raf.close();
    }
}
