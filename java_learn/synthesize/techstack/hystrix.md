
```
@HystrixCommand(fallbackMethod="fallback", commandProperties ={       
@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1000" )})

@HystrixCommand(fallbackMethod="fallback",commandKey="userGetKey")
配置文件给commandKey配置超时时间：
hystrix.command.userGetKey.execution.isolation.thread.timeoutInMilliseconds = 13000
```