package Ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String direccion;
        try{
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            Socket socketCliente = new Socket(InetAddress.getLocalHost(), 49900);

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambio de datos con el servidor
            //Envío de texto al servidor
            System.out.println("(Cliente) Envía el mensaje de texto al servidor");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            direccion = sc.nextLine();
            escribir(bufferedWriter, direccion);

            //Leemos la respuesta del servidor
            System.out.println("(Cliente): Lee la respuesta del servidor");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Mensaje enviado por el servidor: " + bufferedReader.readLine());

            cerrarFlujos(inputStream, outputStream, outputStreamWriter, bufferedWriter, inputStreamReader, bufferedReader);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private static void escribir(BufferedWriter bufferedWriter, String direccion) throws IOException {
        bufferedWriter.write(direccion);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
