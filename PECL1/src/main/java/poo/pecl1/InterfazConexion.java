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

    void enviarAeropuerto(Aeropuerto aeropuerto) throws RemoteException;

    int pasajerosAeropuerto() throws RemoteException;

    int avionesEnHangar() throws RemoteException;

    int avionesEnTaller() throws RemoteException;

    int avionesEnAreaEstacionamiento() throws RemoteException;

    int avionesEnAreaRodaje() throws RemoteException;

    StringBuilder avionesAerovia() throws RemoteException;

}
