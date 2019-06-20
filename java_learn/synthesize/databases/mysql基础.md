# 数据库


###### 读写分离
###### 幂等
多次执行和一次执行的效果相同。
###### 共享锁(读锁)
事务A对对象O加共享锁，事务B也可对对象O加共享锁，但是不能对对象O加排它锁。
###### 排它锁(写锁)
事务A占用了对象O之后，其他事务不可对对象O加锁。
select * from user for update;//使用了排它锁，解决并发问题。
###### 乐观锁：
假设可以获得数据库锁，直接去进行增删改查操作，如果能获取锁，则执行，不能获取锁则等待。
一查二比较三更新。
需要在表上设置修改版本字段，每次修改都需要更新该字段。
###### 悲观锁（排他锁是悲观锁）：
先对数据库表或字段或记录进行加锁，然后进行增删改查。
一锁二查三更新
### 语法：
###### left join
左连接
```
//linux远程登录mysql 需要安装linux远程访问数据库工具
mysql -uroop -ppassword  -h server_ip;
//在数据库 information_schema 中执行，实现sql拼接
SELECT concat(
	'insert into ',TABLE_SCHEMA,'.',
	substr(TABLE_NAME, 1, 11),
	substr(TABLE_NAME ,- 2),' select * from ',TABLE_SCHEMA,'.',TABLE_NAME,';commit; ')
FROM
	TABLES
WHERE
	table_Name LIKE 'fund_share_backup%'
AND TABLE_NAME NOT LIKE '%_t';
```
<![CDATA[<>]]>
//返回更新的行数
t_girl=# update t1 set log_time = now() where id in (1,2,3) returning *;
```
第一种方法： 
用转义字符把”>”和”<”替换掉，就没有问题了。
<if test="startTime != null ">
        AND order_date &gt;= #{startTime,jdbcType=DATE} 
    </if>
    <if test="endTime != null ">
        AND order_date &lt;= #{endTime,jdbcType=DATE} 
    </if>
```


注意下，这里的startTime，endTime都是Date类型的~

附：XML转义字符

< < 小于号 
> > 大于号 
& & 和 
' ’ 单引号 
" ” 双引号

第二种方法：

使用<![CDATA[ ]]>符号进行说明，将此类符号不进行解析 
mapper文件示例代码


```
<if test="startTime != null ">
    AND <![CDATA[ order_date >= #{startTime,jdbcType=DATE}  ]]>
</if>
<if test="endTime != null ">
    AND <![CDATA[ order_date <= #{endTime,jdbcType=DATE}  ]]>
</if>
```

**不等于**：<> ,!=,~= ,^= 这四个符号据说都可以在oracle中表示不等于，但是试了之后发现<> ,!= ,^=是可以的，~=不行,**需要注意的是，只有<>是标准的sql语法，可以移植，其他的都是oracle平台的特性，移植性较差，所以开发中，尽量使用<>表示不等于**

**等于**：=不用多说，但是要进行null的等于判断时，须使用is null 或is not null，**null只能通过is null或者is not null来判断**，其它操作符与null操作都是false。 


遍历一遍传入的列表
```
<foreach collection="tracks" item="tacode"
index="index" open="(" close=")" separator=",">
#{tacode}
</foreach>
```



## linux上安装问题
最近在ubuntu系统上安装了MySQL，但是安装时没有提示输入root用户密码，在网上找了一天解决方案，试图修改root用户下的登入密码
## centos7 安装mysql
//检查是否安装有mysql
rpm -qa | grep mysql
//下载mysql
yum install mysql
//安装mysql 
sudo rpm -ivh mysql-community-release-el7-5.noarch.rpm
//安装mysql-server
//sudo yum install mysql-server
登录
# mysql -u root
登录时有可能报这样的错：ERROR 2002 (HY000): Can’t connect to local MySQL server through socket ‘/var/lib/mysql/mysql.sock’ (2)，
原因是/var/lib/mysql的访问权限问题。下面的命令把/var/lib/mysql的拥有者改为当前用户：
# sudo chown -R openscanner:openscanner /var/lib/mysql
如果报chown: 无效的用户: "openscanner:openscanner"错误，更换命令，并用 ll 查看目录权限列表
chown root /var/lib/mysql/
ll
重启服务：
service mysqld restart
接下来登录重置密码：

 mysql -u root -p
mysql > use mysql;
mysql > update user set password=password('123456') where user='root';
mysql > exit;
重启mysql服务后才生效 # service mysqld restart

必要时加入以下命令行，为root添加远程连接的能力。链接密码为 “root”（不包括双引号）

mysql> GRANT ALL PRIVILEGES ON *.* TO root@"%" IDENTIFIED BY "root";　　

* 查询数据库编码格式，确保是 UTF-8
show variables like "%char%";
* 修改字符编码
set character_set_server=utf8;
set character_set_database=utf8;
设置成下面的样子
 Variable_name            | Value                                             |
+--------------------------+---------------------------------------------------+
| character_set_client     | utf8                                              |
| character_set_connection | utf8                                              |
| character_set_database   | utf8                                              |
| character_set_filesystem | binary                                            |
| character_set_results    | utf8                                              |
| character_set_server     | utf8                                              |
| character_set_system     | utf8      
1、安装
```
$ sudo apt-get install mysql-server
$ apt install mysql-client
$ apt install libmysqlclient-dev
```
 以此在终端输入上述代码，等待安装。装好后，输入以下代码检查下是否安装成功。

```
$ sudo netstat -tap | grep mysql
$ netstat -tap | grep mysql
```
```
tcp6        0       0       [::]:mysql    [::]:*    LISTEN    7510/mysqld
```

 如果出现第三行提示，则表明安装成功。

2、登入MySQL

```
$ mysql -u root -p
```
 输入密码，如果可以进入则下面的不用看了；如果提示不能登入，则我们尝试启用安全模式登入MySQL，这样可以绕过密码登入，登入后再修改密码。
3、安全模式登入MySQL

```
$ sudo /etc/init.d/mysql stop
```
-------------------------------------

```
[sudo] wl 的密码：
[ ok ] Stopping mysql (via systemctl): mysql.service.
```
 

```
$ sudo /usr/bin/mysqld_safe --skip-grant-tables --skip-networking &
```
 输入第一行终止MySQL运行，成功，会提示下面两行；输入第四行，成功，没有任何报错则可以另外打开一个终端窗口进行下一步操作；
 但是一般会报错，比如提示mysqld_safe Directory ‘/var/run/mysqld’ for UNIX socket file don’t exists
因此我们尝试输入以下代码

```
$ sudo mkdir -p /var/run/mysqld
$ sudo chown mysql:mysql /var/run/mysqld
```
 最后再次输入：
```
sudo /usr/bin/mysqld_safe --skip-grant-tables --skip-networking &
```
 到了这里不在提示错误，可以打开另一个终端端口了，尝试无密码登入MySQL。

```
mysql -u root
```
 到这里应该可以进入MySQL了，继续操作
 
```
> use mysql;
> update user set authentication_string=PASSWORD("这里输入你要改的密码") where User='root'; #更改密码
> update user set plugin="mysql_native_password"; #如果没这一行可能也会报一个错误，因此需要运行这一行
 
> flush privileges; #更新所有操作权限
> quit;
```
 4、使用修改的密码登入MySQL

经过上面一系列的操作，应该可以正常使用你更改的密码登入了。
```
> sudo /etc/init.d/mysql stop
> sudo /etc/init.d/mysql start # reset mysql
> mysql -u root -p
 第一行先终止数据库运行，第二行重启数据库服务，第三行root用户登入。
```

 
##  window上操作

```
net start mysql 
net stop mysql
```

//查看mysql初始密码：
sudo grep 'temporary password' /var/log/mysqld.log

//开启远程登录（前提：在使用密码登录的情况下才能修改，mysql -uroot -p 没有密码时直接回车）
Grant all privileges on *.* to 'root'@'%' identified by '@HSDZgfyxgs3588h' with grant option;
flush privileges

### 外部连接
在Centos上安装mysql
https://www.jianshu.com/p/7cccdaa2d177
//mysql 统计时间差
select b.node_desc, begin_date , end_date,TIMESTAMPDIFF(MINUTE,begin_date,end_date) as minutes from biz_node a, biz_node_def b
where  a.node_name = b.node_name and a.biz_date = '20190224' ;