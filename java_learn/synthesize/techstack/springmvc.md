### @RequestParam和@RequestBody的区别
#### @RequestParam 

* 常用来处理简单类型的绑定，通过Request.getParameter() 获取的String可直接转换为简单类型的情况（ 由String到 简单类型的转换操作由ConversionService配置的转换器来完成）；因为使用request.getParameter()方式获取参数，所以可以处理get 方式中queryString的值，也可以处理post方式中 body data的值。

* 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST。(不设置这个属性，好像这就是默认值)

* 该注解有两个属性： value、required。 value用来指定要传入值的id名称，required用来指示参数是否必须绑定。

```
public @ResponseBody JsonResult getPublishedToposByConnStreamId(
@RequestParam(value = "streamId", required = false) String streamId) {}

```

@RequestBody

* GET、POST方式提时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理）
    multipart/form-data, 不能处理（次类型多用来上传文件类型---即使用@RequestBody不能处理这种格式的数据,@RequestParam这个却是可以处理的。）
    其他格式， 必须（其他格式包括application/json, application/xml等。这些格式的数据，必须使用@RequestBody来处理）；

说明：request的body部分的数据编码格式由header部分的Content-Type指定
### 总结

@RequestBody这个一般处理的是在ajax请求中声明contentType: "application/json; charset=utf-8"时候。也就是json数据或者xml(我没用过这个，用的是json)

@RequestParam这个一般就是在ajax里面没有声明contentType的时候，为默认的urlencode格式时

### 过滤器(Filter)和拦截器(Interceptor)的区别
Filter介绍
      Filter可以认为是Servlet的一种“加强版”，它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理，是个典型的处理链。Filter也可以对用户请求生成响应，这一点与Servlet相同，但实际上很少会使用Filter向用户请求生成响应。使用Filter完整的流程是：Filter对用户请求进行预处理，接着将请求交给Servlet进行预处理并生成响应，最后Filter再对服务器响应进行后处理。

      Filter有如下几个用处。

在HttpServletRequest到达Servlet之前，拦截客户的HttpServletRequest。
根据需要检查HttpServletRequest，也可以修改HttpServletRequest头和数据。
在HttpServletResponse到达客户端之前，拦截HttpServletResponse。
根据需要检查HttpServletResponse，也可以修改HttpServletResponse头和数据。
     Filter有如下几个种类。

用户授权的Filter：Filter负责检查用户请求，根据请求过滤用户非法请求。
日志Filter：详细记录某些特殊的用户请求。
负责解码的Filter:包括对非标准编码的请求解码。
能改变XML内容的XSLT Filter等。
Filter可以负责拦截多个请求或响应；一个请求或响应也可以被多个Filter拦截。
     创建一个Filter只需两个步骤

创建Filter处理类
web.xml文件中配置Filter
   创建Filter必须实现javax.servlet.Filter接口，在该接口中定义了如下三个方法。

void init(FilterConfig config):用于完成Filter的初始化。
void destory():用于Filter销毁前，完成某些资源的回收。
void doFilter(ServletRequest request,ServletResponse response,FilterChain chain):实现过滤功能，该方法就是对每个请求及响应增加的额外处理。该方法可以实现对用户请求进行预处理(ServletRequest request)，也可实现对服务器响应进行后处理(ServletResponse response)—它们的分界线为是否调用了chain.doFilter(),执行该方法之前，即对用户请求进行预处理；执行该方法之后，即对服务器响应进行后处理。
 

Interceptor介绍
     拦截器，在AOP(Aspect-Oriented Programming)中用于在某个方法或字段被访问之前，进行拦截，然后在之前或之后加入某些操作。拦截是AOP的一种实现策略。

     在WebWork的中文文档的解释为—拦截器是动态拦截Action调用的对象。它提供了一种机制可以使开发者可以定义在一个Action执行的前后执行的代码，也可以在一个Action执行前阻止其执行。同时也提供了一种可以提取Action中可重用的部分的方式。

     拦截器将Action共用的行为独立出来，在Action执行前后执行。这也就是我们所说的AOP，它是分散关注的编程方法，它将通用需求功能从不相关类之中分离出来；同时，能够共享一个行为，一旦行为发生变化，不必修改很多类，只要修改这个行为就可以。

     拦截器将很多功能从我们的Action中独立出来，大量减少了我们Action的代码，独立出来的行为就有很好的重用性。

     当你提交对Action(默认是.action结尾的url)的请求时，ServletDispatcher会根据你的请求，去调度并执行相应的Action。在Action执行之前，调用被Interceptor截取，Interceptor在Action执行前后执行。

     创建Interceptor必须实现com.opensymphony.xwork2.interceptor.Interceptor接口，该接口定义了如下三个方法。

void init():在该拦截器被实例化之后，在该拦截器执行拦截之前，系统将回调该方法。对于每个拦截器而言，其init()方法只执行一次。因此，该方法的方法体主要用于初始化资源。
void destory():该方法与init()方法对应。在拦截器实例被销毁之前，系统将回调该拦截器的destory方法，该方法用于销毁在init方法里打开的资源。
String intercept(ActionInvocation invocation):该方法是用户需要实现的拦截动作。就像Action的execute方法一样。intercept方法会返回一个字符串作为逻辑视图。如果该方法直接返回了一个字符串，系统会将跳转到该逻辑视图对应的实际视图资源，不会调用被拦截的Action。该方法的ActionInvocation参数包含了被拦截的Action的引用，可以通过调用该参数的invoke方法，将控制权转给下一个拦截器，或者转给Action的execute方法(如果该拦截器后没有其他拦截器，则直接执行Action的execute方法)
Filter和Interceptor的区别
Filter是基于函数回调的，而Interceptor则是基于Java反射的。
Filter依赖于Servlet容器，而Interceptor不依赖于Servlet容器。
Filter对几乎所有的请求起作用，而Interceptor只能对action请求起作用。
Interceptor可以访问Action的上下文，值栈里的对象，而Filter不能。
在action的生命周期里，Interceptor可以被多次调用，而Filter只能在容器初始化时调用一次。
Filter和Interceptor的执行顺序
     过滤前-拦截前-action执行-拦截后-过滤后
     
     
     
     
     
     执行过程大致如下：
     第一步：用户发起request请求，请求至DispatcherServlet前端控制器。
     第二步：DispatcherServlet前端控制器请求HandlerMapping处理器映射器查找Handler。DispatcherServlet: 前端控制器，相当于中央调度器，各个组件都和前端控制器进行交互，降低了各个组件之间的耦合度。
     第三步：HandlerMapping处理器映射器，根据url及一些配置规则(xml配置、注解配置)查找Handler，将Handler返回给DispatcherServlet前端控制器。
     第四步：DispatcherServlet前端控制器调用适配器执行Handler，有了适配器通过适配器去扩展对不同Handler执行方式(比如：原始servlet开发，注解开发)
     第五步：适配器执行Handler；Handler是后端控制器，可以当成模型。
     第六步：Handler执行完后后返回ModelAndView。ModelAndView: SpringMVC的一个对象，对model和view进行封装。
     第七步：适配器将ModelAndView返回给DispatcherServlet前端控制器。
     第八步：DispatcherServlet调用视图解析器进行视图解析，解析后生成view，视图解析器根据逻辑视图名解析出真正的视图。view：SpringMVC视图封装对象，提供了很多view，比如jsp、freemarker、pdf、execel.......。
     第九步：ViewResolver视图解析器给前端控制器返回view。
     第十步：DispatcherServlet调用view的渲染视图的方法，将模型数据填充到request域。
     第十一步：DispatcherServlet向用户响应结果(jsp页面、json数据......).
     
     DispatcherServlet：前端控制器，由SpringMVC提供。
     HandlerMapping：处理器映射器，由SpringMVC提供。
     HandlerAdapter：处理器适配器，由SpringMVC提供。
     Handler：处理器，需要程序员开发。
     ViewResolver：视图解析器，由SpringMVC提供。
     view：真正的视图页面，由程序员编写。
     
## 静态文件上传和下载
application.properties文件中配置
spring.resources.static-locations= classpath:static/

* 默认静态资源映射规则
Spring Boot 默认将 / 所有访问映射到以下目录：**
classpath:/static
classpath:/public
classpath:/resources
classpath:/META-INF/resources

接下来，在main/resources下新建static、public和resources三个文件夹，分别放入public/a.png、resources/b.png和static/c.png三张图片，如下： 
启动项目，分别访问：
http://localhost:8083/a.png
http://localhost:8083/b.png
http://localhost:8083/c.png
发现都能正常访问相应的图片资源。那么说明，Spring Boot 默认会挨个从 public、resources和static 里面找是否存在相应的资源，如果有则直接返回。


### 自定义图片存放自动 和图片访问路径
```java

@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File upload = null;
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File(ResourceUtils.getURL("classpath:").getPath());
            }
            upload = new File(path.getAbsolutePath(),"static/upload/");
            if(!upload.exists()) {
                upload.mkdirs();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //指定图片访问路径 /image/1.png 将会在 "file:"+upload.getAbsolutePath()+"/" 下取
        registry.addResourceHandler("/image/**").addResourceLocations( "file:"+upload.getAbsolutePath()+"/");
        super.addResourceHandlers(registry);
    }
}
```