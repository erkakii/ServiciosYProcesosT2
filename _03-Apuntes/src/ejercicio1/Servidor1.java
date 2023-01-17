package ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Servidor1 {
    public static void main(String[] args) {
        int numeroCliente;

        try {
            //Creación del socket servidor
            ServerSocket serverSocket = new ServerSocket(49900);

            //Espera de la aceptación
            while (true){
                System.out.println("(Servidor): Abriendo conexión");
                Socket socketCliente = serverSocket.accept();

                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                System.out.println("(Servidor): Leo número del cliente del cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                numeroCliente = bufferedReader.read();
                System.out.println("Número del cliente " + numeroCliente);

                System.out.println("(Servidor): Envío mensaje de texto al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write(esPrimo(numeroCliente));
                bufferedWriter.newLine();
                bufferedWriter.flush();







            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static  String esPrimo(int num){
        boolean enc = false;
        String result = "Es primo";

        if (num <= 1){
            result = "No es primo";
        }
        for (int i = 2; i < num / 2 && !enc ; i++) {
            if (num % i == 0){
                result = "No es primo";
                enc = true;
            }
        }

        return result;
    }
}
