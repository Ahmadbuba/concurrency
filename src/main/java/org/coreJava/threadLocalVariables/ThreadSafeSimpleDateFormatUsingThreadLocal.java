package org.coreJava.threadLocalVariables;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class ThreadSafeSimpleDateFormatUsingThreadLocal {

    /* Each thread will have its own instance of SimpleDateFormat */
    private static final ThreadLocal<SimpleDateFormat> formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    /* Each thread safely uses its own formatter instance */
                    String formatted = formatter.get().format(new Date());
                    Date parsed = formatter.get().parse("2025-10-07");
                    System.out.println(Thread.currentThread().getName() +
                            " formatted: " + formatted + " parsed: " + parsed);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        /* Start multiple threads using the same ThreadLocal formatter */
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }

}
