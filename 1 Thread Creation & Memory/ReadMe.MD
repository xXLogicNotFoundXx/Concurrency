
https://www.youtube.com/watch?v=LCSqZyjBwWA&list=RDCMUCkiRZdcuNF7aiT4sQ9MJt-Q&index=2

Every time thread is created thread stack contains the local variable. Anytime you create an object it is created in heap and reference of that object is stored in treadstack. 

![](https://github.com/xXLogicNotFoundXx/Concurrency/blob/main/1%20Thread%20Creation%20%26%20Memory/img/ThreadMem.png)

This is more like this is how the actual work inside. 

![](https://github.com/xXLogicNotFoundXx/Concurrency/blob/main/1%20Thread%20Creation%20%26%20Memory/img/ThreadMem1.png)

## volatile
![](https://github.com/xXLogicNotFoundXx/Concurrency/blob/main/1%20Thread%20Creation%20%26%20Memory/img/volatile.png)

volatile makes sure the write flushes the values in local copy to the main memory and then they are visible to other threads.\
However, if you write the **synchronize** block java gaurantees the those values are flushed to main memroy and visible to other threads.

![](https://github.com/xXLogicNotFoundXx/Concurrency/blob/main/1%20Thread%20Creation%20%26%20Memory/img/synchronize.png)
 
