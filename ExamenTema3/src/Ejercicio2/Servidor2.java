package Ejercicio2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor2 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new FileWriter("Alumnos.txt", true));
        BufferedReader bfr = new BufferedReader(new FileReader("Alumnos.txt"));
        String resultado = "";
        String[] comprobacion;
        try {

            DatagramSocket socket = new DatagramSocket(49000);

            while (true){
                byte [] buffer = new byte[128];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensajeCliente = new String(packet.getData());
                mensajeCliente = mensajeCliente.trim();
                comprobacion = mensajeCliente.split(" ");

                //Comprobamos que nos ha introducido select o create par hacer una opcion u otra
                if (comprobacion[0].equalsIgnoreCase("SELECT") ){
                    resultado = recogerFichero(resultado, bfr);
                }else {
                    if (comprobacion[0].equalsIgnoreCase("CREATE") ){
                        resultado = escribirFichero(mensajeCliente, bfw);
                    }else {
                        resultado = "Escoge una opción correcta :|";
                    }
                }
                byte[] mensajeEnviado = resultado.getBytes();
                DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, packet.getAddress(), packet.getPort());
                socket.send(packetSalida);
            }
        } catch (Exception e) {
            System.out.println("Error :(");
        }finally {
            bfw.close();
            bfr.close();
        }
    }

    /**
     * Metodo que recoge el contenido del fichero
     * @param resultado variable que almacena el contenido del fichero
     * @param bfr buffer de lectura
     * @return resultado
     */
    private static String recogerFichero(String resultado, BufferedReader bfr) {
        String linea = "";
        resultado = "";
        try {
            while ((linea = bfr.readLine()) != null) {
                resultado += linea + "\n";
            }
        } catch (IOException e) {
            resultado = "Error leyendo el fichero";
        }

        return resultado;
    }

    /**
     * Metodo que escribe en el fichero
     * @param mensajeCliente mensaje que recibe el servidor
     * @param bfw buffer de escritura
     * @return resultado
     */
    private static String escribirFichero(String mensajeCliente, BufferedWriter bfw) {
        String resultado = "";
        String [] mensaje = mensajeCliente.split(" ");
        String escribir = mensaje[1] + " " + mensaje[2];
        try {
            bfw.write(escribir);
            bfw.newLine();
            bfw.flush();
            resultado = "Anadido con exito :D";
        } catch (IOException e) {
            resultado = "Error anadiendo al alumno :(";
        }

        return resultado;
    }


}
