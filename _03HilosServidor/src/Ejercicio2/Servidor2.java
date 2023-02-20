package Ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    public static void main(String[] args) {
        try {

            ServerSocket servidor = new ServerSocket(49900);
            Socket peticion;
            while (true) {
                System.out.println("Servidor se encuentra a la escucha de peticiones...");
                peticion = servidor.accept();
                System.out.println("(Servidor) conexión establecida...");
                new GestorProcesos2(peticion).start();
            }

        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }
}
