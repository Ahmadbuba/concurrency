package org.coreJava.threadLocalVariables;

/**
 * Demonstrates ThreadLocalRandom.
 * Each thread automatically gets its own Random generator.
 * No blocking, much faster under load.
 */

import java.util.concurrent.ThreadLocalRandom;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class ThreadLocalRandomExample {

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    int n = ThreadLocalRandom.current().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " -> " + n);
                }
            }, "Thread-" + i);
            t.start();
        }
    }

}
