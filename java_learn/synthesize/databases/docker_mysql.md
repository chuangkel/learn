首先安装docker服务
yum -y install docker   

启动docker
systemctl start docker

docker中搜索可用镜像
docker search mysql

拉取MySQL镜像
docker pull mysql:5.6

查看MySQL镜像
docker image ls

运行mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=@HSDZgfyxgs3588h -d -i -p 3306:3306 --restart=always  mysql:5.6
以上参数的含义：
--name mysql  将容器命名为mysql，后面可以用这个name进行容器的启动暂停等操作
-e MYSQL_ROOT_PASSWORD=123456 设置MySQL密码为123456
-d 此容器在后台运行,并且返回容器的ID
-i 以交互模式运行容器
-p 进行端口映射，格式为主机(宿主)端口:容器端口
--restart=always 当docker重启时，该容器自动重启

/usr/bin/docker-current: Error response from daemon: oci runtime error: container_linux.go:235: starting container process caused "process_linux.go:258: applying cgroup configuration for process caused \"Cannot set property TasksAccounting, or unknown property.\"".
解决：主要原因还是centos系统版本兼容性问题，如果将系统做更新升级，即可解决。
[root@VM_0_10_centos ~]#yum update

运行MySQL容器
docker exec -ti mysql bash


进入mysql，查看mysql的全局端口(还需注意系统是否开启了3306端口)
show global variables like 'port';
netstat -tap | grep mysql //查看端口是否开启
查询数据库用户
mysql> SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;
+------------------------------+
| query                        |
+------------------------------+
| User: 'root'@'%';            |
| User: 'root'@'127.0.0.1';    |
| User: 'root'@'::1';          |
| User: ''@'a934ee46bfb9';     |
| User: 'root'@'a934ee46bfb9'; |
| User: ''@'localhost';        |
| User: 'root'@'localhost';    |
+------------------------------+
7 rows in set (0.00 sec)

