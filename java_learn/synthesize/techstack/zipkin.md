# zipkin

分布式链路跟踪系统，可以将整个请求的耗时存入数据库以供分析，是twitter开源的
spanId(节点id)和 traceId（链路Id）
一个traceId下面有多个spanId
zipkin 有支持多种存储数据库 比如：inMemory 直接存在内存里面，，，，
有专门的界面，直接官网下载jar包运行即可访问UI