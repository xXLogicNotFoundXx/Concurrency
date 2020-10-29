## Mutex and Locks
Mutex and Locks are same (Mutex is short form of **Mut**ual **Ex**clusion) - https://en.wikipedia.org/wiki/Lock_(computer_science)

In Java simple locking mechnism can be used by *synchronized*. 
The Java programming language provides two basic synchronization idioms: synchronized methods and synchronized statements. 
Synchronization is built around an internal entity known as the intrinsic lock or monitor lock.


### java.util.concurrent.locks.Lock Inteface READ : https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Lock.html
All Known Implementing Classes: **ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock**

The use of synchronized methods or statements provides access to the implicit monitor lock associated with every object, but forces all lock acquisition and release to occur in a block-structured way: when multiple locks are acquired they must be released in the opposite order, and all locks must be released in the same lexical scope in which they were acquired.

While the scoping mechanism for synchronized methods and statements makes it much easier to program with monitor locks, and helps avoid many common programming errors involving locks, there are occasions where you need to work with locks in a more flexible way. For example, some algorithms for traversing concurrently accessed data structures require the use of "hand-over-hand" or "chain locking": you acquire the lock of node A, then node B, then release A and acquire C, then release B and acquire D and so on. Implementations of the Lock interface enable the use of such techniques by allowing a lock to be acquired and released in different scopes, and allowing multiple locks to be acquired and released in any order.

## Race Condition
When the result of multiple threads executing a critical section may differ depending on the sequence in which the threads execute, the critical section is said to contain a race condition.\
The term race condition stems from the metaphor that the threads are racing through the critical section, and that the result of that race impacts the result of executing the critical section.
