



## class



```java
* Instances of the class {@code Class} represent classes and
* interfaces in a running Java application.  An enum is a kind of
* class and an annotation is a kind of interface.  Every array also
* belongs to a class that is reflected as a {@code Class} object
* that is shared by all arrays with the same element type and number
* of dimensions.  The primitive Java types ({@code boolean},
* {@code byte}, {@code char}, {@code short},
* {@code int}, {@code long}, {@code float}, and
* {@code double}), and the keyword {@code void} are also
* represented as {@code Class} objects.
*
* <p> {@code Class} has no public constructor. Instead {@code Class}
* objects are constructed automatically by the Java Virtual Machine as classes
* are loaded and by calls to the {@code defineClass} method in the class
* loader.
```

* Class 是class和interface在运行时的实例，enum是一种类，annotation是一种接口
* Class 没有公共的构造方法，class对象是jvm通过类加载器classloader中的defineClass（）方法构造的。



```java
/*
 * Private constructor. Only the Java Virtual Machine creates Class objects.
 * This constructor is not used and prevents the default constructor being
 * generated.
 */
private Class(ClassLoader loader) {
    // Initialize final field for classLoader.  The initialization value of non-null
    // prevents future JIT optimizations from assuming this final field is null.
    classLoader = loader;
}
```

* 私有的构造器可以防止默认的构造器生成，防止用户去实例化，jvm可以实例化的。

```java
/**
 * Returns the class loader for the class.  Some implementations may use
 * null to represent the bootstrap class loader. This method will return
 * null in such implementations if this class was loaded by the bootstrap
 * class loader.
 *
 * <p> If a security manager is present, and the caller's class loader is
 * not null and the caller's class loader is not the same as or an ancestor of
 * the class loader for the class whose class loader is requested, then
 * this method calls the security manager's {@code checkPermission}
 * method with a {@code RuntimePermission("getClassLoader")}
 * permission to ensure it's ok to access the class loader for the class.
 *
 * <p>If this object
 * represents a primitive type or void, null is returned.
 *
 * @return  the class loader that loaded the class or interface
 *          represented by this object.
 * @throws SecurityException
 *    if a security manager exists and its
 *    {@code checkPermission} method denies
 *    access to the class loader for the class.
 * @see java.lang.ClassLoader
 * @see SecurityManager#checkPermission
 * @see java.lang.RuntimePermission
 */
@CallerSensitive
public ClassLoader getClassLoader() {
    ClassLoader cl = getClassLoader0();
    if (cl == null)
        return null;
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        ClassLoader.checkClassLoaderPermission(cl, Reflection.getCallerClass());
    }
    return cl;
}
```

* 原始类型和void关键字没有类加载器