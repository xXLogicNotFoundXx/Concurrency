
* Every time thread is created 'thread stack' contains the local variable like int,long etc. 
* Anytime you create an object it is created in heap and reference of that object is stored in treadstack. 

## Basic problem of Mememory visibility ( before lock, semaphores etc)
Each thread can be on a different core with its own private registers which Java can use to hold values of variables, unless you force access to coherent shared memory. This means that one thread can write to a value storing in a register, and this value is not visible to another thread for some time, like the duration of a loop or whole function. (milli-seconds is not uncommon)

A more extreme example is that the reading thread's code is optimised with the assumption that since it never changes the value, it doesn't need to read it from memory. In this case the optimised code never sees the change performed by another thread.


## volatile

volatile makes sure the the changes are visible to other threads. ( I think of it as write flushes the values in local copy(regirster,or cache) to the main memory)\
However, if you write the **synchronize** block java gaurantees the those values are flushed to main memroy and visible to other threads too. So you dont have to worry about the variables in synchronization blocks. 

## Volitile adhere to happens-before relations.(No Locks are involved) 

Dont confuse volatile operations are atomic though. The effect of the volatile keyword is approximately that each individual read or write operation on that variable is atomic.

Notably, however, an operation that requires more than one read/write -- such as i++, which is equivalent to i = i + 1, which does one read and one write -- is not atomic, **since another thread may write to i between the read and the write.**

The Atomic classes, like AtomicInteger and AtomicReference, provide a wider variety of operations atomically, specifically including increment for AtomicInteger.

So volatile solves the visibility problem not atomicity. 

## Thread Class 
```
public class Thread extends Object implements Runnable {
  ...
}

Methods inherited from class java.lang.Object
equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
```
## Thread.sleep()  [static method]
* This doesnt unlock any aquired locks. You should use wait() instead.
* While sleeping Parent thread may interrupt the thread so sleep() can throw InterruptedException. Always use try catch for that. 
```
catch (InterruptedException e) { 
  ...
}
```

## thread.wait()
* Unclocks the aquired lock. wait() can throw InterruptedException.


## Deamon thread.
t.setDaemon(true):
* They can not prevent the JVM from exiting when all the user threads finish their execution.
* JVM terminates itself when all user threads finish their execution
* If JVM finds running daemon thread, it terminates the thread and after that shutdown itself. JVM does not care whether Daemon thread is running or not.
* It is an utmost low priority thread.


