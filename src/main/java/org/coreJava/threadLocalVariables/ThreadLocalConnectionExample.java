/**
 * Demonstrates how ThreadLocal can be used to give each thread its own
 * independent database connection, avoiding thread-safety issues that
 * occur when multiple threads share a single Connection object.
 */

package org.coreJava.threadLocalVariables;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class ThreadLocalConnectionExample {
    /**
     * ThreadLocal variable that stores one Connection per thread.
     *
     * - Each thread that calls connectionHolder.get() will either:
     *   (a) Retrieve its own existing Connection, or
     *   (b) Create a new one (via the lambda) if none exists yet.
     *
     * - ThreadLocal ensures that each thread’s value is isolated — no sharing.
     */
    private static final ThreadLocal<Connection> connectionHolder =
            ThreadLocal.withInitial(() -> {
                try {
                    /* Ensure the H2 JDBC driver is loaded and registered with DriverManager. */
                    /* Without this, DriverManager.getConnection() may throw "No suitable driver. */
                    Class.forName("org.h2.Driver");

                    /* Create an in-memory H2 database connection. */
                    /* Each thread will get its *own* connection object. */
                    return DriverManager.getConnection("jdbc:h2:mem:testdb");
                } catch (Exception e) {
                    throw new RuntimeException("Error creating DB connection", e);
                }
            });

    public static void main(String[] args) {
        /* Define a task that each thread will run */
        Runnable dbTask = () -> {
            /* Get the current thread’s Connection instance from ThreadLocal */
            Connection conn = connectionHolder.get();

            System.out.println(Thread.currentThread().getName() + " using connection: " + conn);

            /* Do some fake database work... */
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            /* IMPORTANT: Always remove ThreadLocal values when done (especially in thread pools) */
            /* This prevents potential memory leaks when threads are reused. */
            connectionHolder.remove();

            /* Close the connection */
            try {
                conn.close();
                System.out.println(Thread.currentThread().getName() + " connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection for " + Thread.currentThread().getName());
                e.printStackTrace();
            }
        };

        /* Launch a few worker threads that each get their own connection */
        for (int i = 1; i <= 3; i++) {
            new Thread(dbTask, "DB-Worker-" + i).start();
        }
    }
}
