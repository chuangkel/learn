### spring cloud 核心组件
1.Euraka 注册中心,服务发现， Euraka Server是一个集群， Euraka Client 在每个服务中，从Euraka Server拉取服务注册表，从而知道服务在哪里。
2.Hystrix 熔断限流，防止雪崩效应，通过线程池的方式，一个服务一个线程池，实现服务调用的隔离。可以实现服务降级，即服务挂了之后降级处理。
3.ribbion 负载均衡，请求服务时，从该服务的多台机器中选择一台。
4.feign 动态代理，包装繁琐的网络请求，和Eurake配合使用，根据注解和选择的机器拼接地址，发起请求。
5.Zuul 网关，请求服务的统一入口。

TPS：Transactions Per Second 每秒的处理事务的数量
QPS:每秒查询的数量
多线程模型：
并行工作者模型
流水线模型