/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

import java.util.Random;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 */
public class Avion extends Thread {

    private Random aleatorio = new Random();

    private String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String nombreAvion;

    private int id;
    private int pasajeros;
    private int capacidadMaxima;
    private int numVuelos = 0;

    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private Aeropuerto aeropuertoAux;

    private Aerovia aerovia;

    /**
     * Constructor del avion que recibe un aeropuerto y un id del mismo
     *
     * @param id
     * @param aeropuertoOrigen
     */
    public Avion(int id, Aeropuerto aeropuertoOrigen) {
        this.id = id;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.capacidadMaxima = aleatorio.nextInt(201) + 100;
        this.aeropuertoDestino = aeropuertoOrigen.getAerovia().getAeropuertoDestino();
        this.nombreAvion = generarNombre(id);
    }

    /**
     * Metodo get para el numero de pasajeros del avion
     *
     * @return pasajeros, el numero de pasajeros del avion
     */
    public int getPasajeros() {
        return pasajeros;
    }

    /**
     * Metodo get para el numero de vuelos que lleva un avion
     *
     * @return numVuelos, numero de vuelos que lleva el avion
     */
    public int getNumVuelos() {
        return numVuelos;
    }

    /**
     * Metodo get para la capacidad maxima del avion
     *
     * @return capacidadMaxima, psasjeros que admite el avion como maximo
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Metodo get para el nombre del avion
     *
     * @return nombreAvion, nombre del avion compuesto por su id y varias letras
     */
    public String getNombreAvion() {
        return nombreAvion;
    }

    /**
     * Metodo set para los pasajeros de un avion
     *
     * @param pasajeros
     */
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     * Metodo set para el numero de vuelos que lleva el avion
     *
     * @param numVuelos
     */
    public void setNumVuelos(int numVuelos) {
        this.numVuelos = numVuelos;
    }

    /**
     * Metodo set para la capacidad maxima del avion
     *
     * @param capacidadMaxima
     */
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Metodo run que define la tarea ejecutada por el avion
     */
    @Override
    public void run() {
        boolean primerVuelo = true;
        try {
            while (true) {
                if (primerVuelo) {
                    //Se accede al Hangar
                    accederHangar();
                    primerVuelo = false;
                } else {
                    int decision = aleatorio.nextInt(1);
                    if (decision == 0) {
                        accederHangar();
                        Thread.sleep((aleatorio.nextInt(16) + 15) * 1000);
                    }

                }
                //Se accede al área de estacionamiento
                accederAreaEstacionamiento();
                //Accede a una puerta de Embarque
                accederPuertaEmbarque();
                //Se accede al área de rodaje
                accederAreaRodaje();
                //Se realizan comprobaciones de entre 1 y 5 segundos antes de solicitar una pista
                int tiempoDeComprobaciones = aleatorio.nextInt(5) + 1;
                Thread.sleep(1000 * tiempoDeComprobaciones);
                //Se intenta acceder a la pista para realizar un despegue
                adquirirPistaDespegue();
                //Realiza unas últimas verificaciones antes de despegar entre 1 y 3 segundos
                int tiempoComprobacionesDespegar = aleatorio.nextInt(3) + 1;
                Thread.sleep(1000 * tiempoComprobacionesDespegar);
                //El avión realiza el despegue
                despegarAvion();
                //Accedemos al aerovia
                aeropuertoOrigen.getAerovia().accederAerovia(this);
                //Solicitamos aterrizaje en el aeropuerto de destino
                aeropuertoDestino.adquirirPistaAterrizaje(this);
                //Una vez aterrizado el avión accedemos a la areá de rodaje
                aeropuertoDestino.areaRodaje(this);
                //Tarda entre 3 y 5 segundos en llegar al lugar donde se desembarcaran los pasajeros
                Thread.sleep(1000 * aleatorio.nextInt(3) + 3);
                //Desembarca los pasajerosç
                aeropuertoDestino.accederPuertaDesembarque(this);
                //Sumamos uno al valor de número de vuelos
                numVuelos++;
                //Se accede al taller
                accederTaller();
                //Cambiamos el aeropuerto destino y el origen
                aeropuertoAux = aeropuertoDestino;
                aeropuertoDestino = aeropuertoOrigen;
                aeropuertoOrigen = aeropuertoAux;

            }
        } catch (InterruptedException e) {
            //Se imprime la excepcion por pantalla
            System.out.println(e);
        }

    }

    /**
     * Metodo para acceder al hangar del aeropuerto
     * @throws java.lang.InterruptedException
     */
    public void accederHangar() throws InterruptedException {
        aeropuertoDestino.accederHangar(this);
    }

    /**
     * Metodo para acceder al area de estacionamiento
     */
    public void accederAreaEstacionamiento() {
        aeropuertoDestino.accederAreaEstacionamiento(this);
    }

    /**
     * Metodo para acceder al area de rodaje
     */
    public void accederAreaRodaje() {
        aeropuertoDestino.areaRodaje(this);
    }

    /**
     * Metodo para intentar adquirir una pista para despegar
     */
    public void adquirirPistaDespegue() {
        aeropuertoDestino.adquirirPistaDespegue(this);
    }

    /**
     * Metodo para que el avión despegue
     */
    public void despegarAvion() {
        aeropuertoDestino.despegarAvion(this);
    }

    /**
     * Metodo para acceder a las puertas de embarque
     */
    public void accederPuertaEmbarque() {
        aeropuertoDestino.accederPuertaDesembarque(this);
    }

    /**
     * Metodo para acceder al taller
     */
    public void accederTaller() {
        aeropuertoDestino.accederTaller(this);
    }

    /**
     * Metodo para crear el nombre del avion a partir de un id asigando y 2
     * letras aleatorias
     *
     * @param id recibe un identificador del avión
     * @return devuelve el nombre del avión
     */
    public String generarNombre(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(letras.charAt(aleatorio.nextInt(letras.length()))); // Primer carácter aleatorio
        sb.append(letras.charAt(aleatorio.nextInt(letras.length()))); // Segundo carácter aleatorio
        sb.append("-");
        sb.append(String.format("%04d", id));
        return sb.toString();

    }
}
