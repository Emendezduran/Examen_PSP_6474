/*
* Modifica el programa del ejercicio 1 para que los hilos se comporten de forma secuencial inverso,
* (espera a que el tercer hilo acabe para que se ejecute el segundo y a su vez, que el segundo acabe
* para que se ejecute el primero) y termina por último el programa principal escribiendo un mensaje que
* indique que terminó. (2 punto)
 */
package examenpsp_6474;

import java.util.Random;

/**
 *
 * @author DANIELCASTELAO\emendezduran
 */
public class Ex02 extends Thread {
    
    String nombre;
    Thread hijo;

    public Ex02(String nombre) {
        this.nombre = nombre;
    }

    public Ex02(String nombre, Thread hijo) {
        this.nombre = nombre;
        this.hijo = hijo;
    }

    @Override
    public void run() {
        if (hijo != null) {
            try {
                hijo.join();//esperamos al hijo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Hilo: " + nombre + " Inicio!");
        
        for (int i = 0; i <= 25; i++) {
            int retraso = new Random().nextInt(2000) + 1001;
            System.out.println("Numero de Iteracion: " + i + " Hilo: " + nombre);
            try {
                Thread.sleep(retraso);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("Hilo: " + nombre + " Fin!");
    }
    
    public static void main(String[] args) {
        System.out.println("Main Inicio.");
        Ex02 hilo3 = new Ex02(" Tres");
        Ex02 hilo2 = new Ex02(" Dos",hilo3);
        Ex02 hilo1 = new Ex02(" Uno", hilo2);
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Main Fin.");
    }
    

}
