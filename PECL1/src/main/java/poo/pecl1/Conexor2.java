/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Conexor2 extends UnicastRemoteObject implements InterfazConexion2 {

    private boolean[] pistas;

    private int semaforo;

    public Conexor2() throws RemoteException {

    }

    public void enviarDatos(boolean[] pistas, int semaforo) throws RemoteException {
        this.pistas = pistas;
        this.semaforo = semaforo;
    }

    public boolean[] getPistas() {
        return pistas;
    }

    public int getSemaforo() {
        return semaforo;
    }

}
