package com.github.chuangkel.java_learn.base.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: learn
 * @description: 自定义IO装饰器
 * @author: chuangkel
 * @create: 2019-10-21 19:42
 **/
public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase((char) c);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int result = super.read(b,off,len);
        for(int i = off ;i < result + off;i++){
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return result;
    }
}
