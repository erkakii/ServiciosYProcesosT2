package ejercicio3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente3 {
    public static void main(String[] args) {
        String mensajeServidor;
        try {
            //Dirección de socket tipo cliente
            //Dirección ip del servidor y puerto por el que escucha
            System.out.println("(Cliente): Creación de socket");
            Socket socketCliente = new Socket(InetAddress.getLocalHost(), 2100);

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();



            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            do {
                //Intercambio de datos con el servidor
                //Envío de texto al servidor
                System.out.println("(Cliente) Envía el mensaje de texto al servidor");
                int numero = getNumero();
                escribir(bufferedWriter, numero);



                //Leemos la respuesta del servidor
                System.out.println("(Cliente): Lee la respuesta del servidor");
                mensajeServidor = bufferedReader.readLine();

                System.out.println("Mensaje enviado por el servidor: " + mensajeServidor);
            } while (mensajeServidor.equals("Tu número es el número secreto!!!!! :D"));


            //Cerrar los flujos de escritura y de lectura
            cerrarFlujos(inputStream, outputStream, outputStreamWriter, bufferedWriter, inputStreamReader, bufferedReader);


            //Cerramos la conexión
            System.out.println("Se cierra la conexión con el servidor");
            socketCliente.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    private static void cerrarFlujos(InputStream inputStream, OutputStream outputStream, OutputStreamWriter outputStreamWriter, BufferedWriter bufferedWriter, InputStreamReader inputStreamReader, BufferedReader bufferedReader) throws IOException {
        System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
        outputStream.close();
        inputStream.close();
        outputStreamWriter.close();
        bufferedWriter.close();
        inputStreamReader.close();
        bufferedReader.close();
    }

    private static void escribir(BufferedWriter bufferedWriter, int numero) throws IOException {
        bufferedWriter.write(numero);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static boolean validarOpcion(int num) {
        boolean positivo = false;

        if (num > 0) {
            positivo = true;
        }
        return positivo;
    }

    private static int getNumero() {
        int numero = 0;
        Scanner sc = new Scanner(System.in);
        do {
            numero = sc.nextInt();
        } while (!validarOpcion(numero));

        return numero;
    }
}

