## Atomic Operation 
An operation during which a processor can simultaneously read a location and write it in the same bus operation. 
This prevents any other processor or I/O device from writing or reading memory until the operation is complete.

READ : 
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html
* https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html

## Atomic Variables
* The java.util.concurrent.atomic package defines classes that support atomic operations on single variables. 
* All classes have get and set methods that work like reads and writes on volatile variables. 
* That is, a set has a **happens-before relationship with any subsequent get on the same variable**. (Almost like locks-> atomic arithmetic -> unlock)
* The atomic compareAndSet method also has these memory consistency features, as do the simple **atomic arithmetic** methods that apply to integer atomic variables.

#### Some Classes 
**AtomicBoolean	AtomicInteger	AtomicIntegerArray	AtomicLong	AtomicLongArray**

#### Happens-Before
* Language Specification defines the happens-before relation on memory operations such as reads and writes of shared variables. 
* The results of a write by one thread are guaranteed to be visible to a read by another thread only if the write operation happens-before the read operation. 
* The synchronized and volatile constructs can form happens-before relationships.
* Read: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility
 
So, instead of having an **int** variable and having **synchronised** methods to get and set we can just use atomic variables. 
```
import java.util.concurrent.atomic.AtomicInteger;
class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }
}
```
