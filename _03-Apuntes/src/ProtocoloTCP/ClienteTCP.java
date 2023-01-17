package ProtocoloTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            //Dirección de socket tipo cliente
            //Dirección ip del servidor y puerto por el que escucha
            System.out.println("(Cliente): Creación de socket");
            Socket socketCliente = new Socket(InetAddress.getLocalHost(), 49900);

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambio de datos con el servidor
            System.out.println("(Cliente) Envía el mensaje de 14 al servidor");

            outputStream.write(14);

            System.out.println("(Cliente): Lee la respuesta del servidor");
            System.out.println("Mensaje enviado por el servidor " + inputStream.read());

            //Cerrar los flujos de escritura y de lectura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
            outputStream.close();
            inputStream.close();

            //Cerramos la conexión
            socketCliente.close();





        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
