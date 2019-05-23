@Aspect放在类声明前表明可以作为切面切入
@Aspect就是使得这个类具有AOP功能（你可以这样理解）

##### java注解
@Deprecated

##### pom环境配置
dependencyManagement
在dependencyManagement元素下得依赖声明不会引入实际的依赖，不过它能够约束dependencies下的依赖使用。


#### 疑问：
@Value("${task.taskKeepAliveTime:#{60000}}")
    private Long taskKeepAliveTime;
   
```
threadPool.submit(thread);
threadPool.execute(thread);
```
import只在dependencyManagement元素下才有效果，作用是将目标POM中的dependencyManagement配置导入并合并到当前POM的dependencyManagement元素中，如下就是讲account-aggregator中的dependencyManagement配置导入并合并到当前POM中。

```
<dependencyManagement>
      <dependencies>
        <dependency>
            <groupId>com.youzhibing.account</groupId>
              <artifactId>account-aggregator</artifactId>
              <version>1.0.0-SNAPSHOT</version>
            <type>pom</type>
              <scope>import</scope>
        </dependency>
      </dependencies>
  </dependencyManagement>
```

req实体转换成json字符串
```
Gson gson = new Gson();
String reqJson = gson.toJson(req);
```
json字符串转换成JSONObject
```
JSONObject jj = JSONObject.parseObject(reqJson);
```
req实体转换成JSONObject
```
Object object = JSONObject.toJSON(req);
JSONObject jsonObject=(JSONObject)JSONObject.toJSON(req);
```
```
{"cardNo":"6222","name":"章","mobileNo":"152","idNo":"330"}
```

```
PersonBankAuthRes personBankAuthRes  = JSONObject.toJavaObject(result,PersonBankAuthRes.class)
PersonBankAuthRes personBankAuthRes = JSONObject.parseObject(JSONObject.toJSONString(result),PersonBankAuthRes.class)
```
@WebFilter 

StringBuilder
@SuppressWarnings("ALL")

@Import(FeignClientsConfiguration.class)

Facade外观模式  

