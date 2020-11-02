
### Semaphore
A counting semaphore. Conceptually, a semaphore maintains a set of permits. Each acquire() blocks if necessary until a permit is available, and then takes it.\
Each release() adds a permit, potentially releasing a blocking acquirer. However, no actual permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly.\
Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource. 
```
// All waiting threads are granted a permit in an undefined order. With this starvation of a thread could happen 
Semaphore(int num)  


// By setting how to true, you can ensure that waiting threads are granted a permit in the order in which they requested access.
Semaphore(int num, boolean how) 

// Your semaphore initially has 0 permits, in which case permits must be released before they may be acquired.
new Semaphore(0); 
```
Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time.\
If it is one, then only one thread can access the resource at any one time. 


"Semaphore is a bowl of marbles"
If you need a marble, and there are none, you wait.\
You wait until there is one marble and then you take it. If you release(), you will add one marble to the bowl (from thin air).\
If you release(100), you will add 100 marbles to the bowl (from thin air).

### Semaphore Vs Mutex
* Mutex are used for mutual exclusion i.e to safe gaurd the critical sections of a code.
* Semaphone/condition_variable are used for thread synchronisation(which is what we want to achieve here).
* Mutex have ownership assigned with them, that is to say, the thread that locks a mutex must only unlock it. Also, we must not unlock a mutex that has not been locked (This is what most programs have got wrong).
* If the mutex is not used as said above, the behavior is undefined.


https://www.falkhausen.de/Java-8/java.util/concurrent/Phaser_-Semaphore.html
