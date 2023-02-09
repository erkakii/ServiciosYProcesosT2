package Ejercicio1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        int puertoServidor = 49900;
        Scanner sc  = new Scanner(System.in);
        String numero, resultado;

        try {
            System.out.println("(Cliente): Creando el socket...");


            do {
                socket = new DatagramSocket();
                System.out.println("(Cliente) Creando datagrama...");
                System.out.println("Diga un número para probar suerte");
                numero = sc.nextLine();

                byte[] bufferSalida = numero.getBytes();

                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, InetAddress.getLocalHost(), 49900);
                socket.send(paqueteSalida);

                byte[] bufferEntrada = new byte[64];

                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, InetAddress.getLocalHost(), 49900);
                socket.receive(paqueteEntrada);

                resultado = new String(bufferEntrada).trim();
                System.out.println(resultado);
            }while (!resultado.equals("Numero correcto"));

            System.out.println("(Cliente) Cerrando la conexión...");
            socket.close();

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
