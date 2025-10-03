package org.coreJava;


/**
 * Author: Ahmad Buba
 * Date: 9/29/25
 */


/*
* Demonstrates the instance method: isInterrupted(): which only checks whether
* the thread has been interrupted.
* initially starts by printing the first statement, then
* it enters the loop and continues within the loop so long as
* the thread has not been interrupted: ie printing each 5 seconds
* then after 15 seconds the main thread sleeps
* . Right after the main thread sleeps, we interrupt the worker thread
* which causes the worker thread to terminate and set the status to false
* thereby breaking out of the loop and printing the exit statement
* */
public class IsInterruptedExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(()-> {
//            System.out.println(Thread.currentThread().getName() + "interrupted status: " + Thread.currentThread().isInterrupted());
           while(!Thread.currentThread().isInterrupted()) {
               System.out.println("Working");
               try {
                   Thread.sleep(500); /* simulate work */
               } catch (InterruptedException e) {
                   System.out.println("Interrupted during sleep");
                   Thread.currentThread().interrupt();
               }
           }
           System.out.println("Thread status after interruption " + Thread.currentThread().isInterrupted());
           System.out.println("Thread exiting gracefully");
        });
        worker.start();
        Thread.sleep(2000);
        worker.interrupt();
    }
}
