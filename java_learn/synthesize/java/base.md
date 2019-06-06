
### 基本类型
byte
int
long
float
double
### String


### 关键字
Serilizable 序列化类
transient 标识不被序列化的字段


##### ConcurrentHashMap
特点：线程安全。分段锁，效率高。

volatile，final，CAS
CurrentHashMap
map迭代：
EntryKey
### IO/NIO
传统io
是基于字节流 字符流
NIO(new io)
是基于缓冲区的
Channel
Selector
Pipline

### 网络编程
socket

### jvm知识
jps查看线程id
jstack查看该线程的堆栈信息

### java1.8 新特性
###### 本地时间

```
 * Instant：时间戳
 * Duration：持续时间，时间差
 * LocalDate：只包含日期，比如：2016-10-20
 * LocalTime：只包含时间，比如：23:12:10
 * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
 * Period：时间段
 * ZoneOffset：时区偏移量，比如：+8:00
 * ZonedDateTime：带时区的时间
 * Clock：时钟，比如获取目前美国纽约的时间
 * java.time.format类
 * DateTimeFormatter：时间格式化
```


```
//获取当前时间、日期 去掉毫秒
LocalTime localTime = LocalTime.now().withNano(0);
System.out.println(localTime);

//计算天数差
LocalDate localDate = LocalDate.now();
LocalDate futureDay = LocalDate.of(2019,2,01);
Period p = Period.between(localDate,futureDay);
System.out.println(p.getDays());
//日期加一天 对应的时间可以加一个小时
LocalDate plusDay = localDate.plusDays(1);
System.out.println(plusDay);
//日期格式化
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
String time = "20190108";
LocalDate formatDate = LocalDate.parse(time,formatter);
System.out.println(formatDate);
```

###### stream流
函数式编程对集合进行处理

```
Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    if(s == "c")
                        return false;
                    return true;
                }).forEach(System.out::println);
```

###### 函数编程
函数可以以参数的形式作为入参

```
        //所有的匿名类都可以改成函数式编程
        //第一种，
        Runnable r = () -> System.out.println("hell! it is running");
        Thread t = new Thread(r);
        t.start();
        //第二种，
        BinaryOperator<Integer> count = (Integer x, Integer y) -> x + y;
        System.out.println(count.apply(1,2));
        //第三种，
        BinaryOperator<Long> cc = (x,y) -> x + y;
        System.out.println(cc.apply(2L,3L));
        //第四种，
        ActionListener actionListener = event -> System.out.println("is clicked");
        //第五种，
        Runnable runnable = () ->{
            System.out.println("hello");
            System.out.println("world");
        };
```

###### 接口可以添加函数
避免统一增加函数时的不方便。

```
public interface TestImpl {
    /**
     * default关键字
     */
    default void test(){
        System.out.println(this.getClass());
    }
}
```

### 类加载机制
启动类加载器
扩展类加载器
系统类加载器

双亲委派模型
findClass
loadClass
Class.forName()
自定义类加载器

外部连接
https://blog.csdn.net/zhoudaxia/article/details/35824249




生成随机数
```
System.out.println(UUID.randomUUID());
System.out.println(UUID.fromString("d351d4bf-249e-449d-b735-a7083368b500"));
System.out.println(UUID.nameUUIDFromBytes(new byte[]{1,2}));
String str = RandomStringUtils.random(6,false,true);
System.out.println("六位验证码"+str);
```


```
url和URi区别
```

```
BeanUtils.copyProperties
```

断言
```
assertEquals 和 assertTrue 区别
相同之处：都能判断两个值是否相等
assertTrue 如果为true，则运行success，反之Failure
assertEquals 如果预期值与真实值相等，则运行success，反之Failure

不同之处：
assertEquals 运行Failure会有错误提示，提示预期值是xxx，而实际值是xxx。容易调式
assertTrue 没有错误提示
```

```
ServletOutputStream.getOutputStream().write()
//在servelt中用response.getOutputStream().write('1')输出到浏览器为什么不乱码，response.getOutputStream().write(1)为什么乱码？
//response.getOutputStream().write("1222".getByte())为什么不乱码 ！ 凡是通过字符串，字符输出不乱码 为什么？
```

```
编译状态，Object是Class的子类自身类父类运行结果为true.
运行状态，Object是Class的父类会编译不报错，运行结果为false.
Object instanceof Class
//判断对象实例Object是否是Class的实例对象
包权限
默认权限包内可见
protected权限在默认权限的基础是子类可见
```



### 日期
```
时间戳转换成日期，存在毫秒时间戳，秒时间戳
date.setTime(time);
日期转换成时间戳
date.getTime()
```

```
SecurityManager java安全管理器
-Djava.security.manager //开启安全策略
```

```
AtomicInteger的使用

TimeUnit.SECONDS.sleep(1);

AtomicBoolean

DelayQueue延时队列操作实例

Vectory.clone()
```
序列化
只有类型为Enum、Array、Serializable才能被序列化
通过ObjectOutputStream和ObjectInputStream实现序列化的对对象进行序列化。
序列化并不会保存静态变量
想将父类对象序列化，就要让父类也实现Serializable接口。
Transient关键字的作用是控制变量的序列化，阻止这个被声明的变量序列化到文件中，
transient变量的值被设为初始值，对象设为null,int设为0
序列化对敏感数据进行加密解密
除了Serializable接口 还可以实现Externalizable来实现序列化,需要实现如下两个方法
```bash
 @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
```

获取Class的三种方式
```bash
1.Class class = Person.class

2.Person obj = new Person();
  Class class = obj.getClass();

3.Class class = Class.forName("className");
```

生成伪随机数的算法
```
public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);

        int r = next(31);
        int m = bound - 1;
        if ((bound & m) == 0)  // i.e., bound is a power of 2
            r = (int)((bound * (long)r) >> 31);
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0;
                 u = next(31))
                ;
        }
        return r;
    }
```
```
  protected int next(int bits) {
        long oldseed, nextseed;
        AtomicLong seed = this.seed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
    }
```
>> 和 >>> 的区别
都是表示向右移位，但是>>移位时高位用1填充，而>>>是用0填充

```
被该注解标注的都都是函数式接口，内部都是传入方法函数的
@FunctionalInterface
```
