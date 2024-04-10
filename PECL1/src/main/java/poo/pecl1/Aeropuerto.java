/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Aeropuerto {

    private int pasajeros;
    private Random aleatorio = new Random();
    private Semaphore semaforoPasajeros = new Semaphore(1, true);
    private ConcurrentLinkedQueue<Avion> hangar = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> areaDeEstacionamiento = new ConcurrentLinkedQueue();
    private Autobus autobus;

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void bajarPasajerosBus(Autobus autobus) {
        try {

            semaforoPasajeros.acquire();
            pasajeros += autobus.getPasajeros();

        } catch (InterruptedException e) {

            System.out.println(e);

        } finally {

            semaforoPasajeros.release();

        }

    }

    public void subirPasajerosBus(Autobus autobus) {
        try {

            semaforoPasajeros.acquire();
            pasajeros -= autobus.getPasajeros();

        } catch (InterruptedException e) {

            System.out.println(e);

        } finally {

            semaforoPasajeros.release();

        }

    }

    public void aparecerHangar(Avion avion) {
         hangar.add(avion);

    }

    public void taller() {

    }

    public void accederPuertasEmbarque(Avion avion) {

    }

    public void pistas() {

    }

    public void accederAreaEstacionamiento(Avion avion) {
        hangar.remove(avion);
        areaDeEstacionamiento.add(avion);

    }

    public void areaRodaje() {

    }

}
