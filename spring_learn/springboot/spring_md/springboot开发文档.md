###### 心得体会：


```
feign:
  hystrix:
    enabled: true//开启熔断
```



1.启动时：单元测试和application/tomcat之间的转换

类名不能和引入的jar包的类名一样，会导致导入的一样的这个类导入不进来。


theamleaf:
$和*
$属性选择器  *基于父节点的对象选择其下的属性



三种配置文件方式写个demo

工程打成jar包放在服务器上尝试改一下配置文件 怎么改

.properties  和  yml
###### springboot的三种启动方式

1.IDE 运行Application这个类的main方法

2.在springboot的应用的根目录下运行mvn spring-boot:run

3.先到项目根目录
使用mvn install 生成jar后运行
mvn install
cd target
java -jar   xxxx.jar