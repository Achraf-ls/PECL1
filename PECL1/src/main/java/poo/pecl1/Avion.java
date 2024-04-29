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
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int id;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private Aeropuerto aeropuertoAux;
    private int pasajeros;
    private Aerovia aerovia;
    private int capacidadMaxima;
    private int numVuelos = 0;

    /**
     * Constructor del avion que recibe un avión y un id del mismo
     *
     * @param id
     * @param aeropuerto
     */
    public Avion(int id, Aeropuerto aeropuerto) {
        this.id = id;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.capacidadMaxima = aleatorio.nextInt(201) + 100;
        this.aeropuertoDestino = aeropuertoOrigen.getAerovia().getAeropuertoDestino();
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public int getNumVuelos() {
        return numVuelos;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void setNumVuelos(int numVuelos) {
        this.numVuelos = numVuelos;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    //Tarea ejecutada por el avión
    public void run() {
        //Generamos un el nombre del avión 
        String nombreAvion = generarNombre(id);
        //Creamos un contador para contar el número de vuelos realizados
        //Una vez alcanzado 15 vuelos entra al taller
        int contador = 0;
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

    //Método para acceder al Hangar del aeropuerto
    public void accederHangar() {
        aeropuertoDestino.accederHangar(this);
    }

    //Método para acceder al área de estacionamiento
    public void accederAreaEstacionamiento() {
        aeropuertoDestino.accederAreaEstacionamiento(this);

    }

    //Método para acceder al área de rodaje
    public void accederAreaRodaje() {
        aeropuertoDestino.areaRodaje(this);
    }

    //Método para intentar adquirir una pista para despegar
    public void adquirirPistaDespegue() {
        aeropuertoDestino.adquirirPistaDespegue(this);
    }

    //Método para que el avión despegue
    public void despegarAvion() {
        aeropuertoDestino.despegarAvion(this);
    }

    //Método para acceder a las puertas de embarque
    public void accederPuertaEmbarque() {
        aeropuertoDestino.accederPuertaDesembarque(this);
    }

    //Método para acceder al taller
    public void accederTaller() {
        aeropuertoDestino.accederTaller(this);
    }

    //Método para crear el nombre del avión 
    /**
     * Recibe el id del avión y mediante la generación aleatoria de dos
     * cáracteres se genera el nombre del avión
     *
     * @param id recibe un identificador del avión
     * @return devuelve el nombre del avión
     */
    public String generarNombre(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(alphabet.charAt(aleatorio.nextInt(alphabet.length()))); // Primer carácter aleatorio
        sb.append(alphabet.charAt(aleatorio.nextInt(alphabet.length()))); // Segundo carácter aleatorio
        sb.append("-");
        sb.append(String.format("%04d", id));
        return sb.toString();

    }
}
