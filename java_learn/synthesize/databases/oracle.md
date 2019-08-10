
共享sql使用绑定变量

###### 1.何种架构
（是RAC还是单实例）
###### 2.oracle版本
（不同Oracle数据库版本，默认排序不同...)
###### 3.oracle优化器
RULE(基于规则)
COST(基于成本)
须经常运行analyze命令，以增加数据库中的对象统计信息的准确性。


TRUNCATE代替DELETE
索引的建立要慎重，不是越多越好。where条件中欧个的索引列应该避免使用<>,not,is null,is not null,
like "%XXX%" ,"%xxx" 以及oracle系统函数比如。
避免索引列上使用NOT
这种写法不会走索引。
decode的使用
减少对表的查询
***核心*：减少对数据库的访问。**
用EXISTS代替DISTENTD
###### 执行计划的查看办法：
1.pl/sql development工具

2.EXPLAN PLAN FOR select count (*) from dba_users;

3.set autotrace traceonly;

4.sql moniter（推荐)

5.select * from table(dbms_xplan.display_awr(""));//准确

索引提高效率。
基数：列唯一键的数量。
选择性:列唯一键与行数的比值
适合创建索引的一般参考标准：选择性>20%
表，表索引需要存在于独立的表空间。
案例：删除索引 导入数据 建立索引
避免索引列上使用计算。
临时表的使用
全局临时表
1.commit对大事务的dml操作，需及时提交
2.批量提交
锁机制
nested loops（关注驱动表的选择）
小表（驱动表，外部表） 大表 （建立索引）
sert merge(排序合并连接）
pga内存，如果pga不够 会使用的temp
hash join (小表匹配大表，小表放在内存）



查看oracle实例

```
SQL> select instance_name , status from v$instance;
```


连接sql的dba用户

```
[oracle@demo ~]$ sqlplus / as sysdba
```

1.创建表空间

```
create tablespace hs_user datafile '/u02/oradata/orcl/hs_user01.dbf' size 10m autoextend off;
```

2.更新表空间

```
alert tablespace hs_user add datafile '/u02/oradata/orcl/hs_user02.dbf' size 10m;
```

3.给表空间创建用户

```
create user hs_user identified by hundsun default tablespace hs_user;
```

4.从oracle用户登陆hs_user

```
sqlplus hs_user / hundsun
```

5.在sql中查看当前的用户

```
show user
```

6.在oracle用户下无法登陆hs_user 要连接数据库，用sql语句赋权

```
grant connect ,resource to hs_user;
grant dba to hs_user;//dba为管理员
```

7.在SQL语句中登陆用户

```
conn hs_user /hundsun;
```

8.show parameter dump;SQL语句
9.oracle 用户下打开数据库 

```
startup
```

备份...
###### 同学们还记得这些知识吗？ 

vi命令： i表示insert光标位置前面，a表示后面，保存是wq
数据库表空间主要是数据文件

awr报告：
```
@?/rdbms/admin/awrrpt
```
ash报告:

```
@?/rdbms/admin/ashrpt
```
操作系统版本号查看： 

```
cat /etc/redhat-release
```
对新加的硬盘分区： 

```
fdisk /dev/sdb
```
数据库的起停：
```
startup, shutdown immediate
```
监听器启动、停止：
    
```
lsnrctl start, lsnrctl stop
```
//启动监听需获得权限，用以下命令

```
su - oracle
```


如何查看实例：

```
select instance_name, status from v$instance;
```
oracle的优化器： rule模式跟all_rows

最后在了解一下创建表空间，建表的语句
备份expdp的命令也是

```
create table table1(a varchar(3),b number(3));
```

varchar(n),必须有制定长度

```
insert into table3 values('a','b');
```

支持单引号

查看数据表和表空间用户
```
select * from tab;
select * from all_users;
```


```
expdp system/system密码@SID schemas=xxx DUMPFILE=xxx.dmp DIRECTORY=EXPDIR logfile=expdp.log JOB_NAME=full
```

查看当前连接的所有表：
```oracle
-- 查看当前连接的用户名
select user from dual; 
-- 查找指定表的字段名
select lower(column_name) from user_tab_columns where table_name = upper(#{tableName})
-- 查找当前连接的所有表名
select lower(object_name) from user_objects;
-- 查找库的所有表名
select distinct lower(object_name) from all_objects;
-- 注意客户端的修改插入操作 需要commit(); 或者导致表被锁定程序无法操作。
-- 注意：应用程序和oracle库在同一台机器上需要使用hostname -i （即本机ip） 来进行访问 ，在不同机器上使用对外的ip地址进行访问（局域网ip）
```
