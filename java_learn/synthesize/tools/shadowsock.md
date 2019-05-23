测试通过：
1.安装shadowsocks

wget --no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-libev.sh
chmod +x shadowsocks-libev.sh
./shadowsocks-libev.sh 2>&1 | tee shadowsocks-libev.log

2、卸载方法
使用 root 用户登录，运行以下命令
./shadowsocks-libev.sh uninstall

其它事项：
客户端配置的参考链接：https://teddysun.com/339.html
安装完成后即已后台启动 Shadowsocks-libev ，运行：
/etc/init.d/shadowsocks status
可以查看进程是否启动。
本脚本安装完成后，会将 Shadowsocks-libev 加入开机自启动。

3、使用命令：
启动：/etc/init.d/shadowsocks start
停止：/etc/init.d/shadowsocks stop
重启：/etc/init.d/shadowsocks restart
查看状态：/etc/init.d/shadowsocks status




ShadowsocksR安装/搭建心得
2017-07-21 分类：网际穿梭 / 轻心得 阅读(25739)    评论(8)
——最后更新于2017.10.12 00:50.如因时间过久而失效，请留言指出。

shadowsocksR与shadowsocks的主要区别是混淆、协议。

ShadowsocksR的混淆协议和插件可以欺骗某wall目前的流量匹配功能和QOS限速。

shadowsocksR多用户模式：

shadowsocksR单服务器多用户模式搭建

安装的大致步骤：

1.基本库安装(git)

2.下载SSR的源码

3.初始化配置

4.使用配置文件运行或命令行启动

5.多端口配置（可选）

6.配置文件参数说明（可选）


1.基本库安装

centos：

yum install git
ubuntu/debian：

apt-get install git

2.下载SSR的源码

git clone https://github.com/hao35954514/shadowsocksR-b.git
执行完毕后，当前的目录会多一个shadowsocksR-b目录。其中shadowsocksR-b的是多用户版（即数据库版，个人用户请忽略这个），子目录中的shadowsocks是单用户版。

3.初始化配置

apt-get install python-pip    //安装Python（ubuntu）-------（centos命令应该为：yum -y install python-pip，下次我试试）
cd shadowsocksR-b/
bash initcfg.sh
4.运行
4.1 命令行直接运行：

python server.py -p 443 -k password -m aes-256-cfb -O auth_sha1_v4 -o http_simple
#说明：-p 端口 -k 密码  -m 加密方式 -O 协议插件 -o 混淆插件

命令行后台运行：

python server.py -p 443 -k password -m aes-256-cfb -O auth_sha1_v4 -o http_simple -d start
4.2 使用配置文件运行：

编辑shadowsocksR-b文件夹中的user-config.json文件。具体修改教程：https://github.com/breakwa11/shadowsocks-rss/wiki/config.json

一般情况下，只需要修改以下五项即可：

“server_port”:8388,        //端口
“password”:”password”,     //密码
“protocol”:”origin”,       //协议插件
“obfs”:”http_simple”,      //混淆插件
“method”:”aes-256-cfb”,    //加密方式

最后运行shadowsocks内的server.py即可：

python server.py
后台运行：

python server.py -d start
其他操作：

python server.py -d stop/restart

5.多端口配置

示例：

{
    "server": "0.0.0.0",
    "server_ipv6": "::",
    "local_address": "127.0.0.1",
    "local_port": 1080,
    "port_password":{
        "8888":"password1",
        "9999":"password2",
        "2333":"password3"
    },
    "timeout": 120,
    "udp_timeout": 60,
    "method": "aes-128-ctr",
    "protocol": "auth_aes128_md5",
    "protocol_param": "",
    "obfs": "tls1.2_ticket_auth_compatible",
    "obfs_param": "",
    "speed_limit_per_con": 0,
    "speed_limit_per_user": 0,
    "dns_ipv6": false,
    "connect_verbose_info": 0,
    "redirect": "",
    "fast_open": false
}

为每个端口配置不同的混淆协议：

{
    "server":"0.0.0.0",
    "server_ipv6":"::",
    "local_address":"127.0.0.1",
    "local_port":1080,
    "port_password":{
        "8388":{"protocol":"auth_simple", "password":"abcde", "obfs":"http_simple", "obfs_param":""},
        "8389":{"protocol":"origin", "password":"abcde"}
    },
    "timeout":300,
    "method":"aes-128-ctr",
    "protocol": "auth_aes128_md5",
    "protocol_param": "",
    "obfs": "tls1.2_ticket_auth_compatible",
    "obfs_param": "",
    "speed_limit_per_con": 0,
    "speed_limit_per_user": 0,
    "redirect": "",
    "dns_ipv6": false,
    "fast_open": false,
    "workers": 1
}

6.配置文件参数说明（SSR支持单线程限速、总端口限速）

搜索:
参数名称    描述    中文描述
server    the address your server listens    监听地址
server_ipv6    the ipv6 address your server listens    ipv6地址
server_port    server port    监听端口
local_address    the address your local listens    本地地址
local_port    local port    本地端口
password    password used for encryption    SSR密码
timeout    in seconds    超时时间
method    default: “aes-128-cft”    加密方式
protocol_param    default：””    协议插件参数，可设置每个端口的链接设备数，比如”5″就是一个端口最大5个设备连接
protocol    default：”auth_aes128_md5″    协议插件，默认”auth_aes128_md5″
obfs    default：”tls1.2_ticket_auth_compatible”    混淆插件，默认”tls1.2_ticket_auth_compatible”
obfs_param    default：””    混淆插件参数，默认””
speed_limit_per_con    default：0    单线程限速，默认 0，单位KB/S
speed_limit_per_user    default：0    端口总限速，默认 0，单位 KB/S
redirect    default：””    重定向参数，默认””
dns_ipv6    default:false    是否优先使用IPv6地址，有IPv6时可开启
fast_open    use TCP_FASTOPEN, true / false    快速打开(仅限linux客户端)
workers    number of workers, available on Unix/Linux    线程（仅限linux客户端）显示第 1 至 18 项结果，共 18 项




教程二

wget --no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocksR.sh
chmod +x shadowsocksR.sh
./shadowsocksR.sh 2>&1 | tee shadowsocksR.log
运行：/etc/init.d/shadowsocks status
使用命令：
启动：/etc/init.d/shadowsocks start
停止：/etc/init.d/shadowsocks stop
重启：/etc/init.d/shadowsocks restart
状态：/etc/init.d/shadowsocks status
配置文件路径：/etc/shadowsocks.json
日志文件路径：/var/log/shadowsocks.log
代码安装目录：/usr/local/shadowsocks
卸载：./shadowsocksR.sh uninstall

{
"server":"0.0.0.0",
"server_ipv6": "[::]",
"local_address":"127.0.0.1",
"local_port":1080,
"port_password":{
    "8989":"password1",
    "8990":"password2",
    "8991":"password3"
},
"timeout":300,
"method":"aes-256-cfb",
"protocol": "origin",
"protocol_param": "",
"obfs": "plain",
"obfs_param": "",
"redirect": "",
"dns_ipv6": false,
"fast_open": false,
"workers": 1
}

