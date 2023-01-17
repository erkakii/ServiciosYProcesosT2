package apuntes1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejercicio1 {
    public static void main(String[] args){
        try {
            InetAddress direccion = InetAddress.getByName("www.google.es");
            InetAddress local = InetAddress.getLocalHost();


            //Usar este siempre que el otro es caca
            System.out.println("Dirección IP: " + direccion.getHostAddress()); //Devuelve la ip en modo texto
            System.out.println("Mi dirección IP: " + local.getHostAddress());


           /* byte[] direccionIp = direccion.getAddress(); Direccion ip en un array de bits

            System.out.println(Arrays.toString(direccionIp));*/


        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }
}