### spring cloud 核心组件
1.Euraka 注册中心,服务发现， Euraka Server是一个集群， Euraka Client 在每个服务中，从Euraka Server拉取服务注册表，从而知道服务在哪里。
2.Hystrix 熔断限流，防止雪崩效应，通过线程池的方式，一个服务一个线程池，实现服务调用的隔离。可以实现服务降级，即服务挂了之后降级处理。
3.ribbion 负载均衡，请求服务时，从该服务的多台机器中选择一台。
4.feign 动态代理，包装繁琐的网络请求，和Eurake配合使用，根据注解和选择的机器拼接地址，发起请求。
5.Zuul 网关，请求服务的统一入口。
6.zipkin 分布式链路追踪.

TPS：Transactions Per Second 每秒的处理事务的数量
QPS:每秒查询的数量
多线程模型：
并行工作者模型
流水线模型

调用方
```java
@FeignClient(value = "eqianbao-server", fallback = SignServiceApiFallback.class)
public interface SignServiceApi {
	@RequestLine("POST")
	public UnifiedSignApiVoRes unifiedSignPDF(URI requestUrl, UnifiedSignApiVoReq request);
```

spring-cloud-netflix
服务发现
Service Discovery:Eureka Clients
```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
Service Discovery:Eureka Server
服务熔断 
Circuit Breaker:Hystrix Dashboard
Circuit Breaker:Hystrix Clients
负载均衡
Client Side Load Balancer:Ribbon
路由器和过滤器
Router and Filter:Zuul
网关
Route: Route the basic building block of the gateway. It is defined by an ID, a destination URI, a collection of predicates and a collection of filters. A route is matched if aggregate predicate is true.

Predicate: This is a Java 8 Function Predicate. The input type is a Spring Framework ServerWebExchange. This allows developers to match on anything from the HTTP request, such as headers or parameters.

Filter: These are instances Spring Framework GatewayFilter constructed in with a specific factory. Here, requests and responses can be modified before or after sending the downstream request.
专有词汇：
Route:路由
Predicate
Filter
