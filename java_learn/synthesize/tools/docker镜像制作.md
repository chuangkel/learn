宿主机登陆

ssh -p 2024 root@172.17.0.2

修改/etc/sshd/ssh_config 允许Root登陆

重启docker生效 docker restart mysql

重启之后 开启远程登陆 docker exec -it ae2c089e894c /usr/sbin/sshd



防火墙

 firewalld 和 iptables 两种不同的防火墙规则的配置方式，不能同时启动

安装

cat /etc/redhat-release 
CentOS Linux release 7.5.1804 (Core) 
[root@zhgl init.d]# rpm -qa|grep iptables
iptables-1.4.21-24.el7.x86_64
[root@zhgl init.d]# yum -y install iptables-services

启动防火墙

service iptables start
Redirecting to /bin/systemctl start iptables.service
查看防火墙状态
[root@zhgl init.d]# service iptables status 

添加防火墙规则

iptables  -I OUTPUT -o eth0 -p tcp --sport 15845 -j ACCEPT
iptables -I INPUT -i eth0 -p tcp --dport 15845 -j ACCEPT

保存规则

 service iptables save

/etc/rc.d/init.d/iptables save



端口映射

iptables -t nat -A  DOCKER -p tcp --dport 15845 -j DNAT --to-destination 172.17.0.2:15845 

iptables -t nat -nvL  增加完可以用这个命令看到 



列出主机上的网桥

sudo brctl show

查看当前docker0 ip

sudo ifconfig docker0



运行镜像

docker run -d -p 12024:2024 -p 18081:8081 -p 18883:8883 -p 13306:3306 -p 12181:2181 -p 16379:6379 -p 15672:5672 192.168.44.132/pmstest/pms_20190911_1:pmstest  /usr/sbin/sshd -D



删除镜像 

docker image inspect --format='{{.RepoTags}} {{.Id}} {{.Parent}}' $(docker image ls -q --filter since=XXX) # XXX指镜像ID

查询

docker ps -a | grep "Exited" | awk '{print $1 }'|xargs docker stop

删除

 docker ps -a | grep "Exited" | awk '{print $1 }'|xargs docker rm





 docker images -f "dangling=true" -q



镜像打包

docker save -o   pms.zip 仓库:tag  

镜像解包

 docker load < pms.zip







