package com.mike;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.FileChannelImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;

public class MikeNioUtils {
    public static void unmap(MappedByteBuffer bb) {
        Cleaner cl = ((DirectBuffer)bb).cleaner();
        if (cl != null)
            cl.clean();
    }

    public static void unmap2(MappedByteBuffer bb) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = FileChannelImpl.class.getDeclaredMethod("unmap",MappedByteBuffer.class);
        m.setAccessible(true);
        m.invoke(FileChannelImpl.class, bb);
    }


}
