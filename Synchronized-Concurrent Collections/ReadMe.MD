# Collections 

## Synchronized Collections
* **Collections class provides static synchronization wrappers that provides synchronized (thread-safe) collections.**
* Synchronized Collections are not optimized for concurrent access. Only One thread can access sunchronized collection at a time.

Read : https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-

* static <T> Collection<T>	**synchronizedCollection(Collection<T> c)** - Returns a synchronized (thread-safe) collection backed by the specified collection. 
* static <T> List<T>	**synchronizedList(List<T> list)** - Returns a synchronized (thread-safe) list backed by the specified list.
* static <K,V> Map<K,V>	**synchronizedMap(Map<K,V> m)** - Returns a synchronized (thread-safe) list backed by the specified map.
* static <K,V> NavigableMap<K,V>	**synchronizedNavigableMap(NavigableMap<K,V> m)**
* static <T> NavigableSet<T>	**synchronizedNavigableSet(NavigableSet<T> s)**
* static <T> Set<T>	**synchronizedSet(Set<T> s)** 
* static <K,V> SortedMap<K,V>	**synchronizedSortedMap(SortedMap<K,V> m)** 
* static <T> SortedSet<T>	**synchronizedSortedSet(SortedSet<T> s)** 

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
* Concurrent collections (e.g. ConcurrentHashMap), achieve thread-safety by dividing their data into segments. 
* In a ConcurrentHashMap, for example, different threads can acquire locks on different segment, so multiple threads can access the Map at the same time (a.k.a. concurrent access). Think of it as map is divided into parts and each part has its own lock. 
* Concurrent collections are much more performant than synchronized collections, due to the inherent advantages of concurrent thread access.

READ : https://docs.oracle.com/javase/tutorial/essential/concurrency/collections.html

#### Some Inteface/Classes 
* BlockingQueue defines a first-in-first-out data structure that blocks or times out when you attempt to add to a full queue, or retrieve from an empty queue.
* ConcurrentMap is a subinterface of java.util.Map that defines useful atomic operations.
* The standard general-purpose implementation of ConcurrentMap is ConcurrentHashMap, which is a concurrent analog of HashMap.
* ConcurrentNavigableMap is a subinterface of ConcurrentMap that supports approximate matches. 
* The standard general-purpose implementation of ConcurrentNavigableMap is ConcurrentSkipListMap, which is a concurrent analog of TreeMap.

All of these collections help avoid Memory Consistency Errors by defining a happens-before relationship between an operation that adds an object to the collection with subsequent operations that access or remove that object.

