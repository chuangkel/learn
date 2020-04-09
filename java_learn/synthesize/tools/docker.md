查看本地镜像
docker images
运行id镜像
docker run -it id bash
保存为镜像
docker commit 9e9626eda294  yaoqb_python/python-dev:version1

//查看docker的启动状态
docker ps 

```
敲apt-get install vim命令时，提示：
Reading package lists... Done
Building dependency tree       
Reading state information... Done
E: Unable to locate package vim
```

apt-get update这个命令的作用是：同步 /etc/apt/sources.list 和 /etc/apt/sources.list.d 中列出的源的索引，这样才能获取到最新的软件包。
等更新完毕以后再敲命令：apt-get install vim命令即可

docker_mysql
启动命令：docker exec -it docker_mysql /bin/bash
docker 容器 修改mysql密码

1.进入 mysql 容器

docker exec -it mysql /bin/bash

2.修改mysql容器的配置文件

打开配置文件的命令
vi /etc/mysql/my.cnf
在最后一行添加如下代码( //跳过mysql的密码验证)
skip-grant-tables

3.重启mysql容器
docker restart mysql


4.登录docker,重新进入容器输入命令root为用户名
mysql -u root -p

5.修改密码的命令
update mysql.user set authentication_string= "@HSDZgfyxgs3588h" where User = "root"
update user set plugin="mysql_native_password"; #如果没这一行可能也会报一个错误，因此需要运行这一行
> flush privileges; #更新所有操作权限
> quit;
SET PASSWORD FOR 'root'@'%' = PASSWORD('@HSDZgfyxgs3588h'); 

### 制作镜像-上传-下载镜像-运行镜像
* docker commit 镜像id  docker用户名/docker仓库名  //提交镜像到本地
  * sudo docker commit -m "Added json gem" -a "Docker Newbee" 0683ed74bd57  ouruser/sinatra:v2
  * 0683ed74bd57  是用来创建镜像的容器ID
  * ouruser/sinatra:v2 是仓库：tag

docker login //登录docker 输入密码
docker push  docker用户名/docker仓库名 //提交镜到远程
docker pull docker用户名/docker仓库名 //拉取镜像到本地 

* 列出所有容器ID
docker ps -aq
* 查看所有运行或者不运行容器
docker ps -a
* 停止所有的container（容器），这样才能够删除其中的images：
docker stop $(docker ps -a -q) 或者 docker stop $(docker ps -aq) 
* 如果想要删除所有container（容器）的话再加一个指令：
docker rm $(docker ps -a -q) 或者 docker rm $(docker ps -aq) 
* 查看当前有些什么images
docker images
* 删除images（镜像），通过image的id来指定删除谁
docker rmi <image id>
* 想要删除untagged images，也就是那些id为的image的话可以用
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
* 要删除全部image（镜像）的话
docker rmi $(docker images -q)
* 强制删除全部image的话
docker rmi -f $(docker images -q)
* 从容器到宿主机复制
 docker cp tomcat：/webapps/js/text.js /home/admin
 docker  cp 容器名:  容器路径       宿主机路径         
* 从宿主机到容器复制
 docker cp /home/admin/text.js tomcat：/webapps/js
 docker cp 宿主路径中文件      容器名  容器路径   
* 删除所有停止的容器
docker container prune
* 删除所有不使用的镜像
docker image prune --force --all或者docker image prune -f -a
* 停止、启动、杀死、重启一个容器
docker stop Name或者ID  
docker start Name或者ID  
docker kill Name或者ID  
docker restart name或者ID

* docker进入容器，查看配置文件
docker exec ：在运行的容器中执行命令
        -d :分离模式: 在后台运行
        -i :即使没有附加也保持STDIN（标准输入） 打开,以交互模式运行容器，通常与 -t 同时使用；
        -t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；
docker exec -it  f94d2c317477 /bin/bash

出现root@f94d2c317477:/usr/share/elasticsearch/config# vi elasticsearch.yml
bash: vi: command not found
apt-get update && apt-get install vim -y

* 修改配置、退出容器
1、如果要正常退出不关闭容器，请按Ctrl+P+Q进行退出容器
2、如果使用exit退出，那么在退出之后会关闭容器，可以使用下面的流程进行恢复
使用docker restart命令重启容器
使用docker attach命令进入容器

## 
 * docker pull centos 
 * docker image ls
 * cat /proc/version //查看系统版本 

Docker run ：创建一个新的容器并运行一个命令

#### ssh tools

检查是否安装 ssh localhost

安装 apt-get install openssh-server   

启动 sudo /etc/init.d/ssh start  

检查启动 ps -e|grep ssh   

编辑配置 vim /etc/ssh/sshd_config   

停止 sudo stop ssh

卸载 apt-get –purge remove openssh-server

##### 配置密钥对验证

生成密钥对 ssh -keygen -t rsa

登陆 ssh -p 22 root@192.168.1.1

将公钥上传到指定机器目录 ssh-copy-id -i /root/.ssh/id rsa.pub root@192.168.2.1

#### 配置yum源

进入目录 cd /etc/yum.repos.d

将如下文件下入163.repo ,也可以是其他

```bash
[163]
name=163
baseurl=http://mirrors.163.com/centos/7/os/x86_64/
gpgcheck=0
enabled=1
``` 

### docker

使用镜像nginx:latest以后台模式创建名为mynginx的容器

docker run --name mynginx -d nginx:latest 

随机映射端口

docker run -P -d registry.XXX.com/hcs/centos:latest 

端口映射 docker的80到宿主机80，主机目录的/data映射到docker的/docker

docker run -p 80:80 -v /data:/data -d registry.XXX.com/hcs/centos:latest 

以交互方式启动容器，在容器中执行/bin/bash，nginx:latest <=> 仓库：TAG，exit之后，docker不运行

docker run -it nginx:latest /bin/bash  

启动容器，映射端口，打通22端口远程登录

 docker run -d -p 50001:22 registry.XXX.com/library/centos:7.2.1511 /usr/sbin/sshd -D

宿主机的3306端口绑定到容器的3306端口 

docker run -tid –name db -p 3306:3306 MySQL 

##### Docker4种网络模式

- host: 公用一个network namespace,使用宿主机的ip和端口
- container: 和已经存在的一个容器共享network namespace
- none: 独立的network namespace,需要手动添加网络配置
- bridge: 默认配置,为容器自动分配namespace,设置IP,并将容器连接到一个虚拟网络

Docker宿主机上可以同时存在多个不同类型的网络。位于不同网络中的容器，彼此之间无法通信。Docker容器的跨网络隔离与通信，是借助了iptables的机制

bridge 网络模式；--network不指定，默认也是bridge模式



##### 宿主机拷贝到docker文件

查看docker 容器ID docker ps

根据docker 容器ID生成字符串

 docker inspect -f  '{{.Id}}' 0f7d485f2fc1 生成 0f7d485f2fc18b172822e9209e05760343b25d4dd888c1430fa296f8c9364653 字符串

开始拷贝

docker cp /home/ssh 0f7d485f2fc18b172822e9209e05760343b25d4dd888c1430fa296f8c9364653:/root/ssh

​        

```
[root@host152 ~]# docker ps | grep  11216 //查看对外端口为11216的实例
ae2c089e894c        192.168.44.132/tyyh/cmc20190511:tyyh   "/usr/sbin/init bash"   3 months ago        Up 10 weeks         0.0.0.0:10116->22/tcp, 0.0.0.0:11216->2024/tcp, 0.0.0.0:11616->2181/tcp, 0.0.0.0:10416->3306/tcp, 0.0.0.0:11416->5672/tcp, 0.0.0.0:10316->8088/tcp, 0.0.0.0:11716->8888/tcp, 0.0.0.0:10516->9910/tcp, 0.0.0.0:10616->9911/tcp, 0.0.0.0:10716->9912/tcp, 0.0.0.0:10816->9913/tcp, 0.0.0.0:10916->9914/tcp, 0.0.0.0:11016->9915/tcp, 0.0.0.0:11116->9916/tcp, 0.0.0.0:11316->9999/tcp, 0.0.0.0:11516->15672/tcp, 0.0.0.0:10216->15827/tcp                             MriskTest
[root@host152 ~]# 
[root@host152 ~]# docker restart ae2c089e894c //重启实例
```

- docker exec -it ae2c089e894c /usr/sbin/sshd //重启之后开启docker远程登录

//创建容器，处于停止状态 本地有就使用本地镜像，
//没有则从远程镜像库拉取。创建成功后会返回一个容器的ID 

- docker create centos:lastest 
- iptables -t nat -A  DOCKER -p tcp --dport 15845 -j DNAT --to-destination 172.17.0.2:15845 
- iptables -t nat -nvL  增加完可以用这个命令看到  
  删除端口映射
  a. 获取规则编号   iptables -t nat -nL --line-number
  b. 根据编号删除规则    iptables -t nat -D DOCKER $num
- docker ps 看不到
  以上指令会将容器的ip192.168.0.100和80端口，映射到宿主机的8000端口。 
- docker run -p 192.168.0.100:8000:80 -it ubuntu /bin/bash
- docker run -d -p 127.0.0.1:5000:5000 -it centos /bin/bash
  实例目录
- /var/lib/docker/containers/05319c75265aed65356050345febc18e1c39a5b7afbf5b878d5624df89cb49ed

```
docker port container_ID #容器ID
#结果输出
80/tcp -> 0.0.0.0:800
```







