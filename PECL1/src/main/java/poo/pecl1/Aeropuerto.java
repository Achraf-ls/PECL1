/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Aeropuerto implements Serializable {

    private int pasajeros;
    private int pistasDisponibles;
    private int p = 1;
    private int puerta;

    private boolean pista1 = true;
    private boolean pista2 = true;
    private boolean pista3 = true;
    private boolean pista4 = true;

    private Aerovia aerovia;
    private LoggerA loggerA;
    private Random aleatorio = new Random();
    private String nombreAeropuerto;

    private Semaphore semaforoPasajeros = new Semaphore(1, true);
    private Semaphore puertaTaller = new Semaphore(1, true);
    private Semaphore taller = new Semaphore(20, true);
    private Semaphore pista;
    private Semaphore puertasEmbarqueDesembarque = new Semaphore(6, true);
    private Semaphore posiblePuertasEmbarque = new Semaphore(5, true);
    private Semaphore posiblePuertasDesembarque = new Semaphore(5, true);

    private ConcurrentLinkedQueue<Avion> hangar = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> areaDeEstacionamiento = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> avionesTaller = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Avion> areaDeRodaje = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Autobus> busesDirAeropuerto = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Autobus> busesDirCiudad = new ConcurrentLinkedQueue();

    private ArrayList<Avion> avionesPuertas = new ArrayList<Avion>(Arrays.asList(new Avion[6]));
    private ArrayList<Avion> listaPista = new ArrayList<Avion>(Arrays.asList(new Avion[4]));

    private ReentrantReadWriteLock lockPista = new ReentrantReadWriteLock();
    Lock lecturaPista = lockPista.readLock();
    Lock escrituraPista = lockPista.writeLock();
    private ReentrantReadWriteLock lockPuertas = new ReentrantReadWriteLock();
    Lock lecturaPuertas = lockPuertas.readLock();
    Lock escrituraPuertas = lockPuertas.writeLock();

    public Aeropuerto(LoggerA loggerA, String nombreAeropuerto, int pistasDisponibles) {
        this.loggerA = loggerA;
        this.nombreAeropuerto = nombreAeropuerto;
        this.pistasDisponibles = pistasDisponibles;
        this.pista = new Semaphore(pistasDisponibles, true);
    }

    public ConcurrentLinkedQueue<Autobus> getBusesDirAeropuerto() {
        return busesDirAeropuerto;
    }

    public ConcurrentLinkedQueue<Autobus> getBusesDirCiudad() {
        return busesDirCiudad;
    }

    public String getNombreAeropuerto() {
        return nombreAeropuerto;
    }

    public ArrayList<Avion> getListaPista() {
        try {
            lecturaPista.lock();
            return listaPista;
        } finally {
            lecturaPista.unlock();
        }
    }

    /**
     * Metodo get para la aerovía asignada al aeropuerto
     *
     * @return aerovia, la aerovía asiganada al aeropuerto
     */
    public synchronized Aerovia getAerovia() {
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
     * Metodo get para los aviones de las puertas de embarque
     *
     * @return avionesPuertaEmbarque, HashMap de aviones que se encuentran en
     * las puertas de embarque con el avion y su accion(embarque)
     */
    public ArrayList<Avion> getAvionesPuertas() {
        try {
            lecturaPuertas.lock();
            return avionesPuertas;
        } finally {
            lecturaPuertas.unlock();
        }
    }

    public void setBusesDirAeropuerto(ConcurrentLinkedQueue<Autobus> busesDirAeropuerto) {
        this.busesDirAeropuerto = busesDirAeropuerto;
    }

    public void setBusesDirCiudad(ConcurrentLinkedQueue<Autobus> busesDirCiudad) {
        this.busesDirCiudad = busesDirCiudad;
    }

    public void setPistasDisponibles(int pistasDisponibles) {
        escrituraPista.lock();
        this.pistasDisponibles = pistasDisponibles;
        escrituraPista.unlock();
    }

    public void setPista1(boolean pista1) {
        escrituraPista.lock();
        this.pista1 = pista1;
        escrituraPista.unlock();
    }

    public void setPista2(boolean pista2) {
        escrituraPista.lock();
        this.pista2 = pista2;
        escrituraPista.unlock();
    }

    public void setPista3(boolean pista3) {
        escrituraPista.lock();
        this.pista3 = pista3;
        escrituraPista.unlock();
    }

    public void setPista4(boolean pista4) {
        escrituraPista.lock();
        this.pista4 = pista4;
        escrituraPista.unlock();
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
     * Metodo set para el ArrayList de aviones que se encuentran en la puerta de
     * embarque
     *
     * @param avionesPuertas
     */
    public void setAvionesPuertas(ArrayList<Avion> avionesPuertas) {
        this.avionesPuertas = avionesPuertas;
    }

    public void setListaPista(ArrayList<Avion> listaPista) {
        this.listaPista = listaPista;
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
            busesDirAeropuerto.remove(autobus);
            loggerA.logEvent("Bus " + autobus.getNombreBus() + " deja " + autobus.getPasajeros() + " pasajeros en el aeropuerto de " + autobus.getAeropuerto().nombreAeropuerto, autobus.getAeropuerto().nombreAeropuerto);
            pasajeros += autobus.getPasajeros();

        } catch (Exception e) {

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
            loggerA.logEvent("Bus " + autobus.getNombreBus() + " recoge " + autobus.getPasajeros() + " del aeropuerto de " + autobus.getAeropuerto().nombreAeropuerto, autobus.getAeropuerto().nombreAeropuerto);
            pasajeros -= autobus.getPasajeros();
            busesDirCiudad.add(autobus);

        } catch (Exception e) {

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
        loggerA.logEvent("Avión " + avion.getNombreAvion() + " accede al hangar", avion.getAeropuertoOrigen().nombreAeropuerto);
        Thread.sleep(2000);

    }

    /**
     * Metodo que "controla" el hangar, añadiendo y eliminando a los aviones
     *
     * @param avion
     * @throws InterruptedException
     */
    public void salirHangar(Avion avion) throws InterruptedException {
        loggerA.logEvent("Avión " + avion.getNombreAvion() + " sale del hangar", avion.getAeropuertoOrigen().nombreAeropuerto);
        hangar.remove(avion);
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

            loggerA.logEvent("Avión " + avion.getNombreAvion() + " accede al taller en su vuelo numero " + avion.getNumVuelos(), avion.getAeropuertoOrigen().nombreAeropuerto);
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

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void salirTaller(Avion avion) {
        try {
            //Salimos del taller
            puertaTaller.acquire();
            //El avión tarda 1 segundo en atraversar la puerta
            Thread.sleep(1000);
            //La puerta queda libre
            puertaTaller.release();
            avionesTaller.remove(avion);
            taller.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
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
            int puerta = 1;
            posiblePuertasEmbarque.acquire();
            puertasEmbarqueDesembarque.acquire();
            areaDeEstacionamiento.remove(avion);

            escrituraPuertas.lock();

            for (int i = 0; i < avionesPuertas.size(); i++) {
                if (avionesPuertas.get(i) == null) {
                    avionesPuertas.set(i, avion);
                    puerta += i;
                    break;
                }
            }

            escrituraPuertas.unlock();

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
            loggerA.logEvent("Avión " + avion.getNombreAvion() + " accede a puerta de embarque " + puerta + " para embarcar " + avion.getPasajeros() + " pasajeros", avion.getAeropuertoOrigen().nombreAeropuerto);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void salirPuertaEmbarque(Avion avion) {
        try {

            escrituraPuertas.lock();
            for (int i = 0; i < avionesPuertas.size(); i++) {
                Avion avionEnPista = avionesPuertas.get(i);
                if (avionEnPista != null && avionEnPista.getIdAvion() == avion.getIdAvion()) {
                    avionesPuertas.set(i, null); // Elimina el avión de la pista
                    break; // Sal del bucle una vez que hayas eliminado el avión
                }
            }
            escrituraPuertas.unlock();
            puertasEmbarqueDesembarque.release();
            posiblePuertasEmbarque.release();
        } catch (Exception e) {

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
            int puerta = 1;
            posiblePuertasDesembarque.acquire();
            puertasEmbarqueDesembarque.acquire();
            //Eliminamos el avión del área de rodaje
            areaDeRodaje.remove(avion);

            escrituraPuertas.lock();

            for (int i = 0; i < avionesPuertas.size(); i++) {
                if (avionesPuertas.get(i) == null) {
                    avionesPuertas.set(i, avion);
                    puerta += i;
                    break;
                }
            }
            loggerA.logEvent("Avión " + avion.getNombreAvion() + " accede a puerta de embarque " + puerta + " para desembarcar " + avion.getPasajeros() + " pasajeros", avion.getAeropuertoOrigen().nombreAeropuerto);
            escrituraPuertas.unlock();

            //Transferimos los pasajeros al aeropuerto
            semaforoPasajeros.acquire();
            //La transferencia de pasajeros dura entre 1 y 5 segundos
            Thread.sleep(1000 * aleatorio.nextInt(5) + 1);
            pasajeros += avion.getPasajeros();

            avion.setPasajeros(0);
            semaforoPasajeros.release();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void abandonarPuertasDesembarque(Avion avion) {
        escrituraPuertas.lock();
        for (int i = 0; i < avionesPuertas.size(); i++) {
            Avion avionEnPista = avionesPuertas.get(i);
            if (avionEnPista != null && avionEnPista.getIdAvion() == avion.getIdAvion()) {
                avionesPuertas.set(i, null); // Elimina el avión de la pista
                break; // Sal del bucle una vez que hayas eliminado el avión
            }
        }
        escrituraPuertas.unlock();
        puertasEmbarqueDesembarque.release();
        posiblePuertasDesembarque.release();

    }

    /**
     * Metodo para el despegue de un avion
     *
     * @param avion
     */
    public void despegarAvion(Avion avion) {
        try {
            // El avión tarda entre 1 y 5 segundos en despegar
            int tiempoDespegue = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoDespegue);

            // Adquirir el bloqueo de escritura antes de modificar listaPista
            escrituraPista.lock();
            try {
                // El avión despega y la pista queda libre
                for (int i = 0; i < listaPista.size(); i++) {
                    Avion avionEnPista = listaPista.get(i);
                    if (avionEnPista != null && avionEnPista.getIdAvion() == avion.getIdAvion()) {
                        listaPista.set(i, null); // Elimina el avión de la pista
                        break; // Sal del bucle una vez que hayas eliminado el avión
                    }
                }
            } finally {
                escrituraPista.unlock(); // Asegúrate de liberar el bloqueo incluso si ocurre una excepción
            }
            // Libera la pista para que otros aviones puedan usarla
            pista.release();
        } catch (Exception e) {
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

            // Intenta adquirir la pista
            pista.acquire();

            // Elimina el avión del área de rodaje
            areaDeRodaje.remove(avion);

            // Adquiere el bloqueo de escritura antes de modificar listaPista
            escrituraPista.lock();
            try {
                // Añade el avión a la primera pista disponible
                for (int i = 0; i < listaPista.size(); i++) {
                    if (listaPista.get(i) == null) {
                        if (pista1 && i == 0) {
                            listaPista.set(i, avion);
                            p += i;
                            break;
                        } else if (pista2 && i == 1) {
                            listaPista.set(i, avion);
                            p += i;
                            break;
                        } else if (pista3 && i == 2) {
                            listaPista.set(i, avion);
                            p += i;
                            break;
                        } else if (pista4 && i == 3) {
                            listaPista.set(i, avion);
                            p += i;
                            break;
                        }
                    }
                }

                loggerA.logEvent("Avion " + avion.getNombreAvion() + " (" + avion.getPasajeros() + " pasajeros) accede a la pista " + p + " para despegue", avion.getAeropuertoOrigen().nombreAeropuerto);
            } finally {
                escrituraPista.unlock();
            }
        } catch (Exception e) {
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
            escrituraPista.lock();
            for (int i = 0; i < listaPista.size(); i++) {
                if (listaPista.get(i) == null) {
                    if (pista1 && i == 0) {
                        listaPista.set(i, avion);
                        p += i;
                        break;
                    } else if (pista2 && i == 1) {
                        listaPista.set(i, avion);
                        p += i;
                        break;
                    } else if (pista3 && i == 2) {
                        listaPista.set(i, avion);
                        p += i;
                        break;
                    } else if (pista4 && i == 3) {
                        listaPista.set(i, avion);
                        p += i;
                        break;
                    }
                }
            }
            loggerA.logEvent("Avion " + avion.getNombreAvion() + " (" + avion.getPasajeros() + " pasajeros) accede a la pista " + p + " para aterrizaje", avion.getAeropuertoOrigen().nombreAeropuerto);
            escrituraPista.unlock();

            //El avión aterriza durante un tiempo de entre 1 y 5 segundos
            int tiempoAterrizaje = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoAterrizaje);
            //Una vez abandona la aterriza abandona la pista y accede al 
            escrituraPista.lock();
            for (int i = 0; i < listaPista.size(); i++) {
                Avion avionEnPista = listaPista.get(i);
                if (avionEnPista != null && avionEnPista.getIdAvion() == avion.getIdAvion()) {
                    listaPista.set(i, null); // Elimina el avión de la pista
                    break; // Sal del bucle una vez que hayas eliminado el avión
                }
            }
            escrituraPista.unlock();
            pista.release();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void abandonaPistaAterrizaje(Avion avion) {

        try {
            //El avión aterriza durante un tiempo de entre 1 y 5 segundos
            int tiempoAterrizaje = aleatorio.nextInt(5) + 1;
            Thread.sleep(1000 * tiempoAterrizaje);
            //Una vez abandona la aterriza abandona la pista y accede al 
            escrituraPista.lock();
            for (int i = 0; i < listaPista.size(); i++) {
                Avion avionEnPista = listaPista.get(i);
                if (avionEnPista != null && avionEnPista.getIdAvion() == avion.getIdAvion()) {
                    listaPista.set(i, null); // Elimina el avión de la pista
                    break; // Sal del bucle una vez que hayas eliminado el avión
                }
            }
            escrituraPista.unlock();
            pista.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para añadir un avion al area de estacionamiento
     *
     * @param avion
     */
    public void accederAreaEstacionamiento(Avion avion) {
        try {
            areaDeEstacionamiento.add(avion);
            loggerA.logEvent("Avion " + avion.getNombreAvion() + " accede al area de estacionamiento", avion.getAeropuertoOrigen().nombreAeropuerto);
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Metodo para añadir un avion al area de rodaje
     *
     * @param avion
     */
    public void areaRodaje(Avion avion) {
        areaDeRodaje.add(avion);
        loggerA.logEvent("Avion " + avion.getNombreAvion() + " (" + avion.getPasajeros() + " pasajeros) accede al area de rodaje", avion.getAeropuertoOrigen().nombreAeropuerto);
    }

}
