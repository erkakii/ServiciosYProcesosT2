package Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
    public static void main(String[] args) {
        try{
            // Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(49000);
            Socket peticion;

            while (true){
                System.out.println("Servidor se encuentra a la escucha de peticiones...");
                peticion = servidor.accept();
                System.out.println("(Servidor) conexión establecida...");
                // Creamos un hilo para cada petición
                new Gestor1(peticion).start();
            }
        } catch (IOException e) {
            System.out.println("Ha habido algún error en la entrada/salida :(");

        }
    }
}
