/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Conexor extends UnicastRemoteObject implements InterfazConexion {

    private Aeropuerto aeropuerto;

    public Conexor() throws RemoteException {

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

    public StringBuilder avionesAerovia() throws RemoteException {
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

    public void controlarPistas(boolean valor, int pista) throws RemoteException {
        int permisos;
        int nuevosPermisos;
        if (valor) {
            permisos = aeropuerto.getPistasDisponibles();
            aeropuerto.setPistasDisponibles(permisos + 1);
            nuevosPermisos = aeropuerto.getPistasDisponibles();
            Semaphore semaforoPista = new Semaphore(nuevosPermisos, true);
            aeropuerto.setPista(semaforoPista);

        } else {
            permisos = aeropuerto.getPistasDisponibles();
            aeropuerto.setPistasDisponibles(permisos - 1);
            nuevosPermisos = aeropuerto.getPistasDisponibles();
            Semaphore semaforoPista = new Semaphore(nuevosPermisos, true);
            aeropuerto.setPista(semaforoPista);

        }

        if (pista == 1) {
            aeropuerto.setPista1(valor);
        } else if (pista == 2) {
            aeropuerto.setPista2(valor);
        } else if (pista == 3) {
            aeropuerto.setPista3(valor);
        } else {
            aeropuerto.setPista4(valor);
        }

    }

}
