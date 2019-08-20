>在linux机器为ip上抓包
tcpdump tcp  port 12200 and host ip -w /tmp/20190120.pcap

>查看本机ip
hostname -i

>cd - 返回上一层目录

>//查看网卡信息
ip address show

>//查看路由信息 ip route show

>ll -ctr

>ll -lh

>sed -e 's/替换字符串/目标字符串/g' 要替换的文件

>sed -i 's/000710"/000424/g' * //单引号

>find . | args grep -ri "2018" |

>grep -c 20109 1.sql

>grep -C 10 xxx.xx

>rename a b a

>which python

>zip 包存放路径/xxx.zip aaa/* //aaa是文件 需在aaa所在目录下

>ll -h|grep G

>du -a|sort -rn|head -5

>wc -l 文件名 //查看行数

>head -c 20 file　　显示文件file的前20个字节

>head -5 file　　显示文件file的前5行

>grep -c 1D0618060587021D7 30_305_trade_apply_20181023_06.txt

>tar -xvf file.tar //解压 tar包

>find / -name java

>find . -name .svn | xargs rm -rf

>netstat -anp | grep 12200 查询监听端口12200
netstat -ntpl //查看
 ps -aux | grep nginx
>command >out.file 2>&1 &
// command>out.file是将command的输出重定向到out.file文件，即输出内容不打印到屏幕上，而是输出到out.file文件中。
// 2>&1 是将标准出错重定向到标准输出，这里的标准输出已经重定向到了out.file文件，即将标准出错也输出到out.file文件中。最后一个&， 是让该命令在后台执行。
// 试想2>1代表什么，2与>结合代表错误重定向，而1则代表错误重定向到一个文件1，而不代表标准输出；换成2>&1，&与1结合就代表标准输出了，就变成错误重定向到标准输出

>l 安装和登录命令：login、 shutdown、 halt、 reboot 、mount、umount 、chsh
l 文件处理命令：file、 mkdir、 grep、dd、 find、 mv 、ls 、diff、 cat、 ln
l 系统管理相关命令： df、 top、 free、 quota 、at、 lp、 adduser、 groupadd kill、 crontab、 tar、 unzip、 gunzip 、last
l 网络操作命令：ifconfig、 ip 、ping 、 netstat 、telnet、 ftp、 route、 rlogin rcp 、finger 、mail 、nslookup
l 系统安全相关命令： passwd 、su、 umask 、chgrp、 chmod、chown、chattr、sudo、 pswho

>ps //查看进程
ps -ef //显示所有进程信息，连同命令行
ps -A //显示所有命令
ps -A|grep mysql //查看mysql进程
ps -ef | grep mysql //查看带有字符串mysql的进程

>sudo kill -9 pid //强制kill进程pid

>chown -R mysql:mysql /var/lib/mysql //chown授权

>netstat -anp | grep 3306 //查看3306端口的占用程序

>grep -rn "DOWNTUXEDO" * //查找字符串

>grep "grep_pattern" file_name;
//grep（global search regular expression(RE) and print out the //line，全面搜索正则表达式并把行打印出来）是一种强
//大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。

>//在linux中，&和&&,|和||介绍如下：
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

>vim模式下 命令模式dd删除一行

>touch test.sh 创建脚本

>rename test aaa *.sh //将当前目录的所有test.sh 命名为aaa.sh

>ll -ctrh //按时间排序列表

>grep -C 5 aaa 文件名

>grep -r aa 文件名 //-r递归

>tail -100f fintaskcenter-biztask.log 获取日志最后100行，持续刷新

>su admin //切换到admin用户

>top //Linux下常用的性能分析工具  能够实时显示系统中各个进程的资源占用状况。

>date > date.log //将当前时间写入date.log 覆盖
date >> date.log //追加

>stat filename //显示filename 当前的时间
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

>-exec 命令2 {} \ ; 调用命令2 ,{} 是传入的参数，\ 为了转义
 
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
* chown -R runoob:runoobgroup * //将目前目录下的所有文件与子目录的拥有者皆设为 runoob，群体的使用者 runoobgroup:
                          
```
[root@host152 ~]# docker ps | grep  11216 //查看对外端口为11216的实例
ae2c089e894c        192.168.44.132/tyyh/cmc20190511:tyyh   "/usr/sbin/init bash"   3 months ago        Up 10 weeks         0.0.0.0:10116->22/tcp, 0.0.0.0:11216->2024/tcp, 0.0.0.0:11616->2181/tcp, 0.0.0.0:10416->3306/tcp, 0.0.0.0:11416->5672/tcp, 0.0.0.0:10316->8088/tcp, 0.0.0.0:11716->8888/tcp, 0.0.0.0:10516->9910/tcp, 0.0.0.0:10616->9911/tcp, 0.0.0.0:10716->9912/tcp, 0.0.0.0:10816->9913/tcp, 0.0.0.0:10916->9914/tcp, 0.0.0.0:11016->9915/tcp, 0.0.0.0:11116->9916/tcp, 0.0.0.0:11316->9999/tcp, 0.0.0.0:11516->15672/tcp, 0.0.0.0:10216->15827/tcp                             MriskTest
[root@host152 ~]# 
[root@host152 ~]# docker restart ae2c089e894c //重启实例
```
* docker exec -it ae2c089e894c /usr/sbin/sshd //重启之后开启docker远程登录

//创建容器，处于停止状态 本地有就使用本地镜像，
//没有则从远程镜像库拉取。创建成功后会返回一个容器的ID 
* docker create centos:lastest 
* iptables -t nat -A  DOCKER -p tcp --dport 15845 -j DNAT --to-destination 172.17.0.2:15845 
* iptables -t nat -nvL  增加完可以用这个命令看到  
* docker ps 看不到
以上指令会将容器的ip192.168.0.100和80端口，映射到宿主机的8000端口。 
* docker run -p 192.168.0.100:8000:80 -it ubuntu /bin/bash
* docker run -d -p 127.0.0.1:5000:5000 -it centos /bin/bash
实例目录
* /var/lib/docker/containers/05319c75265aed65356050345febc18e1c39a5b7afbf5b878d5624df89cb49ed
```
docker port container_ID #容器ID
#结果输出
80/tcp -> 0.0.0.0:800
```
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