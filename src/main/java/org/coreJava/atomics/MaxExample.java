package org.coreJava.atomics;


import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: Ahmad Buba
 * Date: 10/6/25
 */

public class MaxExample {

    private static final AtomicLong largest = new AtomicLong(0);

    public static void main(String[] args) {
        long observed = 10;

        /* Atomically update largest value */
        largest.updateAndGet(x -> Math.max(x, observed));

        System.out.println("Largest now = " + largest.get());
    }

}
