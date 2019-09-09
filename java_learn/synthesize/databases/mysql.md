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
### mysql语法基础
* union 和 union all 都是将两个结果集合并为一个
union 会去掉重复的行
union all 不会去掉重复行，直接返回合并结果，效率较union高
* alter table table_name add index(id,name...字段名) //增加索引
* alter table 表名 rename 新表名;//修改表名
### msyql语句优化
查看执行计划 Explain 
* join语句优化：尽量采用小表驱动大表，减少NestedLoop循环总数
* where子句优化：
1 .索引列是字符串时，索引列的值需用引号引起来，否则不走索引
2 .尽量避免使用!= < > ,会放弃索引走全表


### 经典mysql题
试试选出每门课的前二名学生，按照成绩grade倒序排列，  用一个sql语句
```mysql
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `grade` decimal(4,2) DEFAULT NULL,
  `course` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('1', 'tom', '90.00', '语文');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('2', 'tom', '80.00', '数学');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('3', 'tom', '85.00', '英语');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('4', 'john', '99.00', '语文');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('5', 'john', '77.00', '数学');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('6', 'john', '88.00', '英语');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('7', 'jack', '99.00', '语文');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('8', 'jack', '89.00', '数学');
INSERT INTO `dindin`.`test` (`id`, `name`, `grade`, `course`) VALUES ('9', 'jack', '79.00', '英语');


SELECT
	*
FROM
	(
		SELECT
			a.*
		FROM
			test a
		WHERE
			grade >= (
				SELECT
					grade
				FROM
					test b
				WHERE
					b.course = a.course
				ORDER BY
					grade DESC
				LIMIT 1,
				1
			)
	) c
ORDER BY
	course,
	grade DESC;
	
	
	select a.id,a.name,a.grade,a.course from test a LEFT JOIN test b ON a.course = b.course AND a.grade <= b.grade 
    
    GROUP BY a.id,a.name,a.grade,a.course HAVING COUNT(b.id) <= 2 ;
    
    SELECT * from (
    
    SELECT * from test a where (SELECT count(DISTINCT grade) from test b where a.course=b.course and b.grade>=a.grade)<=2
    
    ) c ORDER BY course,grade desc;
```

left join(左连接) ： 左表所有数据循环，连接右表，在字段相等的记录。
right join(右连接)：右表所有数据循环，连接左表，在字段相等的记录。
inner join(内连接)：取左右表的交集。

MySQL 的相关优化
1. MySQL 性能优化：组成、表的设计
开启查询缓存。避免某些 SQL 函数直接在 SQL 语句中使用，从而导致 Mysql 缓存失效。

避免画蛇添足。目的是什么就取什么，例如某个逻辑是只需要判断是否存在女性，若是查到了一条即可，勿要全部都查一遍，此时要善用 limit。

建合适的索引。所以要建在合适的地方，合适的对象上。经常操作 / 比较 / 判断的字段应该建索引。

字段大小合宜。字段的取值是有限而且是固定的，这种情况下可以用 enum，IP 字段可以用 unsigned int 来存储。

表的设计。垂直分割表，使得固定表与变长表分割，从而降低表的复杂度和字段的数目。

2. SQL 语句优化：避免全表扫描
建索引：一般在 where 及 order by 中涉及到的列上建索引，尽量不要对可以重复的字段建索引。

尽量避免在 where 中使用 !（<>）或 or，也不要进行 null 值判断。

尽量避免在 where 中对字段进行函数操作、表达式操作。

尽量避免使用 like- %，在此种情况下可以进行全文检索。

```mysql
-- 查询制定db的表名
select TABLE_NAME from information_schema.`TABLES` where TABLE_SCHEMA = #{dbName};
-- 查询制定表的列名
select column_name from information_schema.columns 
where table_schema = #{dbName} and table_name = #{tableName};
-- 删除表中...
DELETE FROM tab1 WHERE col1 = 'value'
```

```mysql

DELIMITER $$ 
DROP TRIGGER IF EXISTS `updateegopriceondelete`$$ 
CREATE 
    TRIGGER `updateegopriceondelete` AFTER  DELETE ON  `customerinfo` 
    FOR EACH ROW BEGIN 
DELETE FROM egoprice  WHERE customerId=OLD.customerId; 
    END$$ 
DELIMITER ; 
```
DELIMITER 定好结束符为"$$", 然后最后又定义为";", MYSQL的默认结束符为";"
```mysql
DELIMITER $$
DROP PROCEDURE IF EXISTS insertItemProcedure $$
CREATE PROCEDURE insertItemProcedure(username VARCHAR(20), record VARCHAR(20),number INT)   /*参数默认为 in ,也可以指定out*/
BEGIN
	DECLARE s VARCHAR(255) DEFAULT 'default';
	SET s = CONCAT('insert into item values(null,\'',username,'\',\'',record,'\',',number,');');
	SET @s  = s;
	PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    END $$
DELIMITER ;

```
> 创建存储过程
  create procedure sp_name()
  begin
  .........
  end
  
> 调用存储过程
  1.基本语法：call sp_name()
  注意：存储过程名称后面必须加括号，哪怕该存储过程没有参数传递
  
> 删除存储过程
  1.基本语法：
  drop procedure sp_name//
  
```mysql
show procedure status; -- 显示数据库中所有存储的存储过程基本信息，包括所属数据库，存储过程名称，创建时间等
show create procedure executUpdateSql; -- 显示某一个mysql存储过程的详细信息 
set global table_definition_cache = 4000; -- 报该错的解决办法 [Err] 1615 - Prepared statement needs to be re-prepared

```
在向表中插入数据的时候，经常遇到这样的情况：1. 首先判断数据是否存在； 2. 如果不存在，则插入；3.如果存在，则更新。
replace into 跟 insert 功能类似，不同点在于：replace into 首先尝试插入数据到表中，
 如果发现表中已经有此行数据（根据主键或者唯一索引判断）则先删除此行数据，然后插入新的数据。 2. 否则没有此行数据的话，直接插入新数据。
1）插入数据的表必须有主键或者是唯一索引！否则的话，replace into 会直接插入数据，这将导致表中出现重复的数据。
2）如果数据库里边有这条记录，则直接修改这条记录；如果没有则，则直接插入，在有外键的情况下，对主表进行这样操作时，因为如果主表存在一条记录，被从表所用时，直接使用replace into是会报错的，这和replace into的内部原理是相关（ps.它会先删除然后再插入）。
 
 * explain只能解释select语句
 
 ```mysql
select * from class c join student s
on c.id = s.class_id
where s.name = 'Sawyer';
```
mysql首先在s表也就是student表中查询name字段为Sawyer的值，由于name字段上并没有索引，所以使用了全表扫描，该表一共有4条记录，所以扫描了4行，rows为4。
然后c表也就是class表使用主键和之前的结果通过s.class_id关联，由于是关联查询，并且是通过唯一键进行查询，所以使用了eq_ref的类型。
EXPLAIN select * from dd_activity where id = 1;
EXPLAIN select * from dd_activity where user_id = 1;
alter TABLE dd_activity add INDEX user_id_index

decimal(15,8)
7 到 12 的范围（包括7和12）内得到一个随机整数, 可使用以下语句：
SELECT FLOOR(7 + (RAND() * 6));
round（x）是四舍五入 ；
floor（x）是去小于等于x的整数；
ceiling（x）是取大于等于x的整数； 

> SELECT * FROM A LEFT JOIN B ON A.ID=B.ID WHERE B.OTHERKEY=XXXX
与SELECT * FROM A LEFT JOIN B ON A.ID=B.ID AND B.OTHERKEY=XXXX
是不一样的,后者相当于（出来的结果一样）
SELECT * FROM A LEFT JOIN B ON A.ID=B.ID WHERE B.OTHERKEY=XXXX OR B.OTHERKEY IS NULL
进行左连接时，就有涉及到主表、辅表，这时主表条件写在WHERE之后，辅表条件写在ON后面