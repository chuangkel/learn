package com.base.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: javaNewCharacteristic
 * @description: 可重入读写锁
 * @author: yaoqb23911
 * @create: 2018-12-23
 **/
public class ReetrantReadWriteLockTest {

    ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    @Test
    public  void main1() throws InterruptedException {
        for (int i = 0 ;i<10;i++){
            User user = new User(reentrantLock);
            Thread thread = new Thread(user);
            thread.start();
        }
        ReentrantReadWriteLock a;
        ReentrantLock reentrantLock = new ReentrantLock();
        //.wait() notify()方法需要先获取锁，否则会报错
        reentrantLock.wait();
        AtomicReference atomicReference ;
    }


    private class User implements Runnable{
        ReentrantReadWriteLock reentrantLock;
        User(ReentrantReadWriteLock reentrantLock){
            this.reentrantLock = reentrantLock;
        }
        @Override
        public void run() {

            ReentrantReadWriteLock.ReadLock readLock = reentrantLock.readLock();
            readLock.lock();
            System.out.println("User is reading");
            readLock.unlock();

            ReentrantReadWriteLock.WriteLock writeLock = reentrantLock.writeLock();
            writeLock.lock();
            System.out.println("User is Writing");
            writeLock.unlock();
        }
    }

}
//执行结果
//"C:\Program Files\Java\jdk1.8.0_172\bin\java.exe" -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\tools\idea\IntelliJ IDEA 2018.1.5\lib\idea_rt.jar=51795:D:\tools\idea\IntelliJ IDEA 2018.1.5\bin" -Dfile.encoding=UTF-8 -classpath "D:\tools\idea\IntelliJ IDEA 2018.1.5\lib\idea_rt.jar;D:\tools\idea\IntelliJ IDEA 2018.1.5\plugins\junit\lib\junit-rt.jar;D:\tools\idea\IntelliJ IDEA 2018.1.5\plugins\junit\lib\junit5-rt.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_172\jre\lib\rt.jar;D:\fileSystem\persons\Github\javaNewCharacteristic\target\classes;D:\fileSystem\File\mavenRepo\junit\junit\4.12\junit-4.12.jar;D:\fileSystem\File\mavenRepo\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit4 com.chuangkel.baseknowladge.ReetrantReadWriteLockTest,main1
//        User is reading
//        User is reading
//        User is Writing
//        User is Writing
//        User is reading
//        User is Writing
//        User is reading
//        User is Writing
//        User is reading
//        User is Writing
//        User is reading
//        User is Writing
//        Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF8