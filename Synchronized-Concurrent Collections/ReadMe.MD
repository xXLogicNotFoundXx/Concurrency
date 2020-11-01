# Collections 

## Synchronized Collections
* **Collections class provides static synchronization wrappers that provides synchronized (thread-safe) collections.**
* Synchronized Collections are not optimized for concurrent access. Only One thread can access sunchronized collection at a time.

Read : https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-

#### Some Synchronized Collections
* **Collections.synchronizedCollection(Collection<T> c)** - Returns a synchronized (thread-safe) collection backed by the specified collection. 
* **Collections.synchronizedList(List<T> list)** - Returns a synchronized (thread-safe) list backed by the specified list.
* **Collections.synchronizedMap(Map<K,V> m)** - Returns a synchronized (thread-safe) list backed by the specified map.
* **Collections.synchronizedNavigableMap(NavigableMap<K,V> m)**
* **Collections.synchronizedNavigableSet(NavigableSet<T> s)**
* **Collections.synchronizedSet(Set<T> s)** 


So, add(), addAl() etc methods are atomic and thread safe. Only one lock is associated with returned collection. 
If you want to iterate over the collection then you are supposed to lock that collection object.

It is imperative that the user manually synchronize on the returned map when iterating over any of its collection views:
```
  List list = Collections.synchronizedList(new ArrayList());
      ...
  synchronized (list) {
      Iterator i = list.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
  *******************************
  Map m = Collections.synchronizedMap(new HashMap());
      ...
  Set s = m.keySet();  // Needn't be in synchronized block
      ...
  synchronized (m) {  // Synchronizing on m, not s!
      Iterator i = s.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```
Failure to follow this advice may result in non-deterministic behavior.\
The returned map will be serializable if the specified map is serializable.

## Concurrent Collections
**We are not doing any synchronization here. The purpose is to make write operations atomic an does happens-before relationship.**

* The differences between them come in performance, scalability and how they achieve thread-safety.
* Concurrent collections are much more performant than synchronized collections, due to the inherent advantages of concurrent thread access.

#### ConcurrentHashMap
* The default concurrency-level of ConcurrentHashMap is 16.
* The ConcurrentHashMap divides the whole map into several segments and locks only the relevant segments, which allows multiple threads to access other segments of the same ConcurrentHashMap without locking. (a.k.a. concurrent access)
* In ConcurrentHashMap, at a time any number of threads can perform retrieval operation but for updation in the object, the thread must lock the particular segment in which the thread wants to operate. This type of locking mechanism is known as Segment locking or bucket locking. Hence at a time, 16 update operations can be performed by threads.

#### CopyOnWriteArrayList
* Similarly, CopyOnWriteArrayList allows multiple reader threads to read without synchronization and when a write happens it copies the whole ArrayList and swaps with a newer one.


READ : https://docs.oracle.com/javase/tutorial/essential/concurrency/collections.html

#### Some Intefaces/Classes 
* **BlockingQueue** defines a first-in-first-out data structure that blocks or times out when you attempt to add to a full queue, or retrieve from an empty queue.
* **ConcurrentMap** is a subinterface of java.util.Map that defines useful atomic operations.The standard general-purpose implementation of **ConcurrentMap is ConcurrentHashMap**, which is a concurrent analog of **HashMap**.
* **ConcurrentNavigableMap** is a subinterface of ConcurrentMap that supports approximate matches. The standard general-purpose implementation of **ConcurrentNavigableMap is ConcurrentSkipListMap**, which is a concurrent analog of **TreeMap**.

All of these collections help avoid Memory Consistency Errors by defining a happens-before relationship between an operation that adds an object to the collection with subsequent operations that access or remove that object.

