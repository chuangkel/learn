

see2.0/tomcat/webapps/acm/WEB-INF/conf/cas.properties  文件中改成cas.enable=false 
然后sh shutDown.sh停止，sh startUp.sh再启动就好了







  docker run -itd --name pmdockeros100 --hostname=pmshost00 --net pmnet --ip 192.168.200.100 -p 11100:22  -p 28101:33061 -p 28102:5672 -p 28103:15672 -p 28104:8080 -p 28105:8081 -p 28106:6379 -p 28107:2181  -m 8G  --memory-swap=10G  --oom-kill-disable --cpus=4 --privileged=true -v /home/docker/dockeros100:/home -v /etc/localtime:/etc/localtime:ro pm/centos:centos7.5  /usr/sbin/init

docker run -itd --name pmdockeros101  --hostname=pmshost01 --net pmnet --ip 192.168.200.101 -p 11101:22  -p 28151:33061 -p 28152:5672 -p 28153:15672 -p 28154:8080 -p 28155:8081 -p 28156:6379 -p 28157:2181  -m 8G  --memory-swap=10G  --oom-kill-disable --cpus=4 --privileged=true -v /home/docker/dockeros101:/home -v /etc/localtime:/etc/localtime:ro  pm/centos:centos7.5  /usr/sbin/init  



 docker run -d --hostname power_env --name power_env  -p 12024:2024 -p 18081:8081 -p 18883:8883 -p 13306:33061 -p 32181:2181 -p 16379:6379 -p 35672:5672  local/images:pms  /usr/sbin/sshd -D  --privileged=true