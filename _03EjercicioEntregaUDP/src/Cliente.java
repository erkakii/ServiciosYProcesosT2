import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {

    private static final String FINAL = "FIN";
    public static void main(String[] args) {
        byte [] envio = new byte[32];
        String mensaje;
        try {
            DatagramSocket socket = new DatagramSocket();
            for (int i = 0; i < 10000; i++) {
                mensaje = "Archivo nº: " + i;
                envio = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(envio, envio.length, InetAddress.getLocalHost(), 49000);
                socket.send(packet);
            }

            envio = FINAL.getBytes();
            DatagramPacket packet = new DatagramPacket(envio, envio.length, InetAddress.getLocalHost(), 49000);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
