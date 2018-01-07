package com.mike;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.FileChannelImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class mikeNioTest {
    public static void main(String[] args) {
        File file = new File(".\\aaa.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];
        MappedByteBuffer mappedByteBuffer = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(file,"r");
            FileChannel fc = raf.getChannel();
            mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, len);
            fc.force(true);
            fc.close();
            raf.close();
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }

        } catch (IOException e) {}
        try {
            boolean ret = file.delete();
//            MikeNioUtils.unmap(mappedByteBuffer);
            MikeNioUtils.unmap2(mappedByteBuffer);
            ret = file.delete();
            System.out.print(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
