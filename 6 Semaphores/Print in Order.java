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

/*
As these Semaphores are intiated with 0. 
They dont have any permit and threads will wait until there is a permit.
first() -> give permit to run2 i.e.  to second() method. 
second() -> give permit to run3 i.e. to third() method. 

*/
class Foo {

    Semaphore run2, run3;
    public Foo() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.aquire();
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.aquire();
        
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
