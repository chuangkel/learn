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
 
 jps -l
 jps -v
 ps -ef | grep java //查看java进程所属的linux用户
 jstat -gcutil 6327 1000
 jmap -histo:live 6327 > jmap_histo_live.txt
 cat jmap_histo_live.txt
 head -n 100 jmap_histo_live.txt
 jmap -dump:live,format=b,file=heap.hprof 6327
 
 
 -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -server -Xms4g -Xmx4g 
 -Xmn680m -Xss256k  
 -XX:+UseConcMarkSweepGC  设置并发收集器
 -XX:+UseParNewGC  
 -XX:+CMSClassUnloadingEnabled 
 -XX:+DisableExplicitGC 
 -verbose:gc 
 -XX:+PrintGCDetails 
 -XX:+PrintGCTimeStamps 
 -XX:+PrintGCDateStamps 
 -Dcom.sun.management.jmxremote.port=9981 
 -Dcom.sun.management.jmxremote.ssl=false 
 -Dcom.sun.management.jmxremote.authenticate=false -Dfile.encoding=UTF-8 
 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs
 
 -Xss: // 设置栈容量的大小
 -XX:NewSize=n:设置年轻代大小
 -XX: -PermSize 设置方法区容量大小 -XX: -MaxPermSize
 -XX: -HeapDumpOnOutOfMemoryError //在发生内存溢出时转存(heapdump)内存快照
 -Xms最小堆内存大小
 -Xmx最大堆内存大小
 -Xms和-Xmx 设置成一样的即可避免堆自动扩展
 System.gc()
 
 jsp -v查看虚拟机进程启动参数
 jsp -m输出虚拟机进程启动时传递给主类的参数
 jstat -gc 22532 250 20 //22532是jsp查询的参数 间隔250毫秒查询一次，查询20次
 输出垃圾收集情况
 jstat -class 22532 250 20//查看类加载情况
 jinfo 22532 //查看虚拟机配置信息
 jstack -l 22352//输出虚拟机的堆栈信息 定位长时间停顿的原因 死锁 死循环 外部请求资源
 导致的长时间等待