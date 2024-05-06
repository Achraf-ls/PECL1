/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase será utilizada para representar los buses en el sistema 
 * y las tareas que los mismos deben realizar
 * @author Achraf El Idrissi y Gisela González 
 * 
 */
public class Autobus extends Thread implements Serializable{
    
    private int id;//Varible del id del bus
    private int pasajeros; //Variable que contiene los pasajerod en el bus
    
    private LoggerA loggerA; //Declaración del logger
    private Aeropuerto aeropuerto; //Aeropuerto en el que se desarrolaran las tarea del bus
    private String nombreBus; //Contiene el nombre del bus
    private ControladorHilos controladorHilos; //Declaración del controlador de hilos
    
    /**
     * Constructor del autobus que recibe un id de este y el aeropuerto con el que se comunica
     * @param id
     * @param aeropuerto 
     * @param loggerA 
     * @param controladorHilos
     */
    public Autobus(int id, Aeropuerto aeropuerto, LoggerA loggerA,ControladorHilos controladorHilos) {
        this.id = id;
        this.aeropuerto = aeropuerto;
        this.nombreBus = String.format("B-%04d", id);
        this.loggerA = loggerA;
        this.controladorHilos = controladorHilos;
    }
    
    /**
     * Metodo get para el nombre del bus
     * @return el nombre del bus
     */
    public String getNombreBus() {
        return nombreBus;
    }
    
    
    /**
     * Metodo get para el aeropuerto
     * @return el aerpuerto en el que se encuentra el bus
     */
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
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
            //Llega a la parada ciudad
            llegar();
            controladorHilos.comprobacionEspera();
            //Se suben los pasajeros al bus
            subirPasajeros();
            controladorHilos.comprobacionEspera();
            //Se viaja dirección aeropuerto
            viajar();
            controladorHilos.comprobacionEspera();
            //Se bajan los pasajeros
            bajarPasajeros();
            controladorHilos.comprobacionEspera();
            //Se suben nuevos pasajeros
            subirNuevos();
            controladorHilos.comprobacionEspera();
            //Se viaja hacia la ciudad
            viajar();
            controladorHilos.comprobacionEspera();
            //El bus se elimina de la lista de buses dirección ciudad ya que este llega correctamente
            aeropuerto.getBusesDirCiudad().remove(this);
            pasajeros = 0; //llega a la parada y los pasajeros dejan de contar
            }
        } catch (Exception ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para que el autobus llegue a la parada y espere a los pasajeros
     * @throws InterruptedException 
     */
    public void llegar() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //llega a la parada y espera
        System.out.println("llega y espera");
    }
    
    /**
     * Metodo que define el numero de pasajeros que se suben al bus y los añade a este
     */
    public void subirPasajeros(){
        pasajeros = new Random().nextInt(51); //se montan entre 0 y 50 pasajeros   
        System.out.println("Se han subido" + pasajeros);
        aeropuerto.getBusesDirAeropuerto().add(this);
    }
    
    /**
     * Metodo para que el autobus haga su recorrido
     * @throws InterruptedException 
     */
    public void viajar() throws InterruptedException{
        Thread.sleep(5000 + new Random().nextInt(5001)); // va o vuelve del aeropuerto 
    }
    
    /**
     * Metodo para bajar los pasajeros del bus y añadirlos al aeropuerto
     */
    public void bajarPasajeros(){
        aeropuerto.bajarPasajerosBus(this); // entran los pasajeros al aeropuerto        
    }
    
    /**
     * Metodo para subir a ciertos pasajeros al bus y eliminarlos del aeropuerto
     * @throws InterruptedException 
     */
    public void subirNuevos() throws InterruptedException{
        Thread.sleep(2000 + new Random().nextInt(3001)); //se montan nuevos pasajeros 
        pasajeros = Math.min(new Random().nextInt(51),aeropuerto.getPasajeros());
        aeropuerto.subirPasajerosBus(this);
    }
   
}
