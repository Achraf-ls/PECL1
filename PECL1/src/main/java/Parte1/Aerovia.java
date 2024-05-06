/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * En esta clase se describe las tareas que se realizan en la aerovia y sus atributos
 * @author Achraf El Idrissi y Gisela González
 */
public class Aerovia implements Serializable{

    private Random aleatorio = new Random(); //Declaración random
    private String aeropuertoOrigenDestino; //A que aeropuerto pertence la aerovia
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue(); //Lista concurrente que almacena los aviones en la aerovia
    private LoggerA loggerA; //Declaración del logger
    private Aeropuerto aeropuertoDestino;//Aeropuerto destino

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
    /**
     * Metodo get que devuelve el aeropuerto de destino
     * @return aeropuerto destino
     */
    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    /**
     * Metodo para que un avion acceda 
     *
     * @param avion recibe un avión
     * @throws InterruptedException
     */
    public void accederAerovia(Avion avion) throws InterruptedException {
        //Añadimos el avión a la aerovia
        avionesAerovia.add(avion);
        loggerA.logEvent("Avion " + avion.getNombreAvion() + "(" + avion.getPasajeros() + " pasajeros) accede a la aerovía " + avion.getAeropuertoOrigen().getAerovia().aeropuertoOrigenDestino, avion.getAeropuertoOrigen().getNombreAeropuerto());
         int tiempoVuelo = aleatorio.nextInt(16) + 15;
        Thread.sleep(1000 * tiempoVuelo);
        
    }
    
    /**
     * Metodo para que el avión salga de la aerovia
     * @param avion recibe un avion
     * @throws InterruptedException 
     */
    public void abandonarAerovia(Avion avion) throws InterruptedException {
        //Una vez se realiza el vuelo dejamos la aerovia e intentamos adquirir una pista del aeropuerto de origen 
        loggerA.logEvent("Avion " + avion.getNombreAvion() + "(" + avion.getPasajeros() + " pasajeros) abandona la aerovía " + avion.getAeropuertoOrigen().getAerovia().aeropuertoOrigenDestino, avion.getAeropuertoOrigen().getNombreAeropuerto());
        avionesAerovia.remove(avion);
    }

}
