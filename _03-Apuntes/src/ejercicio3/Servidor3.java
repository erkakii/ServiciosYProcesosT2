package ejercicio3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Servidor3 {

    public static int numeroRandom = conseguirNumeroRandom();
    private static boolean encontrado = false;

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

    private static int conseguirNumeroRandom() {
        Random random = new Random();
        int numeroRandom = random.nextInt(0, 100);
        return numeroRandom;
    }

    private static void escribir(int numeroCliente, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(esNumero(numeroCliente));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private static String esNumero(int numeroCliente) {
        String resultado = "";

        if (numeroCliente < numeroRandom) {
            resultado = "Tu número es menor que el número secreto";
        } else {
            if (numeroCliente > numeroRandom) {
                resultado = "Tu número es mayor que el número random";
            } else {
                resultado = "Tu número es el número secreto!!!!! :D";
                encontrado = true;
            }
        }

        return resultado;
    }

    private static int leer(BufferedReader bufferedReader) throws IOException {
        int numeroCliente;
        numeroCliente = bufferedReader.read();
        System.out.println("Número del cliente " + numeroCliente);
        return numeroCliente;


    }


}

