package org.coreJava.threadLocalVariables;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Ahmad Buba
 * Date: 10/9/25
 */

public class SimpleDateFormatSingleThreadExample {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringFormattedDated = sdf.format(new Date());
        Date date = sdf.parse(stringFormattedDated);
        System.out.println("String formatted version of today's date: " + stringFormattedDated);
        System.out.println("Date formatted version of today's date: " + date);
    }
}
