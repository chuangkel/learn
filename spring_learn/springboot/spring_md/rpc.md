Agile Reuse Extend Simple
单体架构的缺点：
无法快速迭代 无法有效协作
无法应对海量数据 可靠性差 
扩展能力差 阻碍技术创新

平台升级的业务价值：

RemoteProcedureCall是服务化的关键(RPC)

RPC没那么简单：
1.远程通讯
2.序列化、反序列化
3.链路监控
4.服务注册中心 
5.负载均衡及策略
6.缓存及更新 
7.异步调用 
8.版本控制
9.线程池
10.优雅关机
...
SpringBoot是一个基于Spring的框架，可以使你很容易创建一个产品级的、
独立运行的Spring应用。SpringBoot伴随Spring4诞生，
现在已经是Java微服务开发领域使用最广泛的开源框架了。

SpringBoot 提供了一个内嵌的tomcat \ netty ,

docker
SLB
技术组件：
消息队列 RabbitMq 、定时任务 Quartz、分布式事务 TCCTransaction
数据访问代理 MyCat 、数据同步 斗转星移、hscache分布式缓存、数据库访问 MyBatis
Rpc框架：
服务定义 服务调用 负载均衡 Dubbo 、EndPoint 监控运维扩展、Log4j 链路日志扩展
基础设施：
注册中心 配置中心 ZoonKeeper、链路跟踪 GoogleDapper
日志搜索 ElasticSearch 、网关 Iar 、服务治理MGR、断路器 Hystrix