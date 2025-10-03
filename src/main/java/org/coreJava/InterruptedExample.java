package org.coreJava;


/**
 * Author: Ahmad Buba
 * Date: 9/30/25
 */

/*
* Demonstrates the static method: interrupted()
* we initially start by interrupting a clean thread
* we then check using: interrupted() which checks and clears the status
* so the moment we check it will return true then clear the interrupt status
* so when check again the status is false since the status has been cleared
*  */
public class InterruptedExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
           /* Simulate being interrupted before we check */
            Thread.currentThread().interrupt();

            System.out.println("First check: " + Thread.interrupted()); /* true */
            System.out.println("Second check: " + Thread.interrupted()); /* false */

        });
        worker.start();
    }
}
