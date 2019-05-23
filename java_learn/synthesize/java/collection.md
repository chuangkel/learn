### 数据结构(集合)
#### 数组
快速访问
增删效率低
#### 链表
访问效率低
增删效率高
#### set
HashSet 基于HashMap实现
#### map
##### HashMap
http://jayfeng.com/2016/12/28/%E7%90%86%E8%A7%A3HashMap/
key/value都可以为空。线程不安全。
到java1.8之后，hashmap的存储结构由数组+链表 变成了数组+红黑树。
##### HashTable
key/value都不可以为空。线程安全。
hash = hash(key)
临界阈值，hashMap元素数量达到这个值会进行扩容。
 threshold = hash数组的长度 * loadFactor
装载因子 loadFactor
##### CurrentHashMap
https://www.ibm.com/developerworks/cn/java/java-lo-concurrenthashmap/index.html
segment 是锁 下面有若干了桶，每个桶都是有若干了HashEntry连起来的。
HashEntry 是对node节点的封装
key  hash、next域 都是final
value 是volatile
默认并发级别 DEFAULT_CURRENT_LEVEL = 16
segmentMask 掩码 根据key的散列码的高位来选择具体的segment
segmentShift 偏移量
segment[(hash >>> segmentShift) & segmentMask]




