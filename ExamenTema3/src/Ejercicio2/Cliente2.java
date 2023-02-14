package Ejercicio2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        //Variables que utilizaremos
        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = null;
        int puertoServidor = 49000;
        String direccionIp = "192.168.0.167";
        String mensaje, resultado;
        try {

            System.out.println("(Cliente): Creando el socket...");
            socket = new DatagramSocket();
            System.out.println("(Cliente) Creando el datagrama");
            System.out.println("""
                    Diga la acción que quiere realizar,
                    Si quiere insertar introduzca create codigo aliasAlumno
                    Si quiere la lista de los alumnos escriba Select
                    """);
            mensaje = sc.nextLine();

            byte[] bufferSalida = mensaje.getBytes();

            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, InetAddress.getByName(direccionIp), puertoServidor);

            //Mandamos el mensaje al servidor
            socket.send(paqueteSalida);

            byte[] bufferEntrada = new byte[128];

            //Recibimos la respuesta del servidor
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, InetAddress.getByName(direccionIp), puertoServidor);
            socket.receive(paqueteEntrada);

            resultado = new String(bufferEntrada).trim();
            System.out.println(resultado);

            System.out.println("(Cliente) Cerrando la conexión...");
            socket.close();

        } catch (SocketException e) {
            System.out.println("Error con la creacion del socket :(");
        } catch (UnknownHostException e) {
            System.out.println("Error no conoce la maquina que se va a conectar :(");
        } catch (IOException e) {
            System.err.println("Error en la lectura o escritura :(");
        }
    }
}
