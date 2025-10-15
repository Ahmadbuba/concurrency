/*
* Issue with SimpleDateFormat class demonstrated
* in multi threaded environment: it gets corrupted
* due to the shared internal objects being used
* */

package org.coreJava.threadLocalVariables;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class SimpleDateFormatThreadSafetyIssueDemo {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(sdf.parse("2025-10-07"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
