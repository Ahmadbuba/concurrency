package org.coreJava.threadLocalVariables;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: Ahmad Buba
 * Date: 10/13/25
 */

public class ThreadLocalLeakDemo {

    /* Simulate a database connection object */
    static class FakeConnection {
        private final String name;

        FakeConnection(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Connection{" + name + '}';
        }
    }

    /* ThreadLocal that holds one connection per thread */
    static ThreadLocal<FakeConnection> connectionHolder =
            ThreadLocal.withInitial(() -> new FakeConnection(Thread.currentThread().getName()));

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Runnable unsafeTask = () -> {
            /* gets thread’s own connection */
            FakeConnection conn = connectionHolder.get();
            System.out.println(Thread.currentThread().getName() + " using " + conn);
        };

        System.out.println("===== Running UNSAFE version =====");
        for (int i = 0; i < 5; i++) {
            pool.submit(unsafeTask);
        }

        sleep(2000);

        System.out.println("\n===== Running SAFE version =====");
        Runnable safeTask = () -> {
            FakeConnection conn = connectionHolder.get();
            try {
                System.out.println(Thread.currentThread().getName() + " using " + conn);
            } finally {
                /* crucial cleanup */
                connectionHolder.remove();
            }
        };

        for (int i = 0; i < 5; i++) {
            pool.submit(safeTask);
        }

        pool.shutdown();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
