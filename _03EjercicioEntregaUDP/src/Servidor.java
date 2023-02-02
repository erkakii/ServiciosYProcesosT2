import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(49000);

            while (true){
                byte [] buffer = new byte[32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData());
                mensaje = mensaje.trim();
                escribirEnFichero(mensaje);
                System.out.println(mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void escribirEnFichero(String mensaje) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("Paquetes.txt", true));
            bfw.write(mensaje);
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
