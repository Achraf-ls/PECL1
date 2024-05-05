/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author achra
 */
public class Conexor extends UnicastRemoteObject implements InterfazConexion {

    private  Aeropuerto aeropuerto;

    public Conexor() throws RemoteException {
        // No se necesita inicializar nada especial en el constructor
    }

    public void enviarAeropuerto(Aeropuerto aeropuerto) throws RemoteException {
        this.aeropuerto = aeropuerto;
    }

    public int pasajerosAeropuerto() throws RemoteException {
        return aeropuerto.getPasajeros();
    }

    public int avionesEnHangar() throws RemoteException {
        return aeropuerto.getHangar().size();
    }

    public int avionesEnTaller() throws RemoteException {
        return aeropuerto.getAvionesTaller().size();
    }

    public int avionesEnAreaEstacionamiento() throws RemoteException {
        return aeropuerto.getAreaDeEstacionamiento().size();
    }

    public int avionesEnAreaRodaje() throws RemoteException {
        return aeropuerto.getAreaDeRodaje().size();
    }

    public StringBuilder avionesAerovia() throws RemoteException{
       ConcurrentLinkedQueue<Avion> aerovia = aeropuerto.getAerovia().getAvionesAerovia();
        StringBuilder avionesAerovia = new StringBuilder();

        for (Avion avion : aerovia) {
            avionesAerovia.append(avion.getNombreAvion());
            avionesAerovia.append(", ");
        }

        if (avionesAerovia.length() > 0) {
            avionesAerovia.delete(avionesAerovia.length() - 2, avionesAerovia.length());
        }
        return avionesAerovia;
    }

}
