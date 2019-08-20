# haproxy+keepalived来实现mariadb galera cluster的高可用架构

## haproxy
二层负载均衡会通过一个虚拟 MAC 地址接收请求，然后再分配到真实的 MAC 地址；
三层负载均衡会通过一个虚拟 IP 地址接收请求，然后再分配到真实的 IP 地址；
四层通过虚拟 IP + 端口接收请求，然后再分配到真实的服务器；
七层通过虚拟的 URL 或主机名接收请求，然后再分配到真实的服务器。

## keepalived
> Keepalived是基于vrrp协议,Keepailived有一台主服务器和多台备份服务器，在主服务器和备份服务器上面部署相同的服务配置，使用一个虚拟IP地址对外提供服务，当主服务器出现故障时，虚拟IP地址会自动漂移到备份服务器
VRRP（Virtual Router Redundancy Protocol，虚拟路由器冗余协议）
## mariadb galera cluster
> MariaDB Galera Cluster is a synchronous multi-master cluster for MariaDB. It is available on Linux only, and only supports the XtraDB/InnoDB storage engines