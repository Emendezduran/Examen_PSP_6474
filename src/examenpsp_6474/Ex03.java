/*
* Modifica el programa del ejercicio 1 para que después de cada iteración, después de escribir su
* nombre, dejen paso al otro hilo. Para resolver este programa, solo puede existir el programa principal
* y un único código para los hilos (el programa principal crea un único hilo y el resto son creados
* recurrentemente en el código del hilo). (4 puntos)
*/

package examenpsp_6474;

import java.util.Random;

/**
 *
 * @author DANIELCASTELAO\emendezduran
 */
public class Ex03 extends Thread {

    private int threadNum;

    public Ex03(int threadNum) {
        this.threadNum = threadNum;
    }

    public static void main(String[] args) {
        System.out.println("Main Inicio");
        Ex03 e = new Ex03(3);
        e.start();
        try {
            e.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Main Fin");
    }

    @Override
    public void run() {
        System.out.println("Hilo: " + threadNum + " Inicio ");
        Ex03 hijo = null;
        if (threadNum > 0) {
            hijo = new Ex03(threadNum - 1);
            hijo.start();
        }
        for (int i = 0; i <= 25; i++) {
            System.out.println("Numero de Iteracion: " + i + " SubHilo: " + threadNum);
            int retraso = new Random().nextInt(2000) + 1001;
            try {
                Thread.sleep(retraso);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Hilo " + threadNum + " Fin");

        if (threadNum > 0) {
            try {
                hijo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
