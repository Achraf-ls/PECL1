/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author achra
 */
public class ControladorHilos implements Serializable {

    private volatile boolean seguirEjecutando;

    public ControladorHilos() {
        seguirEjecutando = true;
    }

    public synchronized void detener() {
        seguirEjecutando = false;
    }

    public synchronized void reanudar() {
        seguirEjecutando = true;
        notifyAll(); // Notificar a todos los hilos que estén esperando
    }

    public synchronized boolean isEjecutando() {
        return seguirEjecutando;
    }

    public synchronized void comprobaciónEspera() throws InterruptedException {
        while (!seguirEjecutando) {
            wait(); // Esperar hasta que se reanude el hilo
        }
    }

}
