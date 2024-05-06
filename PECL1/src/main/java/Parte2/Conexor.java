/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte2;

import Parte1.Aeropuerto;
import Parte1.Avion;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * Metodos definidos para el servidor
 * @author Achraf El Idrissi y Gisela González
 */
public class Conexor extends UnicastRemoteObject implements InterfazConexion {

    private Aeropuerto aeropuerto; //El aeropuerto de donde se solicitarán los objetos

    public Conexor() throws RemoteException {

    }
    
    /**
     * El servidor brinda su aeropuerto a la interfaz remota
     * @param aeropuerto
     * @throws RemoteException 
     */

    public void enviarAeropuerto(Aeropuerto aeropuerto) throws RemoteException {
        this.aeropuerto = aeropuerto;
    }
    
    /**
     * El cliente solicita el número de pasajeros
     * @return
     * @throws RemoteException 
     */
    public int pasajerosAeropuerto() throws RemoteException {
        return aeropuerto.getPasajeros();
    }
    
    /**
     * El cliente solicita el número de los aviones en el Hangar
     * @return
     * @throws RemoteException 
     */
    public int avionesHangar() throws RemoteException {
        return aeropuerto.getHangar().size();
    }
    
    /**
     * El cliente solicita el número de los aviones en taller
     * @return
     * @throws RemoteException 
     */
    public int avionesTaller() throws RemoteException {
        return aeropuerto.getAvionesTaller().size();
    }
    
    /**
     * El cliente solicita el número de aviones en el area de estacionamiento
     * @return
     * @throws RemoteException 
     */
    public int avionesAreaEstacionamiento() throws RemoteException {
        return aeropuerto.getAreaDeEstacionamiento().size();
    }

    /**
     * El cliente solicita el numero de aviones en el area de rodaje
     * @return
     * @throws RemoteException 
     */
    public int avionesAreaRodaje() throws RemoteException {
        return aeropuerto.getAreaDeRodaje().size();
    }

    
    /**
     * El cliente solicita los aviones en la aerovia
     * @return
     * @throws RemoteException 
     */
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
    
    /**
     * El cliente solicita un cambio en las pistas disponibles
     * @param valor recibe un boolean que indica si se quiere cerrar o abrir la pista
     * @param pista recibe una pista que indica la pista que se va a cerrar
     * @throws RemoteException 
     */
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
