/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.pecl1;

import java.util.Random;

/**
 *
 * @author Achraf El Idrissi y Gisela González 
 */
public class PECL1 {

    public static void main(String[] args) throws InterruptedException {
        
        Aeropuerto a = new Aeropuerto();
        
        for(int i = 0; i < 8000; i++){
            new Avion(i, a).start();
            Thread.sleep(1000 + new Random().nextInt(2001)); //intervalo entre 1s y 3s
        }
        for(int i = 0; i < 4000; i++){
            new Autobus(i, a).start();
            Thread.sleep(500 + new Random().nextInt(501)); // intervalo entre 0,5s y 1s
        }
    }
}
