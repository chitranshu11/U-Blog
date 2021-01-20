package com.chitranshu.ublog.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    public static void writeLog(String logMessage, String path) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String timeStamp = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String fileName = "logs " + date + ".log";
        String filePath = path + fileName;

        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(timeStamp + "\t" + logMessage);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Log file written to: " + path);
    }

}
