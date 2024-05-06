/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

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
public class LoggerA implements Serializable {

    private String archivoLog;

    // Constructor que toma el nombre del archivo de registro como parámetro
    public LoggerA(String archivoLog) {
        this.archivoLog = archivoLog;
    }

    // Método para registrar un evento en el archivo de registro
    // El método es sincronizado para evitar problemas de concurrencia si múltiples hilos acceden a este registrador
    public synchronized void logEvent(String event, String aeropuerto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoLog, true))) {
            // Obtener la marca de tiempo actual
            String tiempo = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            // Formatear la entrada del registro con la marca de tiempo, el aeropuerto y el evento
            String entrada = String.format("[%s] %s\n %s\n", tiempo, aeropuerto, event);
            // Escribir la entrada en el archivo de registro
            writer.write(entrada);
        } catch (IOException e) {
            // Manejo de excepciones en caso de error al escribir en el archivo de registro
            e.printStackTrace();
        }
    }

}
