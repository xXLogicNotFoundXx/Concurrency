## Race Condition
When the result of multiple threads executing a critical section may differ depending on the sequence in which the threads execute, the critical section is said to contain a race condition.\
The term race condition stems from the metaphor that the threads are racing through the critical section, and that the result of that race impacts the result of executing the critical section.

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
 
## Synchronized Methods
When a thread invokes a synchronized method, it automatically acquires the intrinsic lock for that method's **object** and releases it when the method returns. The lock release occurs even if the return was caused by an uncaught exception.\
This is useful when more than one threads have an acess to same object.\
Constructors can not be synchronized.\
In case of static method, the thread acquires the intrinsic lock for the Class object associated with the class.\
Thus access to class's static fields is controlled by a lock that's distinct from the lock for any instance of the class.\
READ : https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

```
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }
}
```
## Intrinsic Locks and Synchronization Ststements 
* Every object has an intrinsic lock or monitor lock associated with it. You can acquire lock on any object with synchronized(object). 
https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
```
public void addName(String name) {
    synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}
```
Synchronized statements are also useful for improving concurrency with fine-grained synchronization.\
For eg if you have two operations never used together. Instead of using synchronized methods or otherwise using the lock associated with this, we create two objects solely to provide locks.
```
public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }
}
```
### Reentrant Synchronization
A thread can acquire a lock that it already owns. Allowing a thread to acquire the same lock more than once enables reentrant synchronization. This describes a situation where synchronized code, directly or indirectly, invokes a method that also contains synchronized code, and both sets of code use the same lock. Without reentrant synchronization, synchronized code would have to take many additional precautions to avoid having a thread cause itself to block.
