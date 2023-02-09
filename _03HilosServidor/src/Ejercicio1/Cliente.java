package Ejercicio1;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        int puertoServidor = 49900;
        Scanner sc  = new Scanner(System.in);
        String numero;

        try {
            System.out.println("(Cliente): Creando el socket...");
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
