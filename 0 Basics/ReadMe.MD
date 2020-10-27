## Mutex and Locks
Mutex and Locks are same (Mutex is short form of **Mut**ual **Ex**clusion) - https://en.wikipedia.org/wiki/Lock_(computer_science)

In Java simple locking mechnism can be used by *synchronized*. 
https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Lock.html

### java.util.concurrent.locks.Lock Inteface 
All Known Implementing Classes: **ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock**


## Race Condition
When the result of multiple threads executing a critical section may differ depending on the sequence in which the threads execute, the critical section is said to contain a race condition.\
The term race condition stems from the metaphor that the threads are racing through the critical section, and that the result of that race impacts the result of executing the critical section.
