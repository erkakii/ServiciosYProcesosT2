import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new FileWriter("Paquetes.txt", true));
        try {

            DatagramSocket socket = new DatagramSocket(49000);

            while (true){
                byte [] buffer = new byte[32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData());
                mensaje = mensaje.trim();
                escribirEnFichero(mensaje, bfw);
                System.out.println(mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bfw.close();
        }
    }

    private static void escribirEnFichero(String mensaje, BufferedWriter bfw) {
        try {
            bfw.write(mensaje);
            bfw.newLine();
            bfw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
