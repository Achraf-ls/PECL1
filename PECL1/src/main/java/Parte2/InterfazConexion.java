/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte2;

import Parte1.Aeropuerto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz de conexión remota con metodos 
 * @author Achraf El Idrissi y Gisela González
 */
public interface InterfazConexion extends Remote {

    void enviarAeropuerto(Aeropuerto aeropuerto) throws RemoteException;

    int pasajerosAeropuerto() throws RemoteException;

    int avionesHangar() throws RemoteException;

    int avionesTaller() throws RemoteException;

    int avionesAreaEstacionamiento() throws RemoteException;

    int avionesAreaRodaje() throws RemoteException;

    StringBuilder avionesAerovia() throws RemoteException;
    
    void controlarPistas(boolean valor, int pista) throws RemoteException;

}
