/*
* immutable, thread-safe
* */

package org.coreJava.threadLocalVariables;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class ThreadSafeDateFormatter {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String s = fmt.format(LocalDate.now());
        LocalDate date = LocalDate.parse("2025-10-07", fmt);
        System.out.println("String formatted today: " + s);
        System.out.println("Date formatted today: " + date);

    }
}
