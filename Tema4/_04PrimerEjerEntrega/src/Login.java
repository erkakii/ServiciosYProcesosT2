import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login {

    private static int contador= 0;
    private static int contadorUsuario = 0;
    private static int contadorContrasena = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre, contrasena;
        byte[] contrasenab, resumen = null;

        System.out.println("Ingrese su nombre de usuario: ");
        nombre = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        contrasena = sc.nextLine();

        try {
            if (comprobarUsuario(nombre)) {
                if (comprobarContrasena(contrasena)) {
                    System.out.println("Bienvenido :D");
                } else {
                    System.out.println("Contraseña incorrecta :(");
                }
            }else{
                System.out.println("El usuario no existe");
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean comprobarContrasena(String contrasena) {

        Hash hash = new Hash();
        byte[] resumen1, contrsenab, resumen2;
        boolean iguales = false;
        String linea, resumenHexadecimal;
        String[] contrasenaArchivo;
        contrsenab = contrasena.getBytes(StandardCharsets.UTF_8);
        try {
            resumen2 = hash.getDigest(contrsenab);
            resumenHexadecimal = String.format("%064x", new BigInteger(1, resumen2));
            BufferedReader br = new BufferedReader(new FileReader("credenciales.cre"));
            linea = br.readLine();
            while(linea != null && !iguales){
                contadorContrasena++;
                contrasenaArchivo = linea.split(" ");
                if (contadorContrasena == contadorUsuario){
                    iguales = hash.compararResumenes(contrasenaArchivo[1], resumenHexadecimal);
                }
                linea = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return iguales;
    }

    public static boolean comprobarUsuario(String nombre){
        boolean existe = false;
        //Linea que se lee del archivo
        String linea;
        //Arreglo que contiene el nombre y la contraseña
        String[] nombreArchivo;
        try {
            BufferedReader br = new BufferedReader(new FileReader("credenciales.cre"));
            linea = br.readLine();
            while(linea != null && !existe){
                contador++;
                //Guardamos solo el nombre que es lo que nos interesa
                nombreArchivo = linea.split(" ");
                //Lo comparamos con el nombre que ingreso el usuario por teclado
                if(nombreArchivo[0].equals(nombre)){
                    existe = true;
                    contadorUsuario = contador;
                }
                linea = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return existe;
    }



}


