# Concurrency

## Good Read/Watch
* https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
* ConcurrentHashMap and BlockingQueues : https://www.youtube.com/watch?v=Ur3hjgbpVXQ

## Race Condition
When the result of multiple threads executing a critical section may differ depending on the sequence in which the threads execute, the critical section is said to contain a race condition.\
The term race condition stems from the metaphor that the threads are racing through the critical section, and that the result of that race impacts the result of executing the critical section.

## Liveness
A concurrent application's ability to execute in a timely manner is known as its liveness. The most common kind of liveness problem, **deadlock**, and  two other liveness problems, **starvation and livelock**.


### Deadlock
Deadlock describes a situation where two or more threads are blocked forever, waiting for each other.

### Starvation
Starvation describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress. This happens when shared resources are made unavailable for long periods by "greedy" threads. For example, suppose an object provides a synchronized method that often takes a long time to return. If one thread invokes this method frequently, other threads that also need frequent synchronized access to the same object will often be blocked.

### Livelock
A thread often acts in response to the action of another thread. If the other thread's action is also a response to the action of another thread, then livelock may result. As with deadlock, livelocked threads are unable to make further progress. However, the threads are not blocked — they are simply too busy responding to each other to resume work.
