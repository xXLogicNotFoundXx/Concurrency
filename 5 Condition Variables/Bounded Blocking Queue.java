/*
1188. Design Bounded Blocking Queue
https://leetcode.com/problems/design-bounded-blocking-queue/

Implement a thread safe bounded blocking queue that has the following methods:

BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
int size() Returns the number of elements currently in the queue.

Your implementation will be tested using multiple threads at the same time. 
Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.
*/

class BoundedBlockingQueue {
    int []data;
    int size;
    int putptr=0, getptr=0;
    
    Lock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();
    
    public BoundedBlockingQueue(int capacity) {
        
        data = new int[capacity];
    }
    
    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(data.length == size){
                full.await();
            }
            data[putptr] = element;
            putptr = (putptr+1) % data.length;
            size++;
            empty.signal(); // if there is one waiting tell that one to get this item 
            // IMP 
            // if you call empty.notify that means you are saying whowhever waiting on lock of 'empty' object notify them
            // BTW technically you can lock Condition object But dont use condition object for lock object. 
            // Signal is for threads waiting on a Condition 
        } finally {
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(size==0){
                empty.await();
            }
            int a = data[getptr];
            getptr = (getptr+1) % data.length;
            size--;
            full.signal();
            return a;
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        lock.lock();
        try{   
            return size;
        } finally {
            lock.unlock();
        }
    }
}
*/

/*
Following works but not appropriate usage of locks.
Mutex are used for mutual exclusion i.e to safe guard the critical sections of a code.
Semaphores/Condition variables are used for thread synchronization.

We are using lock and notifyall on it. That wakes up all the threads. 
So you would unnecessarily wake threads that can't do anything anyway.
For proper handshake/synchronization we should use condition variables to wait on when queue is full and when queue is empty.

*/
class BoundedBlockingQueue {
    Queue<Integer> q = new LinkedList<Integer>();
    int capacity;
    
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }
    
    public synchronized void enqueue(int element) throws InterruptedException {
        while(q.size() >= capacity){
            this.wait();
        }
        q.add(element);
        // Wake up the threads. 
        this.notifyAll();
    }
    
    public synchronized int dequeue() throws InterruptedException {
        int val =0;
        
        while(q.size() == 0){
            this.wait();
        }
        val = q.remove();
        this.notifyAll();

        return val;
    }
    
    public synchronized int size() {
        return q.size();
    }
}
