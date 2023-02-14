package Ejercicio1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) {
        //variable que necesitaremos
        Scanner sc = new Scanner(System.in);
        String direccion = "192.168.0.167";
        String cadena;

        try {
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            Socket socketCliente = new Socket(direccion, 49000);

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            System.out.println("(Cliente) Envía el mensaje de texto al servidor");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            System.out.println("Escriba la operación que quiere realizar con el formato num1;operador;num2");
            cadena = sc.nextLine();
            escribir(bufferedWriter, cadena);


            System.out.println("(Cliente): Lee la respuesta del servidor");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Mensaje enviado por el servidor: " + bufferedReader.readLine());

            cerrarFlujos(inputStream, outputStream, outputStreamWriter, bufferedWriter, inputStreamReader, bufferedReader);
        } catch (UnknownHostException e) {
            System.out.println("Error no conoce la maquina que se va a conectar :(");
        } catch (IOException e) {
            System.err.println("Error en la lectura o escritura :(");

        }
    }

    /**
     * Método que escribe en el buffer
     * @param bufferedWriter buffer
     * @param cadena cadena que se va a escribir
     * @throws IOException excepción
     */
    private static void escribir(BufferedWriter bufferedWriter, String cadena) throws IOException {
        bufferedWriter.write(cadena);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    /**
     * Método que cierra los flujos
     * @param inputStream flujo de entrada
     * @param outputStream flujo de salida
     * @param outputStreamWriter flujo de escritura
     * @param bufferedWriter buffer de escritura
     * @param inputStreamReader flujo de lectura
     * @param bufferedReader buffer de lectura
     * @throws IOException excepción
     */
    private static void cerrarFlujos(InputStream inputStream, OutputStream outputStream, OutputStreamWriter outputStreamWriter, BufferedWriter bufferedWriter, InputStreamReader inputStreamReader, BufferedReader bufferedReader) throws IOException {
        System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
        outputStream.close();
        inputStream.close();
        outputStreamWriter.close();
        bufferedWriter.close();
        inputStreamReader.close();
        bufferedReader.close();
    }
}
