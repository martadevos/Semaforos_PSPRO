package Semaforo;

import java.util.concurrent.Semaphore;

public class ClienteCarniceria implements Runnable{

    Semaphore semaforo = new Semaphore(4);

    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println("El "+ Thread.currentThread().getName()+" está siendo atendido");
            Thread.sleep((long) (1000*(Math.random()*10+3)));
            System.out.println("El "+ Thread.currentThread().getName()+" ha terminado en la carniceria");
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClienteCarniceria carniceria = new ClienteCarniceria();

        for(int i = 1; i<11; i++){
            Thread hilo = new Thread(carniceria);
            hilo.setName("cliente "+ i);
            hilo.start();
        }
    }
}
