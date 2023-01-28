import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Servidor {
    public static void main(String[] args) {
        String rutaFichero, contenido;

        try {
            ServerSocket serverSocket = new ServerSocket(49900);

            while (true){
                System.out.println("(Servidor): Abriendo conexión");
                Socket socketCliente = serverSocket.accept();

                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                System.out.println("(Servidor): Leo la ruta que me envía el cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                rutaFichero = leerRutaFichero(bufferedReader);
                contenido = leerFichero(rutaFichero);

                System.out.println("(Servidor): Envío mensaje de texto al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                bufferedWriter.write(contenido);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String leerFichero(String rutaFichero) throws IOException {
        String contenido = "";

        File fichero = new File(rutaFichero);
        if (fichero.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero));
            contenido = bufferedReader.readLine();
            bufferedReader.close();
        }else {
            contenido = "El fichero indicado no existe";
        }

        return contenido;
    }

    private static String leerRutaFichero(BufferedReader bufferedReader) throws IOException {
        String rutaFichero;

        rutaFichero = bufferedReader.readLine();

        System.out.println("(Servidor): Ruta que ha enviado el cliente " + rutaFichero);

        return rutaFichero;
    }
}
