package Ejercicio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente1UDP {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String mensaje;
            //Creación del socket
            DatagramSocket socketCliente = new DatagramSocket();

            System.out.println("Escriba su nombre");
            mensaje = sc.next();
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
