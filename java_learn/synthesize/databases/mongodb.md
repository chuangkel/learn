

# mongodb安装
* Configure the package management system (yum).
Create a /etc/yum.repos.d/mongodb-org-4.2.repo file so that you can install MongoDB directly using yum:
创建文件并加入下面的行/etc/yum.repos.d/mongodb-org-4.2.repo
[mongodb-org-4.2]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/4.1/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-4.2.asc

* Install the MongoDB packages.
  To install the latest stable version of MongoDB, issue the following command:
  
  sudo yum install -y mongodb-org-unstable



```
//
//启动mongodb
service mongod start

//查看启动日志
less /var/log/mongodb/mongod.log 
//连接数据库
[root@host ~]# mongo --host 127.0.0.1:27017

//新建数据库
> use souyunku
switched to db souyunku
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB
//插入数据
> db.col.insert({title: 'MongoDB 教程', 
...       description: 'MongoDB 是一个 Nosql 数据库',
...       by: '搜云库教程-专注于开发技术的研究与知识分享',
...       url: 'http://www.souyunku.com',
...       tags: ['mongodb', 'database', 'NoSQL'],
...       likes: 100
...   });
WriteResult({ "nInserted" : 1 })
> 
//查找全部列
>  db.col.find()
{ "_id" : ObjectId("5cf116bc331756832f927dca"), "title" : "MongoDB 教程", "description" : "MongoDB 是一个 Nosql 数据库", "by" : "搜云库教程-专注于开发技术的研究与知识分享", "url" : "http://www.souyunku.com", "tags" : [ "mongodb", "database", "NoSQL" ], "likes" : 100 }
> 
//还可以先命名document
> document=({title: 'MongoDB 教程', 
...     description: 'MongoDB 是一个 Nosql 数据库',
...     by: '搜云库教程-专注于开发技术的研究与知识分享',
...     url: 'http://www.souyunku.com',
...     tags: ['mongodb', 'database', 'NoSQL'],
...     likes: 100
... })
//插入document
> db.col.insert(document)
WriteResult({ "nInserted" : 1 })
> 
//插入单条数据
> var document = db.collection.insertOne({"title": "搜云库"})
> document
{
	"acknowledged" : true,
	"insertedId" : ObjectId("5cf11dd1331756832f927dcc")
}
> 
//插入多条数据
> var res = db.collection.insertMany([{"title": "搜云库"}, {'description': "关注公众号，搜云库，专注于开发技术的研究与知识分享"}])
> 
> res
{
	"acknowledged" : true,
	"insertedIds" : [
		ObjectId("5cf11e11331756832f927dcd"),
		ObjectId("5cf11e11331756832f927dce")
	]
}
> 
//查找所有数据
> db.collection.find()
{ "_id" : ObjectId("5cf11dd1331756832f927dcc"), "title" : "搜云库" }
{ "_id" : ObjectId("5cf11e11331756832f927dcd"), "title" : "搜云库" }
{ "_id" : ObjectId("5cf11e11331756832f927dce"), "description" : "关注公众号，搜云库，专注于开发技术的研究与知识分享" }
> 
//删除指定数据
> db.collection.deleteMany({ title : "搜云库"})
{ "acknowledged" : true, "deletedCount" : 2 }
> db.collection.find()
{ "_id" : ObjectId("5cf11e11331756832f927dce"), "description" : "关注公众号，搜云库，专注于开发技术的研究与知识分享" }
> 

//删除数据库
db.dropDatabase()

```
这两个的区别
db.collection.find()
db.col.find()
```
> db.collection.find()
{ "_id" : ObjectId("5cf11e11331756832f927dce"), "description" : "关注公众号，搜云库，专注于开发技术的研究与知识分享" }
> db.collection.find()
{ "_id" : ObjectId("5cf11e11331756832f927dce"), "description" : "关注公众号，搜云库，专注于开发技术的研究与知识分享" }
> db.collection.find()
{ "_id" : ObjectId("5cf11e11331756832f927dce"), "description" : "关注公众号，搜云库，专注于开发技术的研究与知识分享" }
> db.col.find()
{ "_id" : ObjectId("5cf116bc331756832f927dca"), "title" : "MongoDB 教程", "description" : "MongoDB 是一个 Nosql 数据库", "by" : "搜云库教程-专注于开发技术的研究与知识分享", "url" : "http://www.souyunku.com", "tags" : [ "mongodb", "database", "NoSQL" ], "likes" : 100 }
{ "_id" : ObjectId("5cf11d84331756832f927dcb"), "title" : "MongoDB 教程", "description" : "MongoDB 是一个 Nosql 数据库", "by" : "搜云库教程-专注于开发技术的研究与知识分享", "url" : "http://www.souyunku.com", "tags" : [ "mongodb", "database", "NoSQL" ], "likes" : 100 }
> 

//格式化输出
> db.col.find().pretty()
{
	"_id" : ObjectId("5cf116bc331756832f927dca"),
	"title" : "MongoDB 教程",
	"description" : "MongoDB 是一个 Nosql 数据库",
	"by" : "搜云库教程-专注于开发技术的研究与知识分享",
	"url" : "http://www.souyunku.com",
	"tags" : [
		"mongodb",
		"database",
		"NoSQL"
	],
	"likes" : 100
}
> 
//条件查询and查询
> db.col.find({"by":"搜云库教程-专注于开发技术的研究与知识分享", "title":"MongoDB 教程"}).pretty()
{
	"_id" : ObjectId("5cf116bc331756832f927dca"),
	"title" : "MongoDB 教程",
	"description" : "MongoDB 是一个 Nosql 数据库",
	"by" : "搜云库教程-专注于开发技术的研究与知识分享",
	"url" : "http://www.souyunku.com",
	"tags" : [
		"mongodb",
		"database",
		"NoSQL"
	],
	"likes" : 100
}
//条件or查询
> db.col.find({$or:[{"by":"搜云库教程-专注于开发技术的研究与知识分享"},{"title": "MongoDB 教程"}]}).pretty()
{
	"_id" : ObjectId("5cf116bc331756832f927dca"),
	"title" : "MongoDB 教程",
	"description" : "MongoDB 是一个 Nosql 数据库",
	"by" : "搜云库教程-专注于开发技术的研究与知识分享",
	"url" : "http://www.souyunku.com",
	"tags" : [
		"mongodb",
		"database",
		"NoSQL"
	],
	"likes" : 100
}
```

(>) 大于 - $gt
(<) 小于 - $lt
(>=) 大于等于 - $gte
(<= ) 小于等于 - $lte

