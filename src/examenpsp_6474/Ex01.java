/*
* Programa en java que crea tres hilos y los ejecuta. Los hilos escriben 25 veces el número de iteración
* del bucle y su nombre. En cada iteración, después de escribir su nombre, se bloquean durante un
* tiempo aleatorio de segundos y después vuelven a estar disponibles para su ejecución. El programa
* principal no terminará hasta que hayan terminado los tres hilos, escribiendo un mensaje que indique
* que terminó. (4 puntos).
 */
package examenpsp_6474;

import java.util.Random;

/**
 *
 * @author DANIELCASTELAO\emendezduran
 */
public class Ex01 extends Thread {

    private String nombre;

    public Ex01(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Main Inicio");

        Ex01 hilo1 = new Ex01("Uno");
        Ex01 hilo2 = new Ex01("Dos");
        Ex01 hilo3 = new Ex01("Tres");

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
        System.out.println("Main FIN");
    }
}



