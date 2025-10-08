package org.coreJava.atomics;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Ahmad Buba
 * Date: 10/6/25
 */

public class CompareAndSetExample {

    private static final AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) {
        int expected = 0;
        int newValue = 5;

        if (value.compareAndSet(expected, newValue)) {
            System.out.println("Updated successfully!");
        } else {
            System.out.println("Another thread changed it first.");
        }
    }

}
