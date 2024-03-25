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
    
    private static final Random random = new Random();
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int id;
    private Aeropuerto aeropuerto;
    private int pasajeros;

    public Avion(int id, Aeropuerto aeropuerto) {
        this.id = id;
        this.aeropuerto = aeropuerto;
    }
    
    
    public void Run(){
         String nombreAvion = generarNombre(id);
         
    }
    
    public static String generarNombre(int id){
       StringBuilder sb = new StringBuilder();
        sb.append(alphabet.charAt(random.nextInt(alphabet.length()))); // Primer carácter aleatorio
        sb.append(alphabet.charAt(random.nextInt(alphabet.length()))); // Segundo carácter aleatorio
        sb.append("-");
        sb.append(String.format("%04d", id)); 
        return sb.toString();
    
    }
}
