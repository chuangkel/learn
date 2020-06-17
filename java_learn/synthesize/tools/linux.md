ss转储套接字信息, 显示TCP状态和信息
ss -tnlp | grep ":22"
在linux机器为ip上抓包
tcpdump tcp  port 12200 and host ip -w /tmp/20190120.pcap

查看本机ip
hostname -i

cd - 返回上一层目录

//查看网卡信息
ip address show

//查看路由信息 ip route show

ll -ctr

ll -lh

sed -e 's/替换字符串/目标字符串/g' 要替换的文件

sed -i 's/000710"/000424/g' * //单引号

find . | args grep -ri "2018" |

grep -c 20109 1.sql

grep -C 10 xxx.xx

rename a b a

which python

zip 包存放路径/xxx.zip aaa/* //aaa是文件 需在aaa所在目录下

ll -h|grep G

du -a|sort -rn|head -5

wc -l 文件名 //查看行数

head -c 20 file　　显示文件file的前20个字节

head -5 file　　显示文件file的前5行

grep -c 1D0618060587021D7 30_305_trade_apply_20181023_06.txt

tar -xvf file.tar //解压 tar包

find / -name java

find . -name .svn | xargs rm -rf

netstat -anp | grep 12200 查询监听端口12200
netstat -ntpl //查看
 ps -aux | grep nginx
command >out.file 2>&1 &
// command>out.file是将command的输出重定向到out.file文件，即输出内容不打印到屏幕上，而是输出到out.file文件中。
// 2>&1 是将标准出错重定向到标准输出，这里的标准输出已经重定向到了out.file文件，即将标准出错也输出到out.file文件中。最后一个&， 是让该命令在后台执行。
// 试想2>1代表什么，2与>结合代表错误重定向，而1则代表错误重定向到一个文件1，而不代表标准输出；换成2>&1，&与1结合就代表标准输出了，就变成错误重定向到标准输出

 安装和登录命令：login、 shutdown、 halt、 reboot 、mount、umount 、chsh
 文件处理命令：file、 mkdir、 grep、dd、 find、 mv 、ls 、diff、 cat、 ln
 系统管理相关命令： df、 top、 free、 quota 、at、 lp、 adduser、 groupadd kill、 crontab、 tar、 unzip、 gunzip 、last
网络操作命令：ifconfig、 ip 、ping 、 netstat 、telnet、 ftp、 route、 rlogin rcp 、finger 、mail 、nslookup
 系统安全相关命令： passwd 、su、 umask 、chgrp、 chmod、chown、chattr、sudo、 pswho

ps //查看进程
ps -ef //显示所有进程信息，连同命令行
ps -A //显示所有命令
ps -A|grep mysql //查看mysql进程
ps -ef | grep mysql //查看带有字符串mysql的进程

sudo kill -9 pid //强制kill进程pid

chown -R mysql:mysql /var/lib/mysql //chown授权

netstat -anp | grep 3306 //查看3306端口的占用程序
lsof -i:端口

grep -rn "DOWNTUXEDO" * //查找字符串

grep "grep_pattern" file_name;
//grep（global search regular expression(RE) and print out the //line，全面搜索正则表达式并把行打印出来）是一种强
//大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。

//在linux中，&和&&,|和||介绍如下：
& 表示任务在后台执行，如要在后台运行redis-server,则有 redis-server & ,例如：sh test.sh &
&& 表示前一条命令执行成功时，才执行后一条命令 ，如 echo '1' && echo '2'
| 表示管道，上一条命令的输出，作为下一条命令参数，如 echo 'yes' | wc -l
|| 表示上一条命令执行失败后，才执行下一条命令，如 cat nofile || echo "fail"

>nohup //命令可以在你退出帐户之后继续运行相应的进程。nohup就是不挂起的意思( no hang up)。
该命令的一般形式为：nohup command &

```bash
crontab -e //编辑定时任务 
crontab -l //查看是否有定时任务
crontab表达式
minute hour day month week shell-script
eg. * * * * * test.sh //表示每一分钟执行一次test.sh
1 * * * * test.sh //表示每小时的01分钟执行一次 即每小时执行一次
0 0 * * * test.sh //表示每天的 0 ：0 执行一次
```

vim模式下 命令模式dd删除一行

touch test.sh 创建脚本

rename test aaa *.sh //将当前目录的所有test.sh 命名为aaa.sh

ll -ctrh //按时间排序列表

grep -C 5 aaa 文件名

grep -r aa 文件名 //-r递归

tail -100f fintaskcenter-biztask.log 获取日志最后100行，持续刷新

su admin //切换到admin用户

top //Linux下常用的性能分析工具  能够实时显示系统中各个进程的资源占用状况。

date > date.log //将当前时间写入date.log 覆盖
date >> date.log //追加

stat filename //显示filename 当前的时间
```bash
[root@tactaskcenter-1-gray ~]# stat clean.sh 
  File: `clean.sh'
  Size: 1185      	Blocks: 8          IO Block: 4096   regular file
Device: fc01h/64513d	Inode: 270050      Links: 1
Access: (0644/-rw-r--r--)  Uid: (    0/    root)   Gid: (    0/    root)
Access: 2018-10-10 13:52:23.619000028 +0800
Modify: 2018-05-03 20:28:51.000000000 +0800
Change: 2018-10-10 13:52:04.010000012 +0800
```

find -atime  //访问时间 access time 
find -ctime  //状态改变时间 change time 
find -mtime  //修改时间 modify time
+n 大于n    s m h d w  单位
n 等于n
-n 小于n
eg. find -mtime +3d 查找修改时间大于三天的

-exec 命令2 {} \ ; 调用命令2 ,{} 是传入的参数，\ 为了转义

```bash
#删除4天前log文件
#find /home/admin/logs -mtime +4 -name "*.log*" -exec rm -rf {} \;

#删除3天前本地OSS缓存文件
find /home/admin/logs/ossLocal -mtime +3 -name "*" -exec rm -rf {} \;

#应用日志路径
find /home/admin/logs/tactaskcenter -mtime +4 -name "*.log*" -exec rm -rf {} \;
find /home/admin/logs/fincommontaskhub -mtime +4 -name "*.log*" -exec rm -rf {} \;

#清空大于2G的stderr.log,stdout.log
find /home/admin/logs -name 'std*.log' -size +2G -exec cp /dev/null  {} \;

#清空大于1G的压测log文件
find /home/admin/logs/fincommontaskhub/loadtest -size +1G -exec cp /dev/null  {} \;

#!/bin/sh

if [  -d "/home/admin/logs/ossLocal" ]; then
  find /home/admin/logs/ossLocal -mtime +3 -name "*" -exec rm -rf {} \;
fi

if [  -d "/home/admin/logs" ]; then //如果存在 logs文件 而且是文件目录则为真
  find /home/admin/logs -name 'std*.log' -size +2G -exec cp /dev/null  {} \;
fi

if [  -d "/home/admin/logs/fincommontaskhub/loadtest" ]; then
  find /home/admin/logs/fincommontaskhub/loadtest -mtime +2 -name "*" -exec rm -rf {} \;
  find /home/admin/logs/fincommontaskhub/loadtest -size +5G -exec cp /dev/null  {} \;
fi
```

ll -ctr
ll -lh
sed -e 's/替换字符串/目标字符串/g' 要替换的文件
sed -i 's/000710"/000424/g' *  //单引号
find . | args grep -ri "2018" | 
grep -c 20109  1.sql
grep -C 10 xxx.xx
rename a b a
which python
zip 包存放路径/xxx.zip  aaa/*  //aaa是文件  需在aaa所在目录下
> filename
ll -h|grep G
du -a|sort -rn|head -5
wc -l 文件名 //查看行数
head -c 20 file　　显示文件file的前20个字节
head -5 file　　显示文件file的前5行
grep -c 1D0618060587021D7 30_305_trade_apply_20181023_06.txt
tar -xvf file.tar //解压 tar包

```
command >out.file 2>&1 &
// command>out.file是将command的输出重定向到out.file文件，即输出内容不打印到屏幕上，而是输出到out.file文件中。
// 2>&1 是将标准出错重定向到标准输出，这里的标准输出已经重定向到了out.file文件，即将标准出错也输出到out.file文件中。最后一个&， 是让该命令在后台执行。
// 试想2>1代表什么，2与>结合代表错误重定向，而1则代表错误重定向到一个文件1，而不代表标准输出；换成2>&1，&与1结合就代表标准输出了，就变成错误重定向到标准输出
```

```
l 安装和登录命令：login、 shutdown、 halt、 reboot 、mount、umount 、chsh 
l 文件处理命令：file、 mkdir、 grep、dd、 find、 mv 、ls 、diff、 cat、 ln 
l 系统管理相关命令： df、 top、 free、 quota 、at、 lp、 adduser、 groupadd kill、 crontab、 tar、 unzip、 gunzip 、last 
l 网络操作命令：ifconfig、 ip 、ping 、 netstat 、telnet、 ftp、 route、 rlogin rcp 、finger 、mail 、nslookup 
l 系统安全相关命令： passwd 、su、 umask 、chgrp、 chmod、chown、chattr、sudo、 pswho
```

```
ps //查看进程
ps -ef //显示所有进程信息，连同命令行
ps -A //显示所有命令
ps -A|grep mysql //查看mysql进程
ps -ef | grep mysql //查看带有字符串mysql的进程
```

```
sudo kill -9 pid  //强制kill进程pid
```

```
chown -R mysql:mysql /var/lib/mysql   //chown授权
```

```
netstat -anp | grep 3306 //查看3306端口的占用程序
```

```
grep -rn "DOWNTUXEDO" *  //查找字符串
```

```
grep "grep_pattern" file_name; 
//grep（global search regular expression(RE) and print out the //line，全面搜索正则表达式并把行打印出来）是一种强
//大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。
```


```
//在linux中，&和&&,|和||介绍如下：
&  表示任务在后台执行，如要在后台运行redis-server,则有  redis-server & ,例如：sh test.sh &
&& 表示前一条命令执行成功时，才执行后一条命令 ，如 echo '1' && echo '2'    
| 表示管道，上一条命令的输出，作为下一条命令参数，如 echo 'yes' | wc -l
|| 表示上一条命令执行失败后，才执行下一条命令，如 cat nofile || echo "fail"
```

```
nohup //命令可以在你退出帐户之后继续运行相应的进程。nohup就是不挂起的意思( no hang up)。
该命令的一般形式为：
nohup command &
```
import os
import Logging

编辑定时任务
crontab -e 

vim模式下
命令模式dd删除一行

创建脚本touch test.sh


```
[root@host nwdir]# rename test aaa *.sh
```

```
ll -ctr //按时间排序列表

grep -C 5 aaa 文件名
grep -r aa 文件名  //-r递归
```
获取日志最后100行，持续刷新
tail -100f fintaskcenter-biztask.log
附： 
① 更改文件拥有者 (chown ) 
[root@linux ~]# chown 账号名称 文件或目录 
② 改变文件的用户组用命令 chgrp 
[root@linux ~]# chgrp 组名 文件或目录 
③ 对于目录权限修改之后，默认只是修改当前级别的权限。如果子目录也要递归需要加R参数 
Chown -R : 进行递归,连同子目录下的所有文件、目录

查询mysql 是否启动

* linux解压覆盖命令   
unzip -o  app.zip   
-o参数不提示覆盖同名文件

host文件
hosts文件是linux系统中负责ip地址与域名快速解析的文件，以ASCII格式保存在/etc目录下，文件名为hosts，不同的linux版本，文件也可能不同

* netstat -tnlp | grep nginx 查询引用端口


### 安装OpenSSL:
* tar -xzf openssl-1.0.2f.tar.gz
* cd openssl-1.0.2f
* mkdir /usr/local/openssl
* ./config --prefix=/usr/local/openssl
* make
* make install这样就安装完成了


* yum repolist 查看yum源
*  yum update 更新yum源库 

docker子供向硬件 不共享软件 
*  ps -aux | grep nginx //查看nginx进程
*  chown -R runoob:runoobgroup * //将目前目录下的所有文件与子目录的拥有者皆设为 runoob，群体的使用者 runoobgroup
*  chmod 755  最高权限     
* ls exports -l 查看权限
* chomd go+w 文件 赋予文件读写权限              
                               
### nfs

通过NFS共享数据
* docker之间文件共享 或 多个linux之间文件共享
>  mount /dev/sdb1 ~/Share/ ，把新硬盘的区sdb1挂载到工作目录的~/Share/文件夹下，之后访问这个~/Share/文件夹就相当于访问这个硬盘2的sdb1分区了。
对/Share/的任何操作，都相当于对sdb1里文件的操作。
在一些大公司已经在使用NFS，NFS是一种行之有效的选项作为文件中心服务。
Docker不支持NFS开箱即用,需要每个集装箱上安装NFS客户端。
* mount挂载的作用，就是将一个设备（通常是存储设备）挂接到一个已存在的目录上。访问这个目录就是访问该存储设备。
* linux的所有区都在/下的某个位置，比如/home
* Windows下，mount挂载，就是给磁盘分区提供一个盘符（C,D,E,...）。比如插入U盘后系统自动分配给了它I:盘符其实就是挂载，退优盘的时候进行安全弹出，其实就是卸载unmount

* ipvsadm -Ln 查看 网卡别名 和 负载均衡策略
*  systemctl status -l keepalived 查看keepalived状态
* ip addr show eth0
* iptables -t nat -nL --line-number
* 根据编号删除规则   iptables -t nat -D DOCKER $num
* unzip XXX.zip -d /roo/ 解压到指定目录

#### 指定文件 所属组和用户
* chown -R hsiar:hsiar logs //递归指定，递归文件夹下所有文件，并指定 所有者(用户)：用户组
```
[root@zhgl logs]# groups hsiar //查看用户所属组
hsiar : hsiar
// 组和用户相关的操作
 1001  groupadd -g 889 zk //创建用户组zk,889为gid
 1002  groups //查看当前登录用户所属组， groups zk1 //查看zk1所属组
 1004  useradd -u 600 zk1 //创建用户 zk1,uid为600
 1005  gpasswd -a zk1 zk //将用户zk1加入zk用户组
 1006  su - zk1 //切换用户到zk1用户
 1007  passwd zk1 //设置用户zk1密码
 1009  usermod -l zk11 zk1 //修改用户账户
 1012  usermod -g zk zk11 //将用户zk11加入zk用户组
 1013  usermod -d /zk/zk11 zk11 //为用户zk11的目录改为/zk/zk11
 [root@iZbp1ep0ql2r56axz76wx9Z zk1]# gpasswd -d zk11 zk //从zk用户组移除zk11用户
 Removing user zk11 from group zk
 [root@iZbp1ep0ql2r56axz76wx9Z zk1]# userdel  -r zk11 //删除zk11用户并删除该用户的目录
 userdel: zk11 home directory (zk11) not found
 [root@iZbp1ep0ql2r56axz76wx9Z zk1]# groupdel zk //删除用户组
```

```
drwxr-xr-x 2 root root 4096 06-29 14:30 Test 分段解释
d: 这个应该是目录吧 然后2 就是文件数.
rwxr-xr-x 这里是三段分开解释.r表示可读W表示可写x表示运行
rwx 表示文件所有者的权限
r-x 表示文件所有者所在组的权限
r-x 表示其他人的权限
第一个 root 用户
第二个 root 用户组
4096 是文件大小
06-29 14:30 是创建时间
test 文件名
```

```
//top命令
top - 14:34:52 up 62 days,  4:11,  4 users,  load average: 0.09, 0.18, 0.35
Tasks: 256 total,   1 running, 255 sleeping,   0 stopped,   0 zombie
%Cpu(s):  1.0 us,  0.9 sy,  0.0 ni, 98.1 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem : 16431644 total,   180564 free, 14926044 used,  1325036 buff/cache
KiB Swap:  4194300 total,   106320 free,  4087980 used.   847424 avail Mem 

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                  
 4225 ufr       20   0   11.7g   2.1g   1288 S   6.7 13.3 712:20.69 hsserver                                                 
 5774 pms       20   0 8321856   1.0g  10204 S   1.0  6.3  10:19.81 java                                                     
 9507 pms       20   0 8259444 932324      0 S   1.0  5.7  75:55.49 java                                                     
 2694 service   20   0 8211600 926608    100 S   0.7  5.6  33:38.16 java                                                     
 6569 root      20   0 6119968  75272      0 S   0.7  0.5  71:58.46 beam.smp                                                 
 9818 pms       20   0 8265340 859656      0 S   0.7  5.2  75:50.20 java                                                     
26968 pms       20   0 8303360 923512    384 S   0.7  5.6  72:20.74 java                                                     
30868 pms       20   0 8320972 962364      0 S   0.7  5.9  78:17.07 java                                                     
32121 pms       20   0 7999088 996.5m  10408 S   0.7  6.2   2:46.08 java                                                     
    9 root      20   0       0      0      0 S   0.3  0.0  69:09.76 rcu_sched                                                
 1534 gdm       20   0  739780  18032    440 S   0.3  0.1 123:57.61 gsd-color                                                
 7741 root      20   0  162104   2404   1580 R   0.3  0.0   0:01.39 top                                                      
 9132 hsiar     20   0  295268  28356   1628 S   0.3  0.2   0:25.48 nginx                                                    
30740 root      20   0  163936   6496   4748 S   0.3  0.0   0:12.55 sshd          

[root@zhgl home]# df -lh
Filesystem               Size  Used Avail Use% Mounted on
/dev/mapper/centos-root  153G  123G   24G  85% /
devtmpfs                 7.9G     0  7.9G   0% /dev
tmpfs                    7.9G  1.2M  7.9G   1% /dev/shm
tmpfs                    7.9G  867M  7.0G  11% /run
tmpfs                    7.9G     0  7.9G   0% /sys/fs/cgroup
/dev/sda1                772M  129M  588M  18% /boot
tmpfs                    1.6G   12K  1.6G   1% /run/user/42
tmpfs                    1.6G     0  1.6G   0% /run/user/0
tmpfs                    1.6G     0  1.6G   0% /run/user/1011
tmpfs                    1.6G     0  1.6G   0% /run/user/1012
tmpfs                    1.6G     0  1.6G   0% /run/user/1014
tmpfs                    1.6G     0  1.6G   0% /run/user/1013
tmpfs                    1.6G     0  1.6G   0% /run/user/1008
```



```
[root@zhgl ~]# cat /proc/cpuinfo | grep name | sort | uniq
model name	: Intel(R) Xeon(R) CPU E5-2670 v3 @ 2.30GHz
[root@zhgl ~]# 
[root@zhgl ~]# 
[root@zhgl ~]# cat /proc/cpuinfo | grep "physical id"  
physical id	: 0
physical id	: 0
physical id	: 0
physical id	: 0
physical id	: 1
physical id	: 1
physical id	: 1
physical id	: 1
[root@zhgl ~]# 
[root@zhgl ~]# cat /proc/cpuinfo | grep "physical id" | sort | uniq | wc -l
远程登陆问题解决
[root@zhgl ~]# cat /etc/passwd | grep yqbuser
yqbuser:x:890:1016::/home/yqbuser:/bin/bash
-- 增加写权限
[root@zhgl ~]# sudo chmod u+w /etc/sudoers
[root@zhgl ~]# 
[root@zhgl ~]# ls -l /etc/sudoers
-rw-r-----. 1 root root 3938 Apr 11  2018 /etc/sudoers

sudo vi /etc/sudoers

```

修改root用户密码： passwd root

查看ip

ip addr

查看端口号占用程序

lsof -i :端口号 或 netstat -tunlp |grep 端口号

### 新增用户添加远程登录

* 查看命令帮助
  * man ls //查看所有命令 
  * info ls //查看所有命令 最全
  * help ls // 只能查看内置命令
  * type ls //查看命令是内建还是外部

* yum 适用于redhat，fedora，centos等系列linux发行版

* apt-get 适用于debian，ubuntu，linux mint等系列的linux发行版



rpm -ivh  XXX.rpm

### 端口

开放端口 iptables -I INPUT -p tcp --dport 80 -j ACCEPT

保存 iptables-save

测试远程端口是否打开 telnet 192.168.39.44 28081

安装iptables持久化工具 yum install iptables-persistent

进行持久化 sudo netfilter-persistent save



### 防火墙

查看防火墙状态 systemctl firewalld status

查看开放端口  firewall-cmd --list-all | grep 28081



### 环境变量

移除软连接 sudo rm /home/root/zookeeper/JRE/jre1.8.0_181/bin/java

增加软连接 sudo ln -s /usr/java/jdk/bin/java  /home/root/zookeeper/JRE/jre1.8.0_181/bin/java 

编辑环境变量配置文件 vim /etc/profile

生效 source /etc/profile

查看java命令的位置

which java

安装jps命令 yum install java-1.8.0-openjdk-devel.x86_64 



### 查看机器信息

查看物理cpu数

cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l

查看单个cpu的核数

cat /proc/cpuinfo| grep "cpu cores"| uniq

查看逻辑cpu个数（物理cpu总数 * 当个cpu核数 ？）

cat /proc/cpuinfo| grep "processor"| wc -l 

查看cpu型号

cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c



查看命令

uname -a # 查看内核/操作系统/CPU信息的linux系统信息  
head -n l /etc/issue # 查看操作系统版本  
cat /proc/cpuinfo # 查看CPU信息  
hostname # 查看计算机名的linux系统信息命令  
lspci -tv # 列出所有PCI设备   
lsusb -tv # 列出所有USB设备的linux系统信息命令  
lsmod # 列出加载的内核模块   
env # 查看环境变量资源  
free -m # 查看内存使用量和交换区使用量   
df -h # 查看各分区使用情况  
du -sh # 查看指定目录的大小   
grep MemTotal /proc/meminfo # 查看内存总量  
grep MemFree /proc/meminfo # 查看空闲内存量   
uptime # 查看系统运行时间、用户数、负载  
cat /proc/loadavg # 查看系统负载磁盘和分区   
mount | column -t # 查看挂接的分区状态  
fdisk -l # 查看所有分区   
swapon -s # 查看所有交换分区  
hdparm -i /dev/hda # 查看磁盘参数(仅适用于IDE设备)   
dmesg | grep IDE # 查看启动时IDE设备检测状况网络  
ifconfig # 查看所有网络接口的属性   
iptables -L # 查看防火墙设置  
route -n # 查看路由表   
netstat -lntp # 查看所有监听端口  
netstat -antp # 查看所有已经建立的连接   
netstat -s # 查看网络统计信息进程  
ps -ef # 查看所有进程   
top # 实时显示进程状态用户  
w # 查看活动用户   
id # 查看指定用户信息  
last # 查看用户登录日志   
cut -d: -f1 /etc/passwd # 查看系统所有用户  
cut -d: -f1 /etc/group # 查看系统所有组   
crontab -l # 查看当前用户的计划任务服务  
chkconfig –list # 列出所有系统服务   
chkconfig –list | grep on # 列出所有启动的系统服务程序  
rpm -qa # 查看所有安装的软件包   
cat /proc/cpuinfo ：查看CPU相关参数的linux系统命令  
cat /proc/partitions ：查看linux硬盘和分区信息的系统信息命令   
cat /proc/meminfo ：查看linux系统内存信息的linux系统命令  
cat /proc/version ：查看版本，类似uname -r   
cat /proc/ioports ：查看设备io端口  
cat /proc/interrupts ：查看中断   
cat /proc/pci ：查看pci设备的信息  
cat /proc/swaps ：查看所有swap分区的信息



文件传输

scp pms.zip  root@192.168.51.30:/home/

查看所有用户

vim /etc/group