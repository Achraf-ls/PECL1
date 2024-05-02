/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poo.pecl1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author achra
 */
public interface InterfazConexion2 extends Remote {

    void enviarDatos(boolean[] pistasMadrid, boolean[] pistasBarcelona, int semaforoMadrid, int semaforoBarcelona) throws RemoteException;

    boolean[] getPistasMadrid() throws RemoteException;

    boolean[] getPistasBarcelona() throws RemoteException;

    int getSemaforoMadrid() throws RemoteException;

    int getSemaforoBarcelona() throws RemoteException;

}
