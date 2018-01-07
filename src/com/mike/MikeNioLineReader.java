package com.mike;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MikeNioLineReader {
    private FileChannel fc = null;
    private MappedByteBuffer mbb = null;
    private long fileLength = 0;
    private long fileSliceCount = 0;
    private long currentSliceNumber = 0;
    private long lineCount = 0;
    private ArrayList<String> bufLines= new ArrayList<>();

    public MikeNioLineReader(String inputFileName) throws IOException {
        this.fc = new RandomAccessFile(inputFileName, "r").getChannel();
        this.fileLength = this.fc.size();
        this.fileSliceCount = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE) + 1;
        this.currentSliceNumber = 0;
    }

}
