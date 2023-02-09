package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

import static Ejercicio1.Servidor.random;

public class GestorProcesos extends Thread {

    DatagramSocket socket;
    DatagramPacket datagramPacket;

    public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada) {
        super();
        this.socket = socket;
        this.datagramPacket = datagramaEntrada;
    }

    @Override
    public void run() {
        averiguarNumero();
    }

    private void averiguarNumero() {
        String mensaje = "";
        int numeroAleatorio;
        numeroAleatorio = Servidor.numeroAleatorio;
        System.out.println(numeroAleatorio);
        String mensajeRecibido = new String(datagramPacket.getData());
        System.out.println("(Servidor): Numero recibido " + mensajeRecibido.trim());
        mensajeRecibido = mensajeRecibido.trim();

        if (mensajeRecibido.equals(String.valueOf(numeroAleatorio))) {
            mensaje = "Numero correcto";
        } else {
            if (Integer.parseInt(mensajeRecibido) < numeroAleatorio) {
                mensaje = "Tu numero es menor";
            } else {
                mensaje = "Tu numero es mayor";

            }
        }

        byte[] mensajeEnviado = mensaje.getBytes();
        DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, datagramPacket.getAddress(), datagramPacket.getPort());

        try {
            socket.send(packetSalida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
