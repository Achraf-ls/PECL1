/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pecl1;

/**
 *
 * @author achra
 */
public class Autobus extends Thread {
    private int id;

    public Autobus(int id) {
        this.id = id;
    }
    
    public void Run(){
        String nombreBus = String.format("B-%04d", id);
    }
}
