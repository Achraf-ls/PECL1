/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.io.Serializable;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class ControladorHilos implements Serializable {

    private boolean seguirEjecutando;

    // Constructor que inicializa el estado de ejecución como verdadero
    public ControladorHilos() {
        seguirEjecutando = true;
    }

    // Método para detener la ejecución de los hilos
    public synchronized void detener() {
        seguirEjecutando = false;
    }

    // Método para reanudar la ejecución de los hilos
    public synchronized void reanudar() {
        seguirEjecutando = true;
        // Notificar a todos los hilos que estén esperando
        notifyAll();
    }

    // Método para verificar si la ejecución está en curso
    public synchronized boolean isEjecutando() {
        return seguirEjecutando;
    }

    // Método para comprobar si se debe esperar
    public synchronized void comprobacionEspera() throws InterruptedException {
        // Esperar hasta que se reanude el hilo si la ejecución se detiene
        while (!seguirEjecutando) {
            wait();
        }
    }

}
