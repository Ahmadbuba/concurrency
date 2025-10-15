package org.coreJava.threadLocalVariables;

/**
 * Demonstrates the problem with sharing one Random instance among many threads.
 * Random is thread-safe (internally synchronized), but threads block each other.
 */

import java.util.Random;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class SharedRandomExample {
    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    int n = random.nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " -> " + n);
                }
            }, "Thread-" + i);
            t.start();
        }
    }
}
