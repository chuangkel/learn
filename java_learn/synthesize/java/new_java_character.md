## java1.8新特性



### 语言

#### lambda表达式
- Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
- Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
- lambda表达式的重要特征:
    - **可选类型声明：**不需要声明参数类型，编译器可以统一识别参数值。
    - **可选的参数圆括号：**一个参数无需定义圆括号，但多个参数需要定义圆括号。
    - **可选的大括号：**如果主体包含了一个语句，就不需要使用大括号。
    - **可选的返回关键字：**如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

```java
	//所有的匿名类都可以改成函数式编程
    Runnable r = () -> System.out.println("hell! it is running");
    Thread t = new Thread(r);
    t.start();

    BinaryOperator<Integer> count = (Integer x, Integer y) -> x + y;
    System.out.println(count.apply(1,2));
    
    BinaryOperator<Long> cc = (x,y) -> x + y;
    System.out.println(cc.apply(2L,3L));
    
    ActionListener actionListener = event -> System.out.println("is clicked");
       
    Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );

	Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    if(s == "c") {
                        return false;
                    }
                    return true;
                }).forEach(System.out::println);
```


### 接口的默认方法和静态方法

* Java 8增加了两个新的概念在接口声明的时候：默认和静态方法。默认方法和Trait有些类似，但是目标不一样。默认方法允许我们在接口里添加新的方法，而不会破坏实现这个接口的已有类的兼容性，也就是说不会强迫实现接口的类实现默认方法。默认方法和抽象方法的区别是抽象方法必须要被实现，默认方法不是。作为替代方式，接口可以提供一个默认的方法实现，所有这个接口的实现类都会通过继承得倒这个方法（如果有需要也可以重写这个方法），让我们来看看下面的

```java
public interface InterfaceDefaultMethod {
    /**
     * default关键字 默认方法可以被实现类或者继承类覆写： 方法名 + 参数类型及个数 + 方法访问修饰符 一致
     */
    default void test(){
        System.out.println(this.getClass());
    }
}
```

### 编译器



### 类库
* Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。Optional 类的引入很好的解决空指针异常。

```
/**
 * A container object which may or may not contain a non-null value.
 * If a value is present, {@code isPresent()} will return {@code true} and
 * {@code get()} will return the value.
 *
 * <p>Additional methods that depend on the presence or absence of a contained
 * value are provided, such as {@link #orElse(java.lang.Object) orElse()}
 * (return a default value if value not present) and
 * {@link #ifPresent(java.util.function.Consumer) ifPresent()} (execute a block
 * of code if the value is present).
 *
 * <p>This is a <a href="../lang/doc-files/ValueBased.html">value-based</a>
 * class; use of identity-sensitive operations (including reference equality
 * ({@code ==}), identity hash code, or synchronization) on instances of
 * {@code Optional} may have unpredictable results and should be avoided.
 *
 * @since 1.8
 */
public final class Optional<T> {}
```




### 开发工具



### jvm

