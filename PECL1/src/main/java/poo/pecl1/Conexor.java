/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author achra
 */
public class Conexor extends UnicastRemoteObject implements InterfazConexion {

    private Aeropuerto aeropuertoMadrid;
    private Aeropuerto aeropuertoBarcelona;

    public Conexor() throws RemoteException {
        
    }

   
    public void enviarAeropuertos(Aeropuerto aeropuertoMadrid, Aeropuerto aeropuertoBarcelona) throws RemoteException {
        this.aeropuertoMadrid = aeropuertoMadrid;
        this.aeropuertoBarcelona = aeropuertoBarcelona;
    }

    public Aeropuerto getAeropuertoMadrid() {
        return aeropuertoMadrid;
    }

    public Aeropuerto getAeropuertoBarcelona() {
        return aeropuertoBarcelona;
    }

}
