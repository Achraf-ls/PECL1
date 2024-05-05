/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class LoggerA implements Serializable{

    private String archivoLog;

    public LoggerA(String archivoLog) {
        this.archivoLog = archivoLog;
    }

    public synchronized void logEvent(String event, String aeropuerto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoLog, true))) {
            String tiempo = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            String entrada = String.format("[%s] %s\n %s\n", tiempo, aeropuerto, event);
            writer.write(entrada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
