# My-CS.Map

基于JAVA的编程学习地图

* [java](./)
  * [多线程](#多线程)
  *  [IO](/IO)
* [设计模式](./)
* [网络]()
* [数据库]()
* [系统设计]()
* [数据结构&算法](/算法)

------

### 多线程

* 基本原理：原子性、可见性、有序性

  底层原理：

  > JMM内存模型
  >
  > cache line，MESI（缓存一致性协议）
  >
  > 指令重排序

* synchronized

  > 可重入锁：同一个线程已经获取一个锁之后，还能多次重复获取改锁
  >
  > 原理：
  >
  > ​	对象内存布局
  >
  > 锁升级：
  >
  > 	1. 无锁状态
  >  	2. 偏向锁
  >  	3. 自旋锁
  >  	4. 重量级锁

* cas

  原理：

  > ```
  > expect = read()
  > data = calculate()
  > compareAndSwap(expect,data)
  > ```
  >
  > 指令：lock cmpxchg

  ABA问题

* volatile关键字

  > * 保证可见性
  >
  > * 阻止指令重排序
  >
  >   懒汉模式单例，双重检测，防治 instance = new instance 时被重排

* JUC

  * Atomic*
    * AtomicInteger
    * LongAdder
    * AtomicStampReference
  * ReentrantLock
  * CountDownLatch
  * CyclicBarrier
  * Phaser
  * ReadWriteLock
  * Semaphore
  * Exchanger

* 

