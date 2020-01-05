* mvn dependency:sources 拉去所有的资源码

* mvn package -Dmaven.test.skip=true
* jetty:run
* mvn clean package依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)等７个阶段
* mvn clean install依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)、install等8个阶段
* mvn clean deploy依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)、install、deploy等９个阶段
* package命令完成了项目编译、单元测试、打包功能，但没有把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库
* install命令完成了项目编译、单元测试、打包功能，同时行j把打好的可执ar包（war包或其它形式的包）布署到本地maven仓库，但没有布署到远程maven私服仓库
* deploy命令完成了项目编译、单元测试、打包功能，同时把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
            //指定 jar包存放的位置 相对于执行命令的当前路径
                <outputDirectory>../target</outputDirectory>
            // 执行命令是传入的参数  -DworkspaceName = XXX 
                <classifier>${workspaceName}</classifier>
                // mainClass 多模块下面指明主类
                <mainClass> com.github.chuankel.FirstAPPDemoApplication</mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

<packaging> jar </packaging> // 默认是jar包的方式
```
* mvn clean package -Dmaven.test.skip -DworkspaceName=YaoqbProject   

```
// maven 的setting 文件 ，会根据mirrorOf 进行依赖下载;
//优先完全匹配、次模糊匹配;
//相同的mirrorOf 有先后顺序
<mirror>
  <id>nexus-aliyun</id>
  <mirrorOf>*</mirrorOf>
  <name>Nexus aliyun</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public</url>
 </mirror>
```
maven插件是为了构建自己的工具集，复用其他模块之间的代码
自定义maven插件
命名方式：<yourplugin>-maven-plugin.
 
 Project：选择开源项目
 Issue Type：选择创建新项目
 Summary：随意命名
 Group Id：唯一标识，我是用com.qianxunclub,因为这个是我自己的域名，管理员会问qianxunclub.com这个是不是自己的网站，回答是就好了，如果使用com.github.xxxxx之类的，会方便一些。
 Project URL：项目源码地址，如https://gitee.com/qianxunclub/qianxunclub-maven-plugin
 SCM url：项目管理地址，如：https://gitee.com/qianxunclub/qianxunclub-maven-plugin.git
 
 开始上传jar包到maven中央仓库
 配置maven到settings.xml文件，添加以下内容：
 <servers> 
     <server> 
         <id>snapshots</id> 
         <username>https://issues.sonatype.org的注册账号</username> 
         <password>https://issues.sonatype.org的注册密码</password> 
     </server>
 </servers>
 这里注意以下，如果使用特殊符号，是需要转义的，例如：pwd&，密码要填写成pwd&amp;
 
 在自己的项目中修改pom.xml，添加以下内容：
 <distributionManagement>
     <repository>
         <id>snapshots</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
     </repository>
     <snapshotRepository>
         <id>snapshots</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
     </snapshotRepository>
 </distributionManagement>
 
 到这里就配置完成了，接下来开始发布试试了：
 mvn clean package deploy
 出现success字样，代表已经成功，可以在https://oss.sonatype.org/content/groups/public找到自己的jar包了。
 
 
 Dependency Scope
 Dependency scope is used to limit the transitivity of a dependency, and also to affect the classpath used for various build tasks.
 
 There are 6 scopes available:
 
 compile
 This is the default scope, used if none is specified. Compile dependencies are available in all classpaths of a project. Furthermore, those dependencies are propagated to dependent projects.
 provided
 This is much like compile, but indicates you expect the JDK or a container to provide the dependency at runtime. For example, when building a web application for the Java Enterprise Edition, you would set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. This scope is only available on the compilation and test classpath, and is not transitive.
 runtime
 This scope indicates that the dependency is not required for compilation, but is for execution. It is in the runtime and test classpaths, but not the compile classpath.
 test
 This scope indicates that the dependency is not required for normal use of the application, and is only available for the test compilation and execution phases. This scope is not transitive.
 system
 This scope is similar to provided except that you have to provide the JAR which contains it explicitly. The artifact is always available and is not looked up in a repository.
 import
 This scope is only supported on a dependency of type pom in the <dependencyManagement> section. It indicates the dependency to be replaced with the effective list of dependencies in the specified POM's <dependencyManagement> section. Since they are replaced, dependencies with a scope of import do not actually participate in limiting the transitivity of a dependency.
 Each of the scopes (except for import) affects transitive dependencies in different ways, as is demonstrated in the table below. If a dependency is set to the scope in the left column, transitive dependencies of that dependency with the scope across the top row will result in a dependency in the main project with the scope listed at the intersection. If no scope is listed, it means the dependency will be omitted.
 
 
 打包 子模块下面不要包含maven-plugin 
 A->B->C
 直接B引入C A再引入B ,A可以用C中的内容