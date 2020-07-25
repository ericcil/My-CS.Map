多线程

​	特性：原子性、可见性、有序性

* synchronized

  * 锁原理，锁升级

    无锁状态 -》偏向锁 -》轻量级锁 -》重量级锁

    

  * 可重入锁

* cas

  * 原理 compare and swap

    内核级别指令：lock cmpxchg

  * ABA问题

* volatile关键字

  * 可见性
  * 阻止指令重排序

* Atomic*

  * AtomicInteger
  * LongAdder
  * AtomicStampReference

* ReentrantLock

  * 可重入锁
  * lock、tryLock、lockInterruptibly
  * condiction

* CountDownLatch

* CyclicBarrier

* Phaser

* ReadWriteLock

* Semaphore

* Exchanger

