package com.github.chuangkel.java_learn.base.collection.clone;

import java.io.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-08-11 00:15
 **/
public class SerializableCloneUtil {
    public static <T extends Serializable> T clone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(object);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream is = new ObjectInputStream(bis);
        T t = (T)is.readObject();

        os.close();
        is.close();
        bis.close();
        bos.close();
        return t;
    }
}
