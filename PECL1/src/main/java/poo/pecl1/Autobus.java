/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Autobus extends Thread {
    private int id;
    private Aeropuerto aeropuerto;
    private int pasajeros;
    private String nombreBus;
    
    public Autobus(int id, Aeropuerto a) {
        this.id = id;
        this.aeropuerto = a;
    }

    
    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }
    
    public void run(){
        nombreBus = String.format("B-%04d", id);
        try {
            while(true){
            Thread.sleep(2000 + new Random().nextInt(3001)); //llega a la parada y espera
            pasajeros = new Random().nextInt(51); //se montan entre 0 y 50 pasajeros
            Thread.sleep(5000 + new Random().nextInt(5001)); // va al aeropuerto
            aeropuerto.bajarPasajerosBus(this); // entran los pasajeros al aeropuerto
            Thread.sleep(2000 + new Random().nextInt(3001)); //se montan nuevos pasajeros
            pasajeros = new Random().nextInt(51);
            aeropuerto.subirPasajerosBus(this);
            Thread.sleep(5000 + new Random().nextInt(5001)); //sale con nuevos pasajeros 
            pasajeros = 0; //llega a la parada y los pasajeros dejan de contar
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

  
    
}
