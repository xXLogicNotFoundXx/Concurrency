class BoundedBlockingQueue {
    /* with queue implementation it becomes easy */
    Queue<Integer> queue = new LinkedList<>();
    int capacity;
    
    Lock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();
    
    public BoundedBlockingQueue(int capacity) {
        
        this.capacity = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == capacity){
                full.await();
            }
            queue.add(element);
            empty.signal(); // if there is one waiting on empty.. tell that one to get this item 
        } finally {
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(queue.size()==0){
                empty.await();
            }
            int a = queue.poll();
            full.signal(); // if there is one waiting on full.. tell that one to now add one item 
            return a;
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        lock.lock();
        try{   
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}

// Circular Bounded blocking Queue 
class BoundedBlockingQueue1 {
    int []data;
    int size;
    int putptr=0, getptr=0;
    
    Lock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();
    
    public BoundedBlockingQueue1(int capacity) {
        
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



/*
This works but not appropriate usage of locks.
Mutex are used for mutual exclusion i.e to safe guard the critical sections of a code.
Semaphores/Condition variables are used for thread synchronization.

We are using lock and notifyall on it. That wakes up all the threads. 
So you would unnecessarily wake threads that can't do anything anyway.
For proper handshake/synchronization we should use condition variables to wait on when queue is full and when queue is empty.


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
*/
