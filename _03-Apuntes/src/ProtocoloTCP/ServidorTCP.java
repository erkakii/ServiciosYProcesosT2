package ProtocoloTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ServerCloneException;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            //Creación del socket servidorç
            System.out.println("(Servidor): Abrinedo conexión");
            ServerSocket socketServidor = new ServerSocket(50000);

            //Espera de la aceptación
            System.out.println("(Servidor): Esperando peticiones");
            Socket socketCliente = socketServidor.accept();

            //Flujos de entrada y de salida
            System.out.println("(Servidor): Abriendo flujos de entrada y de salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambiar datos con el cliente
            System.out.println("(Servidor): Leo mensaje del cliente");
            System.out.println("Mensaje del cliente: " + inputStream.read());

            System.out.println("(Servidor): Envío mensaje del cliente con 7");
            outputStream.write(7);

            //Cerrar flujos de lectura y escritura
            inputStream.close();
            outputStream.close();

            //Cerrar la conexión

            socketServidor.close();
            socketCliente.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        };
    }
}
