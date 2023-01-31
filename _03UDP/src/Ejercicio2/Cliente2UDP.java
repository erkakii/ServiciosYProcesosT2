package Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente2UDP {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String cadena1, cadena2;
            //Creación del socket
            DatagramSocket socketCliente = new DatagramSocket();

            System.out.println("Escriba la primera cadena");
            cadena1 = sc.next();
            System.out.println("Escriba la segunda cadena");
            cadena2 = sc.next();
            byte[] bytes = cadena1.getBytes();
            byte[] bytes1 = cadena2.getBytes();

            System.out.println("Creamos el Datagrama");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 49000);
            DatagramPacket packet1 = new DatagramPacket(bytes1, bytes.length, InetAddress.getLocalHost(), 49000);
            socketCliente.send(packet);
            socketCliente.send(packet1);

            byte[] bytesEntrada = new byte[64];
            DatagramPacket packet2 = new DatagramPacket(bytesEntrada, bytesEntrada.length);

            socketCliente.receive(packet2);

            byte[] bytesEntrada2 = new byte[64];
            DatagramPacket packet3 = new DatagramPacket(bytesEntrada2, bytesEntrada2.length);

            socketCliente.receive(packet3);

            String mensaje = new String(packet.getData());
            System.out.println("Mensaje enviado por el cliente " + mensaje.trim());
            String mensaje2 = new String(packet.getData());
            System.out.println("Mensaje enviado por el cliente " + mensaje.trim());

            System.out.println("Cerramos el cliente");
            socketCliente.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
