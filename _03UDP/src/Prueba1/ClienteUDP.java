package Prueba1;

import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            //Creación del socket
            DatagramSocket socketCliente = new DatagramSocket();

            String mensaje = "Hola Diego";
            byte[] bytes = mensaje.getBytes();

            System.out.println("Creamos el Datagrama");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 49000);
            socketCliente.send(packet);

            System.out.println("Cerramos el cliente");
            socketCliente.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
