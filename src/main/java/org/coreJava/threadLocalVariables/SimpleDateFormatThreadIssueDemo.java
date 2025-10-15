/**
 * Demonstrates that SimpleDateFormat is not thread-safe.
 *
 * When multiple threads call format() or parse() concurrently,
 * they share the same internal Calendar instance inside SimpleDateFormat.
 *
 * One thread may set the internal calendar (e.g., to October 2025),
 * while another thread updates it (e.g., to January 2023) before
 * the first thread finishes reading it.
 *
 * This results in corrupted or nonsensical date outputs.
 */

package org.coreJava.threadLocalVariables;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */


public class SimpleDateFormatThreadIssueDemo {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Thread t1 = new Thread(() -> {
            System.out.println(sdf.format(new Date()));
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(sdf.parse("2025-10-07"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }
}
