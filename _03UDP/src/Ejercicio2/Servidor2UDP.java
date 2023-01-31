package Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

public class Servidor2UDP {

    public static void main(String[] args) {
        try {
            System.out.println("Creación del socket");
            DatagramSocket socketServidor = new DatagramSocket(49000);
            List<String> listaCadenas = new ArrayList<>();
            while (true){
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];

                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir el datagrama");
                for (int i = 0; i < 2; i++) {
                    socketServidor.receive(packet);
                    System.out.println("Leemos el mensaje");
                    String mensaje = new String(packet.getData());
                    listaCadenas.add(mensaje.trim());
                }

                Collections.sort(listaCadenas);

                byte[] bufferSalida1 = listaCadenas.get(0).getBytes();
                byte[] bufferSalida2 = listaCadenas.get(1).getBytes();

                DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida1, bufferSalida1.length, packet.getAddress(), packet.getPort());
                DatagramPacket packetSalida2 = new DatagramPacket(bufferSalida2, bufferSalida2.length, packet.getAddress(), packet.getPort());
                socketServidor.send(packetSalida1);
                socketServidor.send(packetSalida2);

            }



        }catch (Exception e){
            System.err.println("Error :|");
            e.printStackTrace();
        }
    }
}
