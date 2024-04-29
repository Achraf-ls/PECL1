/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Aeropuerto {

    private int pasajeros;
    private Aerovia aerovia;
    private Autobus autobus;
    private Random aleatorio = new Random();

    private Semaphore semaforoPasajeros = new Semaphore(1, true);
    private Semaphore puertaTaller = new Semaphore(1, true);
    private Semaphore taller = new Semaphore(20, true);
    private Semaphore pista = new Semaphore(4, true);
    private Semaphore puertasEmbarqueDesembarque = new Semaphore(6, true);
    private Semaphore posiblePuertasEmbarque = new Semaphore(5, true);
    private Semaphore posiblePuertasDesembarque = new Semaphore(5, true);

    private ConcurrentLinkedQueue<Avion> hangar = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> areaDeEstacionamiento = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> avionesTaller = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> areaDeRodaje = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> avionesAerovia = new ConcurrentLinkedQueue();

    private ConcurrentHashMap<Avion, String> avionesPuertaEmbarque = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Avion, String> avionesPuertaDesembarque = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Avion, String> avionesPista = new ConcurrentHashMap<>();

    /**
     * Metodo get para la aerovía asignada al aeropuerto
     *
     * @return aerovia, la aerovía asiganada al aeropuerto
     */
    public Aerovia getAerovia() {
        return aerovia;
    }

    /**
     * Metodo get para la lista de aviones que se encuentran en el hangar
     *
     * @return hangar, la lista de aviones en el hangar
     */
    public ConcurrentLinkedQueue<Avion> getHangar() {
        return hangar;
    }

    /**
     * Metodo get para los pasajeros de un aeropuerto
     *
     * @return pasajeros, el numero de pasajeros en el aeropuerto
     */
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

    /**
     * Metodo get para el area de estacionamiento
     *
     * @return areaDeEstacionamiento, la lista de aviones en el area de
     * estacionamiento
     */
    public ConcurrentLinkedQueue<Avion> getAreaDeEstacionamiento() {
        return areaDeEstacionamiento;
    }

    /**
     * Metodo get para los aviones del taller
     *
     * @return avionesTaller, lista de aviones que se encuentran en el taller
     */
    public ConcurrentLinkedQueue<Avion> getAvionesTaller() {
        return avionesTaller;
    }

    /**
     * Metodo get para los aviones del area de rodaje
     *
     * @return areaDeRodaje, lista de aviones que se encuentran en el area de
     * rodaje
     */
    public ConcurrentLinkedQueue<Avion> getAreaDeRodaje() {
        return areaDeRodaje;
    }

    /**
     * Metodo get para los aviones de la aerovia
     *
     * @return avionesAerovia, lista de aviones que se encuentran en la aerovia
     */
    public ConcurrentLinkedQueue<Avion> getAvionesAerovia() {
        return avionesAerovia;
    }

    /**
     * Metodo get para los aviones de la puerta para embarcar
     *
     * @return avionesPuertaEmbarque, HashMap de aviones que se encuentran en
     * las puertas de embarque con el avion y su accion(embarque)
     */
    public ConcurrentHashMap<Avion, String> getAvionesPuertaEmbarque() {
        return avionesPuertaEmbarque;
    }

    /**
     * Metodo get para los aviones en la puerta para desembarcar
     *
     * @return avionesPuertaDesembarque, HashMap de aviones que se encuentran en
     * las puertas de desembarque con el avion y su accion(desembarque)
     */
    public ConcurrentHashMap<Avion, String> getAvionesPuertaDesembarque() {
        return avionesPuertaDesembarque;
    }

    /**
     * Metodo get para los aviones en las pistas
     *
     * @return avionesPista, HashMap de aviones que se encuentran en las pistas
     * con el avion y su accion (despegue/aterrizaje)
     */
    public ConcurrentHashMap<Avion, String> getAvionesPista() {
        return avionesPista;
    }

    /**
     * Metodo set para la lista de aviones que se encuentran en el hangar
     *
     * @param hangar
     */
    public void setHangar(ConcurrentLinkedQueue<Avion> hangar) {
        this.hangar = hangar;
    }

    /**
     * Metodo set para la lista de aviones que se encuentran en el aera de
     * estacionamiento
     *
     * @param areaDeEstacionamiento
     */
    public void setAreaDeEstacionamiento(ConcurrentLinkedQueue<Avion> areaDeEstacionamiento) {
        this.areaDeEstacionamiento = areaDeEstacionamiento;
    }

    /**
     * Metodo set para la lista de aviones que se encuentran en el taller
     *
     * @param avionesTaller
     */
    public void setAvionesTaller(ConcurrentLinkedQueue<Avion> avionesTaller) {
        this.avionesTaller = avionesTaller;
    }

    /**
     * Metodo set para la lista de aviones que se encuentran en el area de
     * rodaje
     *
     * @param areaDeRodaje
     */
    public void setAreaDeRodaje(ConcurrentLinkedQueue<Avion> areaDeRodaje) {
        this.areaDeRodaje = areaDeRodaje;
    }

    /**
     * Metodo set para la lista de aviones que se encuentran en la aerovia
     *
     * @param avionesAerovia
     */
    public void setAvionesAerovia(ConcurrentLinkedQueue<Avion> avionesAerovia) {
        this.avionesAerovia = avionesAerovia;
    }

    /**
     * Metodo set para el HashMap de aviones que se encuentran en la puerta de
     * embarque
     *
     * @param avionesPuertaEmbarque
     */
    public void setAvionesPuertaEmbarque(ConcurrentHashMap<Avion, String> avionesPuertaEmbarque) {
        this.avionesPuertaEmbarque = avionesPuertaEmbarque;
    }

    /**
     * Metodo set para el HashMap de aviones que se encuentran en la puerta de
     * desembarque
     *
     * @param avionesPuertaDesembarque
     */
    public void setAvionesPuertaDesembarque(ConcurrentHashMap<Avion, String> avionesPuertaDesembarque) {
        this.avionesPuertaDesembarque = avionesPuertaDesembarque;
    }

    /**
     * Metodo set para el HashMap de aviones que se encuentran en las pistas
     *
     * @param avionesPista
     */
    public void setAvionesPista(ConcurrentHashMap<Avion, String> avionesPista) {
        this.avionesPista = avionesPista;
    }

    /**
     * Metodo set para los pasajeros que se encuentran en el aeropuerto
     *
     * @param pasajeros
     */
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     * Metodo set para la aerovia que "corresponde" al aeropuerto
     *
     * @param aerovia
     */
    public void setAerovia(Aerovia aerovia) {
        this.aerovia = aerovia;
    }

    /**
     * Metodo void que añade los pasajeros de un autobus al aeropuerto
     *
     * @param autobus
     */
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

    /**
     * Metodo void que elimina del aeropuerto a los pasajeros que se suben a un
     * bus
     *
     * @param autobus
     */
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

    /**
     * Metodo que "controla" el hangar, añadiendo y eliminando a los aviones
     *
     * @param avion
     * @throws InterruptedException
     */
    public void accederHangar(Avion avion) throws InterruptedException {
        hangar.add(avion);
        System.out.println("el avion se ha añadido correctamente");
        Thread.sleep(3000);
        hangar.remove(avion);
        System.out.println("el avion se ha eliminado");

    }

    /**
     * Metodo que permite a un avion acceder al taller y salir de el tras
     * ejecutar las acciones correspondientes en el
     *
     * @param avion
     */
    public void accederTaller(Avion avion) {
        try {
            taller.acquire();
            //Accedemos a la puerta del taller
            puertaTaller.acquire();
            //El avión tarda 1 segundo en atraversar la puerta
            Thread.sleep(1000);
            //La puerta queda libre
            puertaTaller.release();

            System.out.println("avion en taller");
            avionesTaller.add(avion);
            
            if (avion.getNumVuelos() == 15) {
                //Como el valor de contador de vuelos es igual a 15 se realiza una inspección en profundidad
                //El avión tarda entre 5 y 10 segundos en realizar una inspección en profundidad
                Thread.sleep((aleatorio.nextInt(6) + 5) * 1000);

            } else {
                //Si el valor del contador no es igual a 15 simplemente se realiza una inspección rápida
                //El tiempo de esta inspección es de entre 1 y 5 segundos
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

    /**
     * Metodo que permite a un avion acceder a la puerta de embarque y embarcar
     * a pasajeros
     *
     * @param avion
     */
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

    /**
     * Metodo que permite a un avion acceder a la puerta de desembarque y
     * desembarcar a los pasajeros
     *
     * @param avion
     */
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

    /**
     * Metodo para el despegue de un avion
     *
     * @param avion
     */
    public void despegarAvion(Avion avion) {
        try {
            //El avión tarda entre 1 y 5 segundos en despegar
            int tiempoDespegue = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoDespegue);
            //El avión despega y la pista queda libre
            pista.release();
            //Se elimina el avión de la lista
            avionesPista.remove(avion);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    /**
     * Metodo para acceder a una pista de despegue
     *
     * @param avion
     */
    public void adquirirPistaDespegue(Avion avion) {
        try {
            //Intentamos adquirir la pista
            pista.acquire();
            //Eliminamos el avión de el área de rodaje
            areaDeRodaje.remove(avion);
            while (avionesPista.size() == 4) {
            }
            //Se añade el avión en la lista
            avionesPista.put(avion, "Despegue");

        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    /**
     * Metodo para acceder a una pista de aterrizaje
     *
     * @param avion
     */
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
            avionesPista.put(avion, "Aterrizaje");
            //El avión aterriza durante un tiempo de entre 1 y 5 segundos
            int tiempoAterrizaje = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoAterrizaje);
            //Una vez abandona la aterriza abandona la pista y accede al 
            pista.release();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Metodo para añadir un avion al area de estacionamiento
     *
     * @param avion
     */
    public void accederAreaEstacionamiento(Avion avion) {
        areaDeEstacionamiento.add(avion);
    }

    /**
     * Metodo para añadir un avion al area de rodaje
     *
     * @param avion
     */
    public void areaRodaje(Avion avion) {
        areaDeRodaje.add(avion);
    }

}
