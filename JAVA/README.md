### JDK1.7   HashTable

​	resize -> transfer(newTable,rehash)

​	当rehash==true 进行rehash

​	

### ConcurrentHashMap

* jdk 1.7  Segment -> HashEntry

  基于分段锁，Segment，继承自ReentrantLock，

  数据存在Segment中，

  查询遍历链表效率低

* jdk 1.8

  基于CAS + synchronized

  

### Spring

* IOC
* AOP