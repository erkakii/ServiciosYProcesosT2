import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class Registro {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String nombre, contrasena;
        byte[] contrasenab, resumen = null;
        Hash hash = new Hash();

        System.out.println("Ingrese su nombre de usuario: ");
        nombre = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        contrasena = sc.nextLine();

        try{
            contrasenab = contrasena.getBytes(StandardCharsets.UTF_8);
            resumen = hash.getDigest(contrasenab);
            contrasena =  String.format("%064x", new BigInteger(1, resumen));
            escribir(nombre, contrasena);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Escribe en el archivo
     * @param nombre Nombre del usuario
     * @param contrasena Contraseña del usuario
     */
    public static void escribir(String nombre, String contrasena){
        try{
            File archivo = new File("credenciales.cre");
            BufferedWriter bw;
            if(archivo.exists()){
                bw = new BufferedWriter(new FileWriter(archivo, true));
                bw.write(nombre + " " + contrasena);
            }else{
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(nombre + " " + contrasena);
            }
            bw.newLine();
            System.out.println("Usuario registrado correctamente :D");
            bw.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }


}