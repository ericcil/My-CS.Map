# My-CS.Map

基于JAVA的编程学习地图

* [java](./)
  * [多线程](#多线程)
  *  [IO](/IO)
* [设计模式](./)
* [网络](#网络)
* [数据库]()
* [系统设计](#系统设计)
* [数据结构&算法](/算法)

------

### 多线程

* 基本原理：原子性、可见性、有序性（happen-befor）

* 线程状态

  底层原理：

  > JMM内存模型
  >
  > cache line，缓存一致性协议（x86上为MESI）
  >
  > 指令重排序

* synchronized

  > 可重入锁：同一个线程已经获取一个锁之后，还能多次重复获取改锁
  >
  > 原理：
  >
  > ​	字节码：monitorentry，monitorexit
  >
  > ​	JVM级别：对象内存布局markword（8字节）、classpointer（默认压缩4字节）、instancedata、padding
  >
  > ​		内存超32G时-XX:+UseCompressedClassPointers默认false
  >
  > ​	底层指令lock cmpxchg
  >
  > 锁升级：
  >
  > 	1. 无锁状态
  >  	2. 偏向锁，默认有时延4s，-xx:BiasedLockingStartupDelay=0
  >  	3. 自旋锁 
  >  	4. 重量级锁，需要向系统获取
  >
  > object.wait()会释放锁
  >
  > object.notify()不释放锁

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
  >
  > * 底层实现
  >
  >   汇编码：lock
  >
  >   1. 保证可见性
  >
  >      cache line，缓存一致性协议（x86上为MESI）或者升级为 锁总线
  >
  >      cpu每个核有各自的L1,L2缓存，cpu共享L3缓存
  >
  >      主存 -》L3 -》 L2 -》 L1 -》  ，读取数据一个cache line读（局部性原理,64字节），cpu级别的缓存一致性是按cache line为单位
  >
  >      volatile会锁定共享变量所在的缓存行（可以通过缓存行对齐，减少cpu同步数据的时间消耗，disruptor)
  >
  >   2. 阻止重排序
  >
  >      字节码增加 ACC_VOLATILE
  >
  >      JVM的内存屏障，屏障两边指令不可重排
  >
  >      ​	JRR：LoadLoad、StoreStore、LoadStore、StoreLoad
  >
  >      系统原语：内存屏障 fence
  >
  >      内存屏障 或者升级为 锁总线
  >
  >   

* Atomic*

  * AtomicInteger
  * LongAdder
  * AtomicStampReference

* JUC

  * AQS

  * ReentrantLock

    公平锁、非公平锁

    底层实现cas

    和synchronized差异

  * CountDownLatch

  * CyclicBarrier

  * Phaser

  * ReadWriteLock - StampedLock

  * Semaphore

  * Exchanger

  * LockSoupport

* UnSafe

* ThreadLocal

  强、软、弱、虚引用

  虚引用 -》管理堆外内存

  线程内部维护一个ThreadLocalMap，key为当前ThreadLocal对象，entry为弱引用

  使用完毕需要调用remove，防止内存泄漏

------



### IO

socket：ip+port，ip+port的四元组

程序不能直接访问内核，编译器指向软中断（80中断），由用户空间切换至内核空间

* IO模型

  * 同步阻塞模型

    通过socket，accept()，read()都是阻塞

  * 同步非阻塞模型

    基于select()，需要用户空间向内核空间传入需要访问的所有fd,内核进行遍历，给用户空间返

    poll回可以读取的fd

    poll

  * 多路复用模型（selector）

    基于epoll：epoll_create()、epoll_ctl()、epoll_wait()

  * 信号驱动IO模型

  * 异步IO模型

* JAVA NIO

* Netty

* 输入：mmap，避免用户空间到内核空间的写入耗损

* 输出：0拷贝，内核调用send_file()，不用经过用户空间，直接输出

------



### 网络

* OSI七层模型：应用层、表示层、会话层、传输层、网络层、链路层、物理层

* TCP五层：应用层、传输层（tcp，udp）、网络层（ip）、链路层、物理层

* TCP三次握手：client - 》 syn包 -》 server；server -》syn+ack -》client； client -》 ack -》 server

* TCP四次挥手：a -》fin -》 b；b -》ack -》a；b -》fin -》a；a -》ack -》b

* TCP粘包、粘包

* IP

  * ip、netmask掩码、gateway网关、dns
  * ip与netmask按位与=网络号
  * 路由表

* HTTPS

  非对称加密，公钥加密，私钥解密；SSL TLS

  CA（可靠的三方机构，为用户验证服务站点的真实性）

  握手期间使用非对称加密，通信期间使用对称加密（使用握手期生成的session key）

  * 443端口
  * 四次握手
    1. client hello，客户端发送随机数+cipher suites
    2. server hello，返回服务端随机数+CA证书+公钥+选定的cipher suite
    3. client key exchange，客户端根据根证书验证服务器证书，生成第三个随机数（预主秘钥），并使用公钥加密，同时会附带主秘钥签名过的内容
    4. Server Change Cipher Spec，服务端使用私钥解密，使用三个随机数生成主秘钥，进行验签，并返回ack

------

### JVM

* 对象定位

  * 句柄方式：实例数据指针、类型数据指针
  * 直接指针：直接指向实例，再指向类

* 对象分配

  ![image-20200725230109276](D:\0.2develop\git_repository\My-CS.Map\JAVA\1595689251.png)

* Class实例

  * 1.7 Class在方法区
  * 1.8 Class生成在堆上，方法区存放对应的instanceClassOop（C++实现），oop关联Class，对象实例指向oop

* GC

  * JVM参数，标准“-”开头、非标准“-X”开头、不稳定“-XX”开头

    -XX:+PrintFlagsFinal 显示最终生效的设置

    -XX:+PrintFlagsInitial 显示默认值

    -XX:+PrintCommandLineFlags 命令行参数

  * 引用计数、可达性分析

    * 根对象：线程栈变量、静态变量、常量池、JNI指针

  * 清理算法

    * mark-sweep标记清理：会产生不连续空间
    * copying拷贝：只能使用一半的空间，另一半留作拷贝
    * mark-compact标记压缩：多了压缩整理的耗时，效率相对低

  * 分代模型（G1不区分）

    * 1.7：new 新生代、old 老年代、永久代

    * 1.8：新生代、老年代、元数据区

    * 永久代、元数据 - 存放Class：永久代必须指定大小限制，会发生溢出，元数据区可以不设置上限

    * 字符串常量：1.7存在永久带，1.8存在堆

    * 方法区：逻辑概念，1.7对应永久代，1.8对应元数据区

    * 堆

      * 新生代 Young GC   -coping
        * eden
        * survivor
      * 老年代 Full GC - mark-compact

    * minorGC = YGC；majorGC=FGC

    * 回收算法

      | 新生代                        | 老年代      | 特殊       |
      | ----------------------------- | ----------- | ---------- |
      | serial                        | SerialOld   | G1         |
      | parNew（能配合CMC             | ParallelOld | ZGC        |
      | parallelScavenge（不能配合CMS | CMS         | Shenandoah |
      |                               |             | Epsilon    |

      * CMS - 垃圾回收和应用同时
        * 初始标记（STW）、并发标记、重新标记（STW）、并发清理
        * 垃圾标记和应用同时运行，存在错标，CMS、G!都是通过三色标记纠正，ZGC采用颜色指针



------

### 数据库

* 索引

  dataPage -> B-tree \ B+tree  一次16Kb

  B-tree每个节点除索引数据外都会存data，造成一个page能存的索引有限，无法降低树深度，使得IO次数增加，查找速度变慢

  B+tree只有叶子节点存data，叶子节点有指向前后数据的指针

  聚集索引、非聚集索引

  回表：非主键索引查找，第一次只能找到主键索引，根据主键索引第二次才能找到其他数据

  索引覆盖

  最左匹配（组合索引）

  索引下推

  ​	5.6之前先根据索引a获取数据，再根据索引b筛选

  ​	5.6之后根据索引a，b做查找

  优化器

  ​	CBO

  ​	RBO

  hash索引、组合索引（最左匹配）

  前缀索引

  索引失效条件

  ​	类型转换

  ​	范围查询之后的条件

* 

* 

------

### 系统设计

* 分布式

* redis

  一个worker线程，IO线程不一定唯一（6.X之后有多个IO线程）

  基本类型

  * string（bitmap，含布隆过滤器）

  * list（栈、队列、数组、ltrim）

  * hash（聚合场景）

  * set（去重，无序，不推荐使用，会rehash，推荐好友：交集、差集）

  * zset（排行榜、分页；底层实现：跳表）

    > redis过期策略
    >         定时删除：每个设置了过期时间的key都创建一个定时器，到期清除，会占用大量cpu资源
    >         惰性删除：过期不触发删除，查询时判断是否过期，过期则删除返回null
    >         定期删除：统一的定时器，每隔一段时间扫描一定量的在expires字典内的key，过期则清除
    >
    > 内存删除策略
    >
    > ​		volatile-lru -> 在有设置超时时间的key中，删除最近最少使用的key
    > ​        allkeys-lru -> 在所有key中，删除最近最少使用的key
    > ​        volatile-lfu -> 在有设置超时时间的key中，删除使用频率最低的key
    > ​        allkeys-lfu -> 在所有key中，删除使用频率最低的key
    > ​        volatile-random -> 在有设置超时时间的key中，随机删除
    > ​        allkeys-random -> 在所有key中，随机删除
    > ​        volatile-ttl -> 在有设置超时时间的key中，先删除距离过期时间最近的key
    > ​        noeviction -> 什么都不做，在新增key时抛异常
    >
    > 持久化
    >
    > ​		4.3之前RDB和AOF不相容，4.3之后可以混用：aof-use-rdb-preamle
    >
    > ​        RDB（恢复速度快，数据缺失比较多）
    > ​        AOF（append only file）记录指令操作，便于重启后还原数据（可和RDB同时开启）慢，冗余多
    > ​            配置appendfsync：
    > ​                always：每条指令都记录，最慢，最安全
    > ​                no：让系统决定什么时候记录，最快
    > ​                everysec：每秒同步一次
    > ​            no-appendfsync-on-rewrite：开启，防止aof线程阻塞太久，但是数据优先写入缓存，也会有丢失的风险
    > ​            提供了rewrite机制，当日志超过一定大小，进行rewrite，删除不必要的命令分布式锁

  单点故障：主从，集群（需要同步）

  压力：分片，分集群（不需要同步）

  AKF拆分

* nginx

* zookeeper



