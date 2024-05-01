/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author achra
 */
public class Logger {

    private final String LOG_FILE;

    public Logger(String logFile) {
        this.LOG_FILE = logFile;
    }

    public synchronized void logEvent(String source, String event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logEntry = String.format("[%s] %s: %s\n", timestamp, source, event);
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
