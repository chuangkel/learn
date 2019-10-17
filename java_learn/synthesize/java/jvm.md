# jvm参数
-vmargs -Xms128M -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M 
  
-vmargs 说明后面是VM的参数，所以后面的其实都是JVM的参数了
-Xms128m JVM初始分配的堆内存
-Xmx512m JVM最大允许分配的堆内存，按需分配。
-Xms128m设置与-Xmx128m相同,以避免每次垃圾回收完成后JVM重新分配内存（避免堆的自动扩展）
-XX:PermSize=64M JVM初始分配的非堆内存
-XX:MaxPermSize=128M JVM最大允许分配的非堆内存，按需分配
-Xmn4g:设置年轻代大小为4G
-Xss128k:设置每个线程的堆栈大小

-XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=256m -XX:MaxTenuringThreshold=0
-XX:NewRatio=4:设置年轻代(包括Eden和两个Survivor区)与年老代的比值(除去持久代).设置为4,则年轻代与年老代所占比值为1:4,年轻代占整个堆栈的1/5
-XX:SurvivorRatio=4:设置年轻代中Eden区与Survivor区的大小比值.设置为4,则两个Survivor区与一个Eden区的比值为2:4,一个Survivor区占整个年轻代的1/6
-XX:MaxPermSize=256m:设置持久代大小为256m.
-XX:MaxTenuringThreshold=0:设置垃圾最大年龄.如果设置为0的话,则年轻代对象不经过Survivor区,直接进入年老代.
 对于年老代比较多的应用,可以提高效率.如果将此值设置为一个较大值,
 则年轻代对象会在Survivor区进行多次复制,这样可以增加对象再年轻代的存活时间,增加在年轻代即被回收的概率