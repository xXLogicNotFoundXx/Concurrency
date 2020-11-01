# Collections 

## Synchronized Collections
** Collections class provides static synchronization wrappers that provides synchronized (thread-safe) collections.

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
If you want to iterate over the 

## Concurrent Collections
