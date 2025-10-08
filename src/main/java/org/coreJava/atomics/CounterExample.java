package org.coreJava.atomics;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Ahmad Buba
 * Date: 10/6/25
 */

public class CounterExample {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        /* Thread-safe increments */
        int newValue = counter.incrementAndGet();
        System.out.println("Counter now = " + newValue);
    }

}
