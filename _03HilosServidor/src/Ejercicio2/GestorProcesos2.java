package Ejercicio2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GestorProcesos2 extends Thread{
    private Socket socket;
    private OutputStream os;

    private InputStream inputStream;

    public GestorProcesos2(Socket socket) {
        this.socket = socket;


    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {

        String ruta, contenido;
        InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            System.out.println("(Servidor) Leyendo ruta del cliente...");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ruta = bufferedReader.readLine();
            System.out.println("(Servidor) Ruta recibida: " + ruta + " ");
            contenido = leerFichero(ruta);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("(Servidor) Enviando respuesta a cliente...");
            bufferedWriter.write(contenido);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String leerFichero(String ruta) {
        File fichero = new File("direcciones");
        String resultado = "Dirección no registrada";
        String direccion = ruta;
        String[] lista;
        try{
            if (fichero.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero));
                String linea = bufferedReader.readLine();
                while (linea != null) {
                    lista = (linea.split(" "));
                    if (lista[0].equals(direccion))
                        resultado = lista[1];
                    linea = bufferedReader.readLine();
                }
                bufferedReader.close();
            } else {
                resultado = "El fichero no existe";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultado;

    }
}
