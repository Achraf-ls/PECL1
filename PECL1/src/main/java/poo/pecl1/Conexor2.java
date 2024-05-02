/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author achra
 */
public class Conexor2 extends UnicastRemoteObject implements InterfazConexion2 {

    private boolean[] pistasMadrid;
    private boolean[] pistasBarcelona;
    private int semaforoMadrid;
    private int semaforoBarcelona;

    public Conexor2() throws RemoteException {

    }

    @Override
    public void enviarDatos(boolean[] pistasMadrid, boolean[] pistasBarcelona, int semaforoMadrid, int semaforoBarcelona) throws RemoteException {
        this.pistasMadrid = pistasMadrid;
        this.pistasBarcelona = pistasBarcelona;
        this.semaforoMadrid = semaforoMadrid;
        this.semaforoBarcelona = semaforoBarcelona;
    }

    public boolean[] getPistasMadrid() {
        return pistasMadrid;
    }

    public boolean[] getPistasBarcelona() {
        return pistasBarcelona;
    }

    public int getSemaforoMadrid() {
        return semaforoMadrid;
    }

    public int getSemaforoBarcelona() {
        return semaforoBarcelona;
    }

}
