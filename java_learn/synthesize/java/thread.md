###java base

####Channel
####buffer
####Selector

### 多线程
Thread
Runnable
Callable
```
CallableThread c = new CallableThread();
//Callable需要包装成FutureTask
FutureTask futureTask = new FutureTask(c);
Thread t = new Thread(futureTask);
t.start();
String result = String)futureTask.get();
```

#### 线程同步：
##### 1.为什么要进行线程同步？
当多个线程对同一个资源变量进行操作时，会产生资源竞争，如果不对读写权限进行控制，将引发内存的修改变得混乱不可预测。
##### 2.如何进行线程同步控制？

###### synchronized关键字
注意：加载类的静态代码块将会对整个类进行加锁。
###### volaite关键字
读写操作都是对内存进行的，so所有的线程对别的线程的修改都是可见的。
###### lock()和unlock()方法
这两个方法是配合着使用的
#### 线程异步：
线程异步就是请求读写资源时，不会等待请求返回，而是继续去处理其他的任务，请求处理完成之后会以回调函数的方式返回。
#### 线程安全：
就是线程同步的内容
#### 线程池
六大参数含义
##### 线程组ThreadGroup
```
获得当前线程组的名字
Thread.currentThread().getThreadGroup().getName()
守护线程和非守护线程
守护线程 java.lang.Thread.isDaemon()方法

private final static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Constants.MSG_CORE_POOL_SIZE, Constants.MSG_MAXIMUM_POOL_SIZE, Constants.MSG_KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.CallerRunsPolicy());
1. CallerRunsPolicy ：这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
2. AbortPolicy ：对拒绝任务抛弃处理，并且抛出异常。
3. DiscardPolicy ：对拒绝任务直接无声抛弃，没有异常信息。
4. DiscardOldestPolicy ：对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
```


线程间通信
notify() notifyAll() wait() 来实现，需放置在同步块中执行
例子：生产者消费者模式
假唤醒：在不满足唤醒条件下被唤醒了，造成程序错误。
在调用wait()之前调用了notify()造成了死锁，需要一个信号量（共享对象/资源）
来同步，同时加上自旋锁,即while()

什么是死锁
两个或多个线程等待已经被加锁的资源，其他线程等待它锁住的资源
如何避免死锁？
1.串行化加锁，需提前知道所要加的锁。
2.死锁检测机制，记录线程加锁的对象，请求锁的对象。

公平锁 使用队列方式，队列里面的元素保存了共享变量保证信号量不会丢失。

LocalThread 线程本地化变量，线程本地可见，只能本地更新。
可重入锁：synchronized是可重入锁，同一个锁住的对象内的两个同步块可以相互调用。
线程饥饿：synchronized 同步块并不能保证所有线程都得到执行。

ForkJoinPool

ExecutorService submit()
Executors.new。。。



