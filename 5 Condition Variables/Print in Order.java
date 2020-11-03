/*
https://leetcode.com/problems/print-in-order/
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. 
Thread A will call first(), thread B will call second(), and thread C will call third(). 
Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().


Input: [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. 
The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
Example 2:

Input: [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
*/

class Foo {
    final Lock lock = new ReentrantLock();
    final Condition cond2  = lock.newCondition(); 
    final Condition cond3  = lock.newCondition(); 
    volatile int count=1;
    
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run(); // printFirst.run() outputs "first". Do not change or remove this line.
            count++;
            cond2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while(count!=2){
                // waiting on the condition backed by same lock 
                cond2.await(); 
            }

            printSecond.run(); // printSecond.run() outputs "second". Do not change or remove this line.
            count++;
            cond3.signal();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while(count!=3){
                // waiting on the condition backed by same lock 
            }
            printThird.run(); // printThird.run() outputs "third". Do not change or remove this line.
        } finally {
            lock.unlock();
        }
    }
}
