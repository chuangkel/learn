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
启动命令：docker exec it docker_mysql /bin/bash
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
