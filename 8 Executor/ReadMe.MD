## Problem : 
* Let say you create a thread-per-task (task could be anything HTML request you want to handle or a blocking call in your application).
* Thread objects use a significant amount of memory, and in a large-scale application, allocating and deallocating many thread objects creates a significant memory management overhead.
* This will result into thread explosion and your system will crash if you get too many tasks at once.
* Trying to spawn threads and manage their lifecycle yourself, makes it complicated and concurrent Programming becomes spaghetti nightmare code. 

## Solution
* We need to limit the number of threads being created.
* We should be able to use same thread for different task (avoiding creating and teardown of threads).
* With a limit on the number of the threads that can be created, the application will not be servicing HTTP requests as quickly as they come in, but it will be servicing them as quickly as the system can sustain.

## Solution : Thread Pool
* To achieve the solution we have "Thread pools". 
* It is a concept where we can specify maximum number of threads can exist in the system.
* These thread are reusable and accept a new task from the queue when they are done with current task.
![](https://media.geeksforgeeks.org/wp-content/uploads/tpinit.jpg)

## Java Thread Pool
* Java provides its own implementations of the thread pool pattern through objects called Executors which is part of Executor framework. 
* Class Executors provide differnt factory methods to create Thread Pools in java. 
  * newFixedThreadPool: A fixed-size thread pool creates threads as tasks are submitted, up to maximum pool size, and then attempts to keep the pool size constant (adding new threads if a thread dies due to an unexpected exception).
  * newCachedThreadPool: A cached thread pool has more flexibility to reap idle threads when the current size of the pool exceeds the demand for processing, and to add new threads when demand increases, but places no bounds on the size of the pool.
  * newSingleThreadExecutor: A single-threaded executor creates a single worker thread to process tasks, replacing it if it dies unexpectedly. Tasks are guaranteed to be processed sequentially according to the order imposed by the task queue (FIFO, LIFO, priority order).
  * newScheduledThreadPool: A fized-size thread pool that supports delayed and periodic task execution, similar to Timer.
  
  
### Most common is FixedThreadPool
* Without looking more into Executor framework... here is the example to create fixed number of threads and using those threads for multiple tasks.
* Note: There are only two threads created : pool-1-thread-1 & pool-1-thread-2 in following example. 

```
// Print : <thread-name> START - sleep - <thread-name> END
class Task implements Runnable{
  String str;
  Task(String str){
      this.str=str;
  }
  public void run(){
      System.out.println(Thread.currentThread().getName() + " "+str + " - START");
      try{
          Thread.sleep(1000);
      }catch(InterruptedException e) { 
          e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " "+str + " - END");
  }
}

public static voi main(){
....
  ExecutorService pool = Executors.newFixedThreadPool(2);   // we are asking maximum two threads to be created.
  for(int i=1;i<=5;i++){
      pool.execute(new Task("Task "+i));  // or you can use submit() method       
  }
  pool.shutdown(); // telling ExecutorService to shutdown all threads
....
}


O/P: 
pool-1-thread-1 Task 1 - START
pool-1-thread-2 Task 2 - START
pool-1-thread-1 Task 1 - END          // pool-1-thread-1 completes the Task 1 
pool-1-thread-1 Task 3 - START        // pool-1-thread-1 now picks up the Task 3  
pool-1-thread-2 Task 2 - END
pool-1-thread-2 Task 4 - START
pool-1-thread-1 Task 3 - END
pool-1-thread-1 Task 5 - START
pool-1-thread-2 Task 4 - END
pool-1-thread-1 Task 5 - END
```

### How Thread Pool works? 
* In java you don't have to implement your own thread pool. Still it can be useful to know a bit about the implementation of a thread pool anyways.
* http://tutorials.jenkov.com/java-concurrency/thread-pools.html


## READ
* https://www.geeksforgeeks.org/thread-pools-java/?ref=lbp 
* https://docs.oracle.com/javase/tutorial/essential/concurrency/pools.html
* More about executor : https://www.thealgorists.com/Concurrency/EasyParallelization
* https://leetcode.com/problems/web-crawler-multithreaded/discuss/886130/Efficient-Solution-using-ExecutorService-Callable-and-Future-(Runtime%3A-16-ms)

