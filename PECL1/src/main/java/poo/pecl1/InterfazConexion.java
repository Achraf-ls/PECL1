/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author achra
 */
public interface InterfazConexion extends Remote {

    void enviarAeropuertos(Aeropuerto aeropuertoMadrid, Aeropuerto aeropuertoBarcelona) throws RemoteException;

    Aeropuerto getAeropuertoMadrid() throws RemoteException;

    Aeropuerto getAeropuertoBarcelona() throws RemoteException;
}
