
 
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
## Intrinsic Locks (monitor lock) and Synchronization Ststements 
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
