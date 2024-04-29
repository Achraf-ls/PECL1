/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private Semaphore puertaTaller = new Semaphore(1, true);
    private Semaphore taller = new Semaphore(20, true);
    private ConcurrentLinkedQueue<Avion> avionesTaller = new ConcurrentLinkedQueue();
    private Semaphore pista = new Semaphore(4, true);
    private Semaphore puertasEmbarqueDesembarque = new Semaphore(6, true);
    private Semaphore posiblePuertasEmbarque = new Semaphore(5, true);
    private Semaphore posiblePuertasDesembarque = new Semaphore(5, true);
    private ConcurrentHashMap<Avion, String> avionesPuertaEmbarque = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Avion, String> avionesPuertaDesembarque = new ConcurrentHashMap<>();
    private ReadWriteLock cerrojo = new ReentrantReadWriteLock();
    private ConcurrentLinkedQueue<Avion> areaDeRodaje = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue();
    private Lock eListaPista = cerrojo.writeLock();
    private Lock lListaPista = cerrojo.readLock();
    private HashMap<Avion, String> diccionarioPista = new HashMap<>();
    private Aerovia aerovia;
    private Autobus autobus;

    public Aerovia getAerovia() {
        return aerovia;
    }

    public ConcurrentLinkedQueue<Avion> getHangar() {
        return hangar;
    }

    public void setHangar(ConcurrentLinkedQueue<Avion> hangar) {
        this.hangar = hangar;
    }
    
    public int getPasajeros() {
        try {
            semaforoPasajeros.acquire();
            return pasajeros;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;  // Devuelve un valor de error en caso de interrupción
        } finally {
            semaforoPasajeros.release();
        }
    }

    public ConcurrentLinkedQueue<Avion> getAreaDeEstacionamiento() {
        return areaDeEstacionamiento;
    }

    public void setAreaDeEstacionamiento(ConcurrentLinkedQueue<Avion> areaDeEstacionamiento) {
        this.areaDeEstacionamiento = areaDeEstacionamiento;
    }

    public ConcurrentLinkedQueue<Avion> getAvionesTaller() {
        return avionesTaller;
    }

    public void setAvionesTaller(ConcurrentLinkedQueue<Avion> avionesTaller) {
        this.avionesTaller = avionesTaller;
    }

    public ConcurrentLinkedQueue<Avion> getAreaDeRodaje() {
        return areaDeRodaje;
    }

    public void setAreaDeRodaje(ConcurrentLinkedQueue<Avion> areaDeRodaje) {
        this.areaDeRodaje = areaDeRodaje;
    }

    public ConcurrentLinkedQueue<Avion> getAvionesAerovia() {
        return avionesAerovia;
    }

    public void setAvionesAerovia(ConcurrentLinkedQueue<Avion> avionesAerovia) {
        this.avionesAerovia = avionesAerovia;
    }

    public ConcurrentHashMap<Avion, String> getAvionesPuertaEmbarque() {
        return avionesPuertaEmbarque;
    }

    public void setAvionesPuertaEmbarque(ConcurrentHashMap<Avion, String> avionesPuertaEmbarque) {
        this.avionesPuertaEmbarque = avionesPuertaEmbarque;
    }

    public ConcurrentHashMap<Avion, String> getAvionesPuertaDesembarque() {
        return avionesPuertaDesembarque;
    }

    public void setAvionesPuertaDesembarque(ConcurrentHashMap<Avion, String> avionesPuertaDesembarque) {
        this.avionesPuertaDesembarque = avionesPuertaDesembarque;
    }

    public HashMap<Avion, String> getDiccionarioPista() {
        return diccionarioPista;
    }

    public void setDiccionarioPista(HashMap<Avion, String> diccionarioPista) {
        this.diccionarioPista = diccionarioPista;
    }
 
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void setAerovia(Aerovia aerovia) {
        this.aerovia = aerovia;
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

    public void accederHangar(Avion avion) throws InterruptedException {
        hangar.add(avion);
        System.out.println("el avion se ha añadido correctamente");
        Thread.sleep(3000);
        hangar.remove(avion);
        System.out.println("el avion se ha eliminado");

    }

    public void accederTaller(Avion avion) {
        try {
            taller.acquire();
            //Accedemos a la puerta del taller
            puertaTaller.acquire();
            //El avión tarda 1 segundo en atraversar la puerta
            Thread.sleep(1000);
            //La puerta queda libre
            puertaTaller.release();

            if (avion.getNumVuelos() == 15) {
                //Como el valor de contador de vuelos es igual a 15 se realiza una inspección en profundidad
                //El avión tarda entre 5 y 10 segundos en realizar una inspección en profundidad
                avionesTaller.add(avion);
                Thread.sleep((aleatorio.nextInt(6) + 5) * 1000);

            } else {
                //Si el valor del contador no es igual a 15 simplemente se realiza una inspección rápida
                //El tiempo de esta inspección es de entre 1 y 5 segundos
                avionesTaller.add(avion);
                Thread.sleep((aleatorio.nextInt(5) + 1) * 1000);
            }
            //Salimos del taller
            puertaTaller.acquire();
            //El avión tarda 1 segundo en atraversar la puerta
            Thread.sleep(1000);
            //La puerta queda libre
            puertaTaller.release();
            avionesTaller.remove(avion);
            taller.release();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void accederPuertaEmbarque(Avion avion) {
        try {
            posiblePuertasEmbarque.acquire();
            puertasEmbarqueDesembarque.acquire();
            areaDeEstacionamiento.remove(avion);
            avionesPuertaEmbarque.put(avion, "Embarque");
            int contador = 0;
            while (contador < 3 && avion.getPasajeros() < avion.getCapacidadMaxima()) {
                // Si no hay suficientes pasajeros en el aeropuerto, coge los que haya disponibles
                semaforoPasajeros.acquire();
                int pasajerosParaEmbarcar = Math.min(pasajeros, avion.getCapacidadMaxima() - avion.getPasajeros());
                avion.setPasajeros(avion.getPasajeros() + pasajerosParaEmbarcar);
                pasajeros -= pasajerosParaEmbarcar;
                semaforoPasajeros.release();
                // Cada transferencia de pasajeros al avión dura un tiempo aleatorio entre 1 y 3 segundos
                Thread.sleep((aleatorio.nextInt(3) + 1) * 1000);
                if (avion.getPasajeros() < avion.getCapacidadMaxima()) {
                    // Espera un tiempo aleatorio (1-5 segundos) antes de volver a admitir más pasajeros
                    Thread.sleep((aleatorio.nextInt(5) + 1) * 1000);
                }

                contador++;
            }

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            avionesPuertaEmbarque.remove(avion);
            puertasEmbarqueDesembarque.release();
            posiblePuertasEmbarque.release();

        }

    }

    public void accederPuertaDesembarque(Avion avion) {
        try {
            posiblePuertasDesembarque.acquire();
            puertasEmbarqueDesembarque.acquire();
            //Eliminamos el avión del área de rodaje
            areaDeRodaje.remove(avion);
            //Transferimos los pasajeros al aeropuerto
            semaforoPasajeros.acquire();
            //La transferencia de pasajeros dura entre 1 y 5 segundos
            Thread.sleep(1000 * aleatorio.nextInt(5) + 1);
            pasajeros += avion.getPasajeros();
            avion.setPasajeros(0);
            semaforoPasajeros.release();

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            puertasEmbarqueDesembarque.release();
            posiblePuertasDesembarque.release();

        }

    }

    public void despegarAvion(Avion avion) {
        try {
            //El avión tarda entre 1 y 5 segundos en despegar
            int tiempoDespegue = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoDespegue);
            //El avión despega y la pista queda libre
            pista.release();
            //Se elimina el avión de la lista
            eListaPista.lock();
            diccionarioPista.remove(avion);
            eListaPista.unlock();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public void adquirirPistaDespegue(Avion avion) {
        try {
            //Intentamos adquirir la pista
            pista.acquire();
            //Eliminamos el avión de el área de rodaje
            areaDeRodaje.remove(avion);
            //Añademos el avión a la lista para ello bloqueamos la lista para que se pueda solo estar realizando la escritura
            while (diccionarioPista.size() == 4) {
            }
            eListaPista.lock();
            //Se añade el avión en la lista
            diccionarioPista.put(avion, "Despegue");
            //Se libera el cerrojo de escritura
            eListaPista.unlock();

        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public void adquirirPistaAterrizaje(Avion avion) {
        try {
            boolean pistaAdquirida = false;
            while (!pistaAdquirida) {
                pistaAdquirida = pista.tryAcquire();
                if (!pistaAdquirida) {
                    //Damos una vuelta a la espera de que haya una pista vacia
                    int tiempoVuelta = aleatorio.nextInt(5) + 1;
                    Thread.sleep(1000 * tiempoVuelta);
                }

            }
            //Si hay una pista disponible este accede a ella
            eListaPista.lock();
            diccionarioPista.put(avion, "Aterrizaje");
            eListaPista.unlock();
            //El avión aterriza durante un tiempo de entre 1 y 5 segundos
            int tiempoAterrizaje = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoAterrizaje);
            //Una vez abandona la aterriza abandona la pista y accede al 
            pista.release();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void accederAreaEstacionamiento(Avion avion) {
        areaDeEstacionamiento.add(avion);

    }

    public void areaRodaje(Avion avion) {
        areaDeRodaje.add(avion);
    }

}
