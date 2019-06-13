## 驼峰下划线转换

## 返回json数据封装
* 首先自定义实现ResponseBodyWrapHandler返回体包装处理器
```java
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;
    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        RtnMsg rtnMsg = null;
        if(returnValue instanceof RtnMsg){
            rtnMsg = (RtnMsg)returnValue;
        }else{
            rtnMsg = new RtnMsg(RtnCode.STATUS_OK,"",returnValue);
        }
        delegate.handleReturnValue(rtnMsg, returnType, mavContainer, webRequest);
    }
}
```
* 实现一个FactoryBean,在bean初始化之后替换原生返回体包装处理器
```java
public class ResponseBodyWrapFactoryBean implements InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter adapter;
    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers){
        for(HandlerMethodReturnValueHandler handler : handlers){
            if(handler instanceof RequestResponseBodyMethodProcessor){
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                int index = handlers.indexOf(handler);
                //替换原有返回体包装处理器
                handlers.set(index, decorator);
                break;
            }
        }
    }
}
```
* bean注入
```java
@Configuration
public class RtnMsgConfig {
    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap(){
        return new ResponseBodyWrapFactoryBean();
    }
}
```

##统一异常捕获
```java
@ControllerAdvice
public class CommExceptionHandler  {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RtnMsg handle(Exception e){
        RtnMsg msg = new RtnMsg(RtnCode.STATUS_SYSERROR,"系统异常",e.getMessage());
        return msg;
    }
}
```