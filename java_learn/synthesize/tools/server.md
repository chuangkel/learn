Tomcat Jboss Apache WebLogic区别与比较(阿里面试)
1. Tomcat是Apache鼎力支持的Java Web应用服务器，只能做JSP和Servlet的Container，不支持EJB，由于它优秀的稳定性以及丰富的文档资料，广泛的使用人群，从而在开源领域受到最广泛的青睐。­实际上Tomcat 部分是Apache 服务 器的扩展，但它是独立运行的，所以当你运行tomcat 时，它实际上作为一个与Apache 独立的进 程单独运行的。

2.Apache全球应用最广泛的http服务器，免费，出自apache基金组织.apache仅有的不足是，它本身仅提供html静态页面的功能，不能支持jsp、java com.github.chuangkel.servlet、asp等功能，

但通过同其他应用服务器一起工作或添加插件来支持。（稳定高效，通常需要与其他服务器软件或插件协同工作）

3. Jboss作为Java EE应用服务器，它不但是Servlet容器，而且是EJB容器（同时支持EJB的优化版tomcat），是完整的J2EE容器，从而受到企业级开发人员的欢迎，从而弥补了Tomcat只是一个Servlet容器的缺憾。­

4.BEA WebLogic Server是专门为企业电子商务应用系统开发的。它不但是Servlet容器，而且是EJB容器（同时支持EJB的优化版tomcat），是完整的J2EE容器， 企业电子商务应用系统需要快速开发，并要求服务器端组件具有良好的灵活性和安全性，

同时还要支持关键任务所必需的扩展、性 能、和高可用性。BEA WebLogic Server简化了可移植及可扩展的应用系统的开发，并为其它应用 系统和系统提供了丰富的互操作性。

但是weblogic不是一款开源的软件，虽然存在免费试用等，但它主要的应用目标为大型商业应用，而这种应用需要高额的license费用。

EJB与Spring的区别：

Spring容器取代了原有的EJB容器，因此以Spring框架为核心的应用无须EJB容器支持，可以在Web容器中运行。
Spring容器管理的不再是复杂的EJB组件，而是POJO（Plain Old Java Object） Bean。
对于Spring的作者而言，他已经深深地吃透了EJB的设计理念，并遵循这种理念开发出了一个开源的Spring框架。换个角度来看，Spring容器又何尝不是另一个Bean容器，只是这个Bean容器并未遵循Sun公司的EJB容器规范。
个人理解：也就是说EJB需要EJB提供的容器才能进行部署。