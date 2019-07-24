
### 基本类型
byte 1个字节 8位
boolean 1个字节 8位
short 2个字节 16位
char 2个字节 16位（中英文一样）
int 4个字节  32位
float 4个字节 32位
long 8个字节 64位
double 8个字节 64位
```
float a = 2.1f;
double b = 3.3;
b = a;
System.out.println(b);
//输出为2.0999999046325684
```

拆箱：是将引用类型转换成基本类型 Integer -> int d :调用IntValue()
装箱：是将基本类型转换成引用类型 int -> Integer :调用 Integer.valueOf() 

### 引用类型
String和数组都是引用类型

### 基本类型和基本类型--存储方式
基本类型存储：
栈有一个很重要的特殊性，就是存在栈中的基本类型数据可以共享。
比如： 我们同时定义：int a=3; int b=3;
编译器先处理int a = 3；首先它会在栈中创建一个变量为a的引用，然后查找有没有字面值为3的地址，没找到，
就开辟一个存放3这个字面值的地址，然后将a指向3的地址。
接着处理int b = 3；在创建完b这个引用变量后，由于在栈中已经有3这个字面值，便将b直接指向3的地址。
这样，就出现了a与b同时均指向3的情况。
(1)在函数中定义的基本类型变量（即基本类型的局部变量）和对象的引用变量（即对象的变量名）都在函数的栈内存中分配；
(2)堆内存用来存放由new创建的对象和数组以及对象的实例变量（即全局变量）。
(3)方法区存储类信息和静态变量以及final变量

### jvm各区存储
 堆区: 
1.存储的全部是对象，每个对象都包含一个与之对应的class的信息。(class的目的是得到操作指令) 
2.jvm只有一个堆区(heap)被所有线程共享，堆中不存放基本类型和对象引用，只存放对象本身 
栈区: 
1.每个线程包含一个栈区，栈中只保存基础数据类型的对象和自定义对象的引用(不是对象)，对象都存放在堆区中 
2.每个栈中的数据(原始类型和对象引用)都是私有的，其他栈不能访问。 
3.栈分为3个部分：基本类型变量区、执行环境上下文、操作指令区(存放操作指令)。 
方法区: 
1.又叫静态区，跟堆一样，被所有的线程共享。方法区包含所有的class和static变量。 
2.方法区中包含的都是在整个程序中永远唯一的元素，如class，static变量。

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
```
 @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
```

获取Class的三种方式
```
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

### jvm  结构
* 程序计数器（Program Counter Register）：程序计数器是一个比较小的内存区域，用于指示当前线程所执行的字节码执行到了第几行，可以理解为是当前线程的行号指示器。字节码解释器在工作时，会通过改变这个计数器的值来取下一条语句指令。

* 虚拟机栈（JVM Stack）：一个线程的每个方法在执行的同时，都会创建一个栈帧（Statck Frame），栈帧中存储的有局部变量表、操作站、动态链接、方法出口等，当方法被调用时，栈帧在JVM栈中入栈，当方法执行完成时，栈帧出栈。

* 本地方法栈（Native Method Statck）：本地方法栈在作用，运行机制，异常类型等方面都与虚拟机栈相同，唯一的区别是：虚拟机栈是执行Java方法的，而本地方法栈是用来执行native方法的，在很多虚拟机中（如Sun的JDK默认的HotSpot虚拟机），会将本地方法栈与虚拟机栈放在一起使用。

* 堆区（Heap）：堆区是理解Java GC机制最重要的区域，没有之一。在JVM所管理的内存中，堆区是最大的一块，堆区也是Java GC机制所管理的主要内存区域，堆区由所有线程共享，在虚拟机启动时创建。堆区的存在是为了存储对象实例，原则上讲，所有的对象都在堆区上分配内存（不过现代技术里，也不是这么绝对的，也有栈上直接分配的）。

* 方法区（Method Area）：（也被称为永久代），方法区是各个线程共享的区域，用于存储已经被虚拟机加载的类信息（即加载类时需要加载的信息，包括版本、field、方法、接口等信息）、final常量、静态变量、编译器即时编译的代码等。

* 直接内存（Direct Memory）：直接内存并不是JVM管理的内存，可以这样理解，直接内存，就是JVM以外的机器内存，比如，你有4G的内存，JVM占用了1G，则其余的3G就是直接内存，JDK中有一种基于通道（Channel）和缓冲区（Buffer）的内存分配方式，将由C语言实现的native函数库分配在直接内存中，用存储在JVM堆中的DirectByteBuffer来引用。由于直接内存收到本机器内存的限制，所以也可能出现OutOfMemoryError的异常。

### 监视器方法
* 任何一个对象 都有wait(),notify(),notifyAll()
* Condition也提供了类似的方法，await(),singal(),singalAll()

* Lock相当于Synchronized,Condition相当于对象的监视器

### 为什么switch表达式不允许在Java中使用long,float,double或boolean值？为什么只允许int(或者编译之后为int的类型)
只能支持byte short int char String（返回的hashcode是int,为了防止hash碰撞，继续用equal()方法判断）