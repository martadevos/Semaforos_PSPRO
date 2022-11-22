package SemaforoAvanzado;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();

        for(int i = 1; i<11; i++){
            Thread hilo = new Thread(cliente);
            hilo.setName("cliente "+ i);
            hilo.start();
        }
    }
}
