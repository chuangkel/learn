package com.github.chuangkel.java_learn.base.reflect.javacompiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.Map;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-23 20:07
 **/
public class JavaCompilerTest {
    /**使用java编译器的api动态生成字节码*/
    public static void main(String[] args) {
        //Map<String, byte[]> results;
        //JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
        //try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
        //    JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
        //    JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
        //    if (task.call()) {
        //        results = manager.getClassBytes();
        //    }
        //}
    }
}
