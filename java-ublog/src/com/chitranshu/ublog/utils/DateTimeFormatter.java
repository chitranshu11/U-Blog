package com.chitranshu.ublog.utils;

import java.time.LocalDateTime;

public class DateTimeFormatter {
    public static String format(LocalDateTime localDateTime) {
        String[] token = localDateTime.toString().split("T");
        String[] datetoken = token[0].split("-");

        String date = datetoken[2] + "-" + datetoken[1] + "-" + datetoken[0];

        String time = token[1].substring(0, 8);

        return date + " " + time;

    }

    public static void main(String[] args) {
        System.out.println(DateTimeFormatter.format(LocalDateTime.now()));
    }
}
