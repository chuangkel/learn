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


docker commit 镜像id  docker用户名/docker仓库名  //提交镜像到本地
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
