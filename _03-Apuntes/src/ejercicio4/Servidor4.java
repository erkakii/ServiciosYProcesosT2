package ejercicio4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Servidor4 {

    public static  int  sumaNumero = 0;
    public static void main(String[] args) {
        int numeroCliente;


        try {
            //Creación del socket servidor
            ServerSocket serverSocket = new ServerSocket(2100);

            //Espera de la aceptación
            while (true) {
                System.out.println("(Servidor): Abriendo conexión");
                Socket socketCliente = serverSocket.accept();

                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    System.out.println("(Servidor): Leo número del cliente del cliente");

                    numeroCliente = leer(bufferedReader);

                    System.out.println("(Servidor): Envío mensaje de texto al cliente");

                    escribir(numeroCliente, bufferedWriter);



            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    private static void escribir(int numeroCliente, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(sumarNumero(numeroCliente));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private static int sumarNumero(int numeroCliente) {

        sumaNumero += numeroCliente;

        return sumaNumero;
    }


    private static int leer(BufferedReader bufferedReader) throws IOException {
        int numeroCliente;
        numeroCliente = bufferedReader.read();
        System.out.println("Número del cliente " + numeroCliente);
        return numeroCliente;


    }

}

