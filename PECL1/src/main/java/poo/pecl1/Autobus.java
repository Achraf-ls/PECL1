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
    private int pasajeros;   
    private LoggerA loggerA;
    
    private Aeropuerto aeropuerto;
    private String nombreBus;
    
    /**
     * Constructor del autobus que recibe un id de este y el aeropuerto con el que se comunica
     * @param id
     * @param aeropuerto 
     * @param loggerA 
     */
    public Autobus(int id, Aeropuerto aeropuerto, LoggerA loggerA) {
        this.id = id;
        this.aeropuerto = aeropuerto;
        this.nombreBus = String.format("B-%04d", id);
        this.loggerA = loggerA;
    }

    public String getNombreBus() {
        return nombreBus;
    }
    
    
    /**
     * Metodo get para el numero de pasajeros del autobus
     * @return pasajeros, numero de pasajeros del autobus
     */
    public int getPasajeros() {
        return pasajeros;
    }

    /**
     * Metodo set para el numero de pasajeros del autobus
     * @param pasajeros 
     */
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }
    
    /**
     * Metodo run que difine la tarea ejecutada por el autobus
     */
    public void run(){
        try {
            while(true){
            llegar();
            subirPasajeros();
            viajar();
            bajarPasajeros();
            subirNuevos();
            viajar();
            //El bus se elimina de la lista de buses dirección ciudad ya que este llega correctamente
            aeropuerto.getBusesDirCiudad().remove(this);
            pasajeros = 0; //llega a la parada y los pasajeros dejan de contar
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para que el autobus llegue a la parada y espere a los pasajeros
     * @throws InterruptedException 
     */
    private void llegar() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //llega a la parada y espera
        System.out.println("llega y espera");
    }
    
    /**
     * Metodo que define el numero de pasajeros que se suben al bus y los añade a este
     */
    private void subirPasajeros(){
        pasajeros = new Random().nextInt(51); //se montan entre 0 y 50 pasajeros   
        System.out.println("Se han subido" + pasajeros);
        aeropuerto.getBusesDirAeropuerto().add(this);
    }
    
    /**
     * Metodo para que el autobus haga su recorrido
     * @throws InterruptedException 
     */
    private void viajar() throws InterruptedException{
        Thread.sleep(5000 + new Random().nextInt(5001)); // va o vuelve del aeropuerto 
    }
    
    /**
     * Metodo para bajar los pasajeros del bus y añadirlos al aeropuerto
     */
    private void bajarPasajeros(){
        aeropuerto.bajarPasajerosBus(this); // entran los pasajeros al aeropuerto        
    }
    
    /**
     * Metodo para subir a ciertos pasajeros al bus y eliminarlos del aeropuerto
     * @throws InterruptedException 
     */
    private void subirNuevos() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //se montan nuevos pasajeros 
        pasajeros = Math.min(new Random().nextInt(51),aeropuerto.getPasajeros());
        aeropuerto.subirPasajerosBus(this);
    }
   
}
