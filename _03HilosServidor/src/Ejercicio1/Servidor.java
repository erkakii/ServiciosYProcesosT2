package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Servidor {
    public static Random random = new Random();
    public static int numeroAleatorio = random.nextInt(1,100);
    public static void main(String[] args) {
        DatagramSocket socketServidor = null;
        String numeroRecibido;
        try {
            System.out.println("(Servidor) Creamos el socket..");
            socketServidor = new DatagramSocket(49900);

            while (true){
                byte [] buffer = new byte[64];
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);

                System.out.println("(Servidor) esperando peticiones...");
                socketServidor.receive(datagramaEntrada);

                new GestorProcesos(socketServidor, datagramaEntrada).start();
            }
        } catch (SocketException e) {
            System.err.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}
