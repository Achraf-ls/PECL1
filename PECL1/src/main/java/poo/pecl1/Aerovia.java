/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Aerovia {
    
    private Random aleatorio = new Random();
    private Aeropuerto aeropuertoDestino;
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue();

    /**
     * Constructor de la aerovia que recibe el aeropuerto de destino de esta
     * @param aeropuertoDestino 
     */
    public Aerovia(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    /**
     * Metodo get para la lista de aviones que se encuentran en la aerovia
     * @return avionesAerovia, lista de aviones que se encuentran en esta
     */
    public ConcurrentLinkedQueue<Avion> getAvionesAerovia() {
        return avionesAerovia;
    }

    /**
     * Metodo get para el aeropuerto de destino
     * @return aeropuertoDestino, aeropuerto destino de la aerovia
     */
    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }
    
    /**
     * Metodo para que un avion acceda y salga de la aerovia
     * @param avion
     * @throws InterruptedException 
     */
    public void accederAerovia(Avion avion) throws InterruptedException{
        //Añadimos el avión a la aerovia
        avionesAerovia.add(avion);
        //El avión vuela un tiempo entre 15 y 30 segunodos
        int tiempoVuelo = aleatorio.nextInt(16)+ 15;
        Thread.sleep(1000*tiempoVuelo);
        //Una vez se realiza el vuelo dejamos la aerovia e intentamos adquirir una pista del aeropuerto de origen 
        avionesAerovia.remove(avion);
    }
    
}
