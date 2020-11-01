
## Mutex and Locks
Mutex and Locks are same (Mutex is short form of **Mut**ual **Ex**clusion) - https://en.wikipedia.org/wiki/Lock_(computer_science)

In Java simple locking mechnism can be used by *synchronized*. 
The Java programming language provides two basic synchronization idioms: synchronized methods and synchronized statements. 
Synchronization is built around an internal entity known as the intrinsic lock or monitor lock.


#### java.util.concurrent.locks.Lock Inteface 
#### READ : https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Lock.html
All Known Implementing Classes: **ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock**

The use of synchronized methods or statements provides access to the implicit monitor lock associated with every object, but forces all lock acquisition and release to occur in a block-structured way: when multiple locks are acquired they must be released in the opposite order, and all locks must be released in the same lexical scope in which they were acquired.

While the scoping mechanism for synchronized methods and statements makes it much easier to program with monitor locks, and helps avoid many common programming errors involving locks, there are occasions where you need to work with locks in a more flexible way. For example, some algorithms for traversing concurrently accessed data structures require the use of "hand-over-hand" or "chain locking": you acquire the lock of node A, then node B, then release A and acquire C, then release B and acquire D and so on. Implementations of the Lock interface enable the use of such techniques by allowing a lock to be acquired and released in different scopes, and allowing multiple locks to be acquired and released in any order.

With this increased flexibility comes additional responsibility. The absence of block-structured locking removes the automatic release of locks that occurs with synchronized methods and statements. In most cases, the following idiom should be used:
```
     Lock l = ...;
     l.lock();
     try {
         // access the resource protected by this lock
     } finally {
         l.unlock();
     }
```  

We can use Lock if you want to do something else if we can't acquire a Lock. 
```
Example : boolean tryLock() 

      Lock lock = ...;
      if (lock.tryLock()) {
          try {
              // manipulate protected state
          } finally {
              lock.unlock();
          }
      } else {
          // perform alternative actions
      }
```

### Reentrant Lock
A thread can acquire a lock that it already owns. Allowing a thread to acquire the same lock more than once enables reentrant synchronization. This describes a situation where synchronized code, directly or indirectly, invokes a method that also contains synchronized code, and both sets of code use the same lock. Without reentrant synchronization, synchronized code would have to take many additional precautions to avoid having a thread cause itself to block.


