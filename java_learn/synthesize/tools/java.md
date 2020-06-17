

```sbtshell
现象：
[root@logcenter ~]# java ./JstackLockAndWhileTrueDemo
错误: 找不到或无法加载主类 ..JstackLockAndWhileTrueDemo
在环境配置文件中加入：
export CLASSPATH=.:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib
[root@logcenter ~]# vim /etc/profile
[root@logcenter ~]# source /etc/profile
```