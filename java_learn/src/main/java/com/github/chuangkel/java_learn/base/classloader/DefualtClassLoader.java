package com.github.chuangkel.java_learn.base.classloader;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-02-28 20:53
 **/
public class DefualtClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                try {
//                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                    InputStream is = getClass().getResourceAsStream(fileName);
//                    if (is == null) {
//                        return super.loadClass(name);
//                    }
//                    byte[] b = new byte[is.available()];
//                    is.read(b);
//                    return defineClass(name, b, 0, b.length);
//                } catch (IOException e) {
//                    throw new ClassNotFoundException(name);
//                }

                //使用默认类加载器
                return super.loadClass(name);
            }
        };
        Object obj = myLoader.loadClass("com.base.classloader.DefualtClassLoader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DefualtClassLoader);
    }

}
