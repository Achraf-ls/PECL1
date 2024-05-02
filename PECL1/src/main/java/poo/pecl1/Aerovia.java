/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Aerovia implements Serializable{

    private Random aleatorio = new Random();
    private String aeropuertoOrigenDestino;
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue();
    private LoggerA loggerA;
    private Aeropuerto aeropuertoDestino;

    /**
     * Constructor de la aerovia que recibe el aeropuerto de destino de esta
     *
     * @param aeropuertoOrigenDestino
     * @param aeropuertoDestino
     * @param loggerA
     */
    public Aerovia(String aeropuertoOrigenDestino, Aeropuerto aeropuertoDestino, LoggerA loggerA) {
        this.aeropuertoOrigenDestino = aeropuertoOrigenDestino;
        this.loggerA = loggerA;
        this.aeropuertoDestino = aeropuertoDestino;
    }

    /**
     * Metodo get para la lista de aviones que se encuentran en la aerovia
     *
     * @return avionesAerovia, lista de aviones que se encuentran en esta
     */
    public ConcurrentLinkedQueue<Avion> getAvionesAerovia() {
        return avionesAerovia;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    /**
     * Metodo para que un avion acceda y salga de la aerovia
     *
     * @param avion
     * @throws InterruptedException
     */
    public void accederAerovia(Avion avion) throws InterruptedException {
        //Añadimos el avión a la aerovia
        avionesAerovia.add(avion);
        loggerA.logEvent("Avion " + avion.getNombreAvion() + "(" + avion.getPasajeros() + " pasajeros) accede a la aerovía " + avion.getAeropuertoOrigen().getAerovia().aeropuertoOrigenDestino);
        //El avión vuela un tiempo entre 15 y 30 segunodos
        int tiempoVuelo = aleatorio.nextInt(16) + 15;
        Thread.sleep(1000 * tiempoVuelo);
        //Una vez se realiza el vuelo dejamos la aerovia e intentamos adquirir una pista del aeropuerto de origen 
        avionesAerovia.remove(avion);
    }

}
