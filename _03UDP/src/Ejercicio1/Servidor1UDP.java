package Ejercicio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor1UDP {
    public static void main(String[] args) {
        try {
            System.out.println("Creación del socket");
            DatagramSocket socketServidor = new DatagramSocket(49000);

            while (true){
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];

                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                System.out.println("A la espera de recibir el datagrama");
                socketServidor.receive(packet);

                System.out.println("Leemos el mensaje");
                String mensaje = new String(packet.getData());
                System.out.println("Mensaje enviado por el cliente " + mensaje.trim());
            }



        }catch (Exception e){
            System.err.println("Error :|");
            e.printStackTrace();
        }
    }
}
