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
    
    public Autobus(int id, Aeropuerto aeropuerto) {
        this.id = id;
        this.aeropuerto = aeropuerto;
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
            llegar();
            subirPasajeros();
            viajar();
            bajarPasajeros();
            subirNuevos();
            viajar();
            pasajeros = 0; //llega a la parada y los pasajeros dejan de contar
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llegar() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //llega a la parada y espera
        System.out.println("llega y espera");
    }
    
    private void subirPasajeros(){
        pasajeros = new Random().nextInt(51); //se montan entre 0 y 50 pasajeros   
        System.out.println("Se han subido" + pasajeros);
    }
    
    private void viajar() throws InterruptedException{
        Thread.sleep(5000 + new Random().nextInt(5001)); // va o vuelve del aeropuerto      
    }
    
    private void bajarPasajeros(){
        aeropuerto.bajarPasajerosBus(this); // entran los pasajeros al aeropuerto        
    }
    
    private void subirNuevos() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //se montan nuevos pasajeros 
        pasajeros = new Random().nextInt(51);
        aeropuerto.subirPasajerosBus(this);
    }
   
}
