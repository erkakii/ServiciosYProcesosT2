package ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Servidor2 {
    public static void main(String[] args) {
        int numeroCliente;

        try {
            //Creación del socket servidor
            ServerSocket serverSocket = new ServerSocket(1500);

            //Espera de la aceptación
            while (true){
                System.out.println("(Servidor): Abriendo conexión");
                Socket socketCliente = serverSocket.accept();

                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                System.out.println("(Servidor): Leo número del cliente del cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                numeroCliente = leer(bufferedReader);

                System.out.println("(Servidor): Envío mensaje de texto al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                escribir(numeroCliente, bufferedWriter);

            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void escribir(int numeroCliente, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(factorial(numeroCliente));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private static int leer(BufferedReader bufferedReader) throws IOException {
        int numeroCliente;
        numeroCliente = bufferedReader.read();
        System.out.println("Número del cliente " + numeroCliente);
        return numeroCliente;
    }

    private static  int factorial(int num){
        int factorial = num;

        for (int i = factorial - 1; i > 0 ; i--) {
            factorial *= i;
        }

        return factorial;
    }
}
