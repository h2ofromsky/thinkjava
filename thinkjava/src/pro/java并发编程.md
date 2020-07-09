##### 减少上下文切换

+ 无锁并发编程
+ CAS算法
+ 使用最少线程
+ 协程 

##### 避免死锁的方法

+ 避免一个线程获取多个锁
+ 避免一个线程在锁内占用多个资源
+ 尝试使用定时锁 lock.tryLock(timeout)
+ 数据库锁，加索和解锁必须在同一个数据库连接

#### Java并发机制的底层实现原理

``` 
java代码-java字节码-（类加载器）-JVM中
java并发机制依赖于JVM实现和CPU指令
```

##### volatile

1. volatile是轻量级的synchronized,保证可见性

volatile实现原则

1. Lock前缀指令引起处理器缓存回写到内存
2. 一个处理器的缓存回写到内存会导致其他处理器的缓存失效

##### synchronized

``` 
java中每个对象都可作为锁
1.普通同步方法，锁是当前实例对象
2.对于静态同步方法，锁是当前类的class对象
3.对于同步方法块，锁是synchronized括号中配置的对象
JVM基于进入和退出Monitor对象来实现方法同步和代码块同步
代码块同步是使用monitorenter和monitorexit指令实现
monitorenter指令在编译后插入到同步代码块开始的位置，monitorexit则是插入到方法结束处和异常处
每个monitorenter都有对应的monitorexit，每个对象都有一个monitor与之关联，当一个monitor被持有后将处于锁定状态，线程执行到monitorenter指令时，将尝试获取对象对应的monitor的所有权，既尝试获取对象的锁。
synchronized用的锁存在JAVA对象头中，
```

​	JAVA SE 1.6中，锁共有四种状态，级别从低到高依次是:无锁状态，偏向锁状态，轻量级锁状态，重量级锁状态

**锁可以升级不能降级**

##### 偏向锁

##### 轻量级锁

#### 原子操作

``` 
CAS compare and swap
1.使用总线锁保证原子性
2.使用缓存锁保证原子性
ABA问题
循环时间长开销大
只能保证一个共享变量的原子操作
```

#### JAVA内存模型

线程通信机制：共享内存，消息传递

java并发采用的是共享内存模型，java线程之间的通信总是隐式进行

Java中所有实例域，静态域，数组元素都存储在堆内存中，对内存在线程之间共享

局部变量，方法定义参数，和异常处理器参数不会在线程之间共享

线程间共享变量存储在主内存中，每个线程都有一个私有的本地内存

``` 
源代码-编译器优化重排序-指令级并行重排序-内存系统重排序-最终执行的指令序列
```

##### volatile 内存语义

###### 锁内存语义

ReentrantLock的实现依赖于Java同步器框架AbstractQueueSynchronizer（AQS）

AQS使用一个整型的volatile变量（state）维护同步状态

