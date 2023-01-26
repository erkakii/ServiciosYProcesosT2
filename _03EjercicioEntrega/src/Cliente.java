import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta;


        System.out.println("Diga la ruta del fichero");
        ruta = sc.next();

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
            //Envío de texto al servidor
            System.out.println("(Cliente) Envía el mensaje de texto al servidor");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            System.out.println("La ruta del archivo que enviamos es: " + ruta);
            bufferedWriter.write(ruta);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //Leemos la respuesta del servidor
            System.out.println("(Cliente): Lee la respuesta del servidor");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Mensaje enviado por el servidor: " + bufferedReader.readLine());

            //Cerrar los flujos de escritura y de lectura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
            outputStream.close();
            inputStream.close();
            outputStreamWriter.close();
            bufferedWriter.close();
            inputStreamReader.close();
            bufferedReader.close();


            //Cerramos la conexión
            System.out.println("Se cierra la conexión con el servidor");
            socketCliente.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

    }
}
