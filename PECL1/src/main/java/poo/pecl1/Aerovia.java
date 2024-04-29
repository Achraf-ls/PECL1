/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author achra
 */
public class Aerovia {
    private Random aleatorio = new Random();
    private Aeropuerto aeropuertoDestino;
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue();

    public Aerovia(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public ConcurrentLinkedQueue<Avion> getAvionesAerovia() {
        return avionesAerovia;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }
    
    
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
