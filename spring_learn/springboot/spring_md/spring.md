# spring boot

zdal 水平拆分 ：十库百表，eg.根据fund_share_XX表中的trade_account字段来路由到某库某表。
分布式事务 :将多个服务器的操作放在一起，原子性执行。
使用java新特性编程 
## java事务分为三类：
jdbc (java database connection) 事务： 通过Connection对象进行 setAutoSubmit submit rollback 等
一个jdbc事物不能跨越多个数据库

jta (java transaction api) 事务
容器事务 如：spring 事务：是基于jta完成的

## application.properties详解
该文件中的配置不能穷尽，你可以加入自己的配置
@ConfigurationProperties and @Value 定义了该配置文件



## 注解       
两种注入方式: @Autowired 和 构造器 等价。
@SpringBootApplication 相当于 下面三个注解：
源码如下：
```
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(//...)
public @interface SpringBootApplication {}
```

### @AliasFor
#### Explicit aliases within an annotation:注解中属性互为别名
```java
 public @interface ContextConfiguration {
    @AliasFor("locations")
    String[] value() default {};

    @AliasFor("value")
    String[] locations() default {};
    // ...
 }
```
#### Explicit alias for attribute in meta-annotation: 元注解中属性的别名
MyTestConfig 相当于继承了 ContextConfiguration，对ContextConfiguration中的locations起别名
```java
@ContextConfiguration
 public @interface MyTestConfig {

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] value() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] groovyScripts() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xmlFiles() default {};
 }
```
#### Implicit aliases within an annotation: 注解中隐式别名
```java
 @MyTestConfig
 public @interface GroovyOrXmlTestConfig {

    @AliasFor(annotation = MyTestConfig.class, attribute = "groovyScripts")
    String[] groovy() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xml() default {};
 }
 //最后xml 和 groovy 都是ContextConfiguration中的location的别名
```


## 数据库连接池
```
private static final String[] DATA_SOURCE_TYPE_NAMES = new String[] {
			"org.apache.tomcat.jdbc.pool.DataSource",
			"com.zaxxer.hikari.HikariDataSource",
			"org.apache.commons.dbcp.BasicDataSource", // deprecated
			"org.apache.commons.dbcp2.BasicDataSource" };
```

###### Bean Naming Conventions(约定）
Examples of such names would be (without quotes) 'accountManager', 'accountService', 'userDao', 'loginController', and so forth.

```
<alias name="subsystemA-dataSource" alias="subsystemB-dataSource"/>
<alias name="subsystemA-dataSource" alias="myApp-dataSource" />
```
###### Instantiation with a static factory method

```
<bean id="clientService"
    class="examples.ClientService"
    factory-method="createInstance"/>
```
```
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}
```



###### Instantiation using an instance factory method

```
<!-- the factory bean, which contains a method called createInstance() -->
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
    <!-- inject any dependencies required by this locator bean -->
</bean>

<!-- the bean to be created via the factory bean -->
<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>
```

```
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
```


```
<bean id="theTargetBean" class="..."/>

<bean id="theClientBean" class="...">
    <property name="targetName">
        <idref bean="theTargetBean"/>
    </property>
</bean>
```



The above bean definition snippet is exactly equivalent (at runtime) to the following snippet:


```
<bean id="theTargetBean" class="..." />

<bean id="client" class="...">
    <property name="targetName" value="theTargetBean"/>
</bean>
```

The first form is preferable to the second, because using the idref tag allows the container to validate at deployment time that the referenced, named bean actually exists. 

###### References to other beans (collaborators)



```
<!-- in the parent context -->
<bean id="accountService" class="com.foo.SimpleAccountService">
    <!-- insert dependencies as required as here -->
</bean>
<!-- in the child (descendant) context -->
<bean id="accountService" <!-- bean name is the same as the parent bean -->
    class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="target">
        <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
    </property>
    <!-- insert other configuration and dependencies as required here -->
</bean>
```

###### Inner beans

A <bean/> element inside the <property/> or <constructor-arg/> elements defines a so-called inner bean.


```
<bean id="outer" class="...">
    <!-- instead of using a reference to a target bean, simply define the target bean inline -->
    <property name="target">
        <bean class="com.example.Person"> <!-- this is the inner bean -->
            <property name="name" value="Fiona Apple"/>
            <property name="age" value="25"/>
        </bean>
    </property>
</bean>
```

###### Collections

In the <list/>, <set/>, <map/>, and <props/> elements, you set the properties and arguments of the Java Collection types List, Set, Map, and Properties, respectively.


```
<bean id="moreComplexObject" class="example.ComplexObject">
    <!-- results in a setAdminEmails(java.util.Properties) call -->
    <property name="adminEmails">
        <props>
            <prop key="administrator">[email protected]</prop>
            <prop key="support">[email protected]</prop>
            <prop key="development">[email protected]</prop>
        </props>
    </property>
    <!-- results in a setSomeList(java.util.List) call -->
    <property name="someList">
        <list>
            <value>a list element followed by a reference</value>
            <ref bean="myDataSource" />
        </list>
    </property>
    <!-- results in a setSomeMap(java.util.Map) call -->
    <property name="someMap">
        <map>
            <entry key="an entry" value="just some string"/>
            <entry key ="a ref" value-ref="myDataSource"/>
        </map>
    </property>
    <!-- results in a setSomeSet(java.util.Set) call -->
    <property name="someSet">
        <set>
            <value>just some string</value>
            <ref bean="myDataSource" />
        </set>
    </property>
</bean>
```

spring:使用<prop>标签为Java持久属性集注入值
使用 spring 提供的<prop>为Java持久属性集注入值，也就是向 java.util.Properties 对象中注入值。

<props> 是简化了的 <map> ,该元素对应配置类型为 java.util.Properties 的对象依赖。

因为Properties 只能指定 String 类型的键和值，所以 <props> 的配置简化很多，只有固定的格式。

###### Collection merging

```
<beans>
    <bean id="parent" abstract="true" class="example.ComplexObject">
        <property name="adminEmails">
            <props>
                <prop key="administrator">[email protected]</prop>
                <prop key="support">[email protected]</prop>
            </props>
        </property>
    </bean>
    <bean id="child" parent="parent">
        <property name="adminEmails">
            <!-- the merge is specified on the child collection definition -->
            <props merge="true">
                <prop key="sales">[email protected]</prop>
                <prop key="support">[email protected]</prop>
            </props>
        </property>
    </bean>
<beans>
```

Notice the use of the merge=true attribute on the <props/> element of the adminEmails property of the child bean definition. When the child bean is resolved and instantiated by the container, the resulting instance has an adminEmails Propertiescollection that contains the result of the merging of the child’s adminEmails collection with the parent’s adminEmails collection.


```
[email protected]
[email protected]
[email protected]
```

the parent’s values precede all of the child list’s values. 

###### Strongly-typed collection



```
public class Foo {

    private Map<String, Float> accounts;

    public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }
}
```


```
<beans>
    <bean id="foo" class="x.y.Foo">
        <property name="accounts">
            <map>
                <entry key="one" value="9.99"/>
                <entry key="two" value="2.75"/>
                <entry key="six" value="3.99"/>
            </map>
        </property>
    </bean>
</beans>
```
###### XML shortcut with the p-namespace（带有P命名空间的XML快捷方式）


```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="classic" class="com.example.ExampleBean">
        <property name="email" value="[email protected]"/>
    </bean>

    <bean name="p-namespace" class="com.example.ExampleBean"
        p:email="[email protected]"/>
</beans>
```

XML shortcut with the p-namespace


```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="john-classic" class="com.example.Person">
        <property name="name" value="John Doe"/>
        <property name="spouse" ref="jane"/>
    </bean>

    <bean name="john-modern"
        class="com.example.Person"
        p:name="John Doe"
        p:spouse-ref="jane"/>

    <bean name="jane" class="com.example.Person">
        <property name="name" value="Jane Doe"/>
    </bean>
</beans>
```
the second bean definition uses p:spouse-ref="jane" as an attribute to do the exact same thing. In this case spouse is the property name, whereas the -ref part indicates that this is not a straight value but rather a reference to another bean.

###### $ XML shortcut with the c-namespace(带有C命名空间的XML快捷方式）

Similar to the XML shortcut with the p-namespace


```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="bar" class="x.y.Bar"/>
    <bean id="baz" class="x.y.Baz"/>

    <!-- traditional declaration -->
    <bean id="foo" class="x.y.Foo">
        <constructor-arg ref="bar"/>
        <constructor-arg ref="baz"/>
        <constructor-arg value="[email protected]"/>
    </bean>

    <!-- c-namespace declaration -->
    <bean id="foo" class="x.y.Foo" c:bar-ref="bar" c:baz-ref="baz" c:email="[email protected]"/>

</beans>
```
###### Using depends-on
a static initializer in a class needs to be triggered, such as database driver registration. The depends-on attribute can explicitly force one or more beans to be initialized before the bean using this element is initialized.

```
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>
<bean id="manager" class="ManagerBean" />
```

```
<bean id="beanOne" class="ExampleBean" depends-on="manager,accountDao">
    <property name="manager" ref="manager" />
</bean>

<bean id="manager" class="ManagerBean" />
<bean id="accountDao" class="x.y.jdbc.JdbcAccountDao" />
```

Dependent beans that define a depends-on relationship with a given bean are destroyed first, prior to the given bean itself being destroyed. Thus depends-on can also control shutdown order.
###### Lazy-initialized beans
A lazy-initialized bean tells the IoC container to create a bean instance when it is first requested, rather than at startup.
```
<bean id="lazy" class="com.foo.ExpensiveToCreateBean" lazy-init="true"/>
<bean name="not.lazy" class="com.foo.AnotherBean"/>
```
When the preceding configuration is consumed by an ApplicationContext, the bean named lazy is not eagerly pre-instantiated when the ApplicationContext is starting up, whereas the not.lazy bean is eagerly pre-instantiated.

However, when a lazy-initialized bean is a dependency of a singleton bean that is not lazy-initialized, the ApplicationContext creates the lazy-initialized bean at startup, because it must satisfy the singleton’s dependencies. The lazy-initialized bean is injected into a singleton bean elsewhere that is not lazy-initialized.
You can also control lazy-initialization at the container level by using the default-lazy-init attribute on the <beans/> element; for example:


```
<beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
</beans>
```

###### spring的懒加载和depends-on
①延迟初始化Bean(惰性初始化Bean)是指不提前初始化Bean，而是只有在真正使用时才创建及初始化Bean.  配置方式很简单只需在<bean>标签上指定 “lazy-init” 属性值为“true”即可延迟初始化Bean。
配置文件：


```
<!--HelloWorld.xml-->
<bean id="helloApi"  
class="cn.javass.spring.chapter2.helloworld.HelloImpl"  
lazy-init="true"/>
```

 


②depends-on是指指定Bean初始化及销毁时的顺序，使用depends-on属性指定的Bean要先初始化完毕后才初始化当前Bean,由于只有"singleton"Bean才能被Spring管理销毁，所以当指定的Bean都是"singleton"时，使用depends-on属性指定的Bean要在指定的Bean之后销毁。
配置代码：

```
<bean id="helloApi" class="com.feng.spring.chapter2.helloworld.HelloApi">
</bean>
<bean id="decorator"  
    class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
    depends-on="helloApi">  
    <property name="helloApi"><ref bean="helloApi"/></property>  
</bean>
```
###### bean作用域配置 scope:

singleton

至始至终都是一个Bean,在容器初始化是生产Bean

prototype

每次调用getBean（）方法之后生成一个新的Bean

request

在一次request请求中有效

You can change the internal state of the instance that is created as much as you want, because other instances created from the same loginAction bean definition will not see these changes in state; they are particular to an individual request. 

session

The Spring container creates a new instance of the UserPreferences bean by using the userPreferences bean definition for the lifetime of a single HTTP Session. In other words, the userPreferences bean is effectively scoped at the HTTP Session level. As with request-scoped beans, you can change the internal state of the instance that is created as much as you want, knowing that other HTTP Session instances that are also using instances created from the same userPreferences bean definition do not see these changes in state, because they are particular to an individual HTTP Session. 
application

```
<bean id="appPreferences" class="com.foo.AppPreferences" scope="application"/>
```

```
@SessionScope
@Component
public class UserPreferences {
    // ...
}
```

This is somewhat similar to a Spring singleton bean but differs in two important ways: It is a singleton per ServletContext, not per Spring 'ApplicationContext' (for which there may be several in any given web application), and it is actually exposed and therefore visible as a ServletContext attribute.
websocket
###### Singleton beans with prototype-bean dependencies
However, suppose you want the singleton-scoped bean to acquire a new instance of the prototype-scoped bean repeatedly at runtime. You cannot dependency-inject a prototype-scoped bean into your singleton bean, because that injection occurs only once, when the Spring container is instantiating the singleton bean and resolving and injecting its dependencies. If you need a new instance of a prototype bean at runtime more than once, see Method injection

java中的Session和request的区别：
session是针对userPereferences来的，在一段时间之后会失效，比较消耗资源
request是针对一次请求来的，里面的属性在两个页面之间有效
###### Scoped beans as dependencies

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- an HTTP Session-scoped bean exposed as a proxy -->
    <bean id="userPreferences" class="com.foo.UserPreferences" scope="session">
        <!-- instructs the container to proxy the surrounding bean -->
        <aop:scoped-proxy/>
    </bean>

    <!-- a singleton-scoped bean injected with a proxy to the above bean -->
    <bean id="userService" class="com.foo.SimpleUserService">
        <!-- a reference to the proxied userPreferences bean -->
        <property name="userPreferences" ref="userPreferences"/>
    </bean>
</beans>

```
The container injects this proxy object into the userManager bean, which is unaware that this UserPreferences reference is a proxy. In this example, when a UserManager instance invokes a method on the dependency-injected UserPreferences object, it actually is invoking a method on the proxy. The proxy then fetches the real UserPreferences object from (in this case) the HTTP Session, and delegates the method invocation onto the retrieved real UserPreferences object.

```
<bean id="userPreferences" class="com.foo.UserPreferences" scope="session">
    <aop:scoped-proxy/>
</bean>

<bean id="userManager" class="com.foo.UserManager">
    <property name="userPreferences" ref="userPreferences"/>
</bean>
```

