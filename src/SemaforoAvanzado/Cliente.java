package SemaforoAvanzado;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable{

    Semaphore semaforoCarniceria = new Semaphore(4);
    Semaphore semaforoCharcuteria = new Semaphore(2);

    @Override
    public void run() {
        boolean carniceriaHecho = false, charcuteriaHecho = false;
        try {
            while (!carniceriaHecho || !charcuteriaHecho) {
                if (!carniceriaHecho && semaforoCarniceria.tryAcquire(1L, TimeUnit.SECONDS)) {
                        System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la carniceria");
                        Thread.sleep((long) (1000 * (Math.random() * 10 + 3)));
                        System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carniceria");
                        carniceriaHecho = true;
                        semaforoCarniceria.release();
                }
                if (!charcuteriaHecho && semaforoCharcuteria.tryAcquire(1L, TimeUnit.SECONDS)) {
                        System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la charcuteria");
                        Thread.sleep((long) (1000 * (Math.random() * 10 + 3)));
                        System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la charcuteria");
                        charcuteriaHecho = true;
                        semaforoCharcuteria.release();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
