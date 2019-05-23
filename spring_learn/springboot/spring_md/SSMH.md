# 后端知识
</context-param>和</init-param>区别：

</init-param>标签是指定<serlet>的初始化参数的，如果没有配置contextConfigLocation会默认找webapp下的WEB-INF下的[XXX-com.github.chuangkel.servlet.xm]。
```
<com.github.chuangkel.servlet>
    <com.github.chuangkel.servlet-name>applicationContext1</com.github.chuangkel.servlet-name>
    <com.github.chuangkel.servlet-class>org.springframework.web.com.github.chuangkel.servlet.DispatcherServlet</com.github.chuangkel.servlet-class>
      <init-param>
配置是事务规则

4.配置事务入口

<param-name>contextConfigLocation</param-name>
          <param-value>/WEB-INF/applicationContext-com.github.chuangkel.servlet.xml</param-value>
      </init-param>
  </com.github.chuangkel.servlet>
```

</context-param> 

### Hibernate

1.配置SessionFactory

```
<beanid="sessionFactory"
    class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
    <propertyname="configLocation"value="classpath:hibernate.cfg.xml"/>
</bean>
```


2.配置事务容器

```
<beanid="transactionManager"
    class="org.springframework.orm.hibernate3.HibernateTransactionManager">
    <propertyname="sessionFactory"ref="sessionFactory"/>
</bean>
```


3.配置是事务规则


```
<tx:adviceid="txAdvice"transaction-manager="transactionManager">
    <tx:attributes>
        <tx:methodname="add*"propagation="REQUIRED"/>
        <tx:methodname="modify*"propagation="REQUIRED"/>
        <tx:methodname="del*"propagation="REQUIRED"/>
        <tx:methodname="*"propagation="REQUIRED"read-only="true"/>
    </tx:attributes>
</tx:advice>
```


4.配置事务入口

```
<aop:config>
    <aop:pointcutid="allDaoMethod"expression="execution(* com.jianxin.dao.*.*(..))"  />
    <aop:advisoradvice-ref="txAdvice"pointcut-ref="allDaoMethod"/>
</aop:config>
```



# 前端知识

 res.data是一个数组，排序
```
res.data.sort(function (b, a) { return new Date(a.beginTime).getTime() - new Date(b.beginTime).getTime() });
```
