import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Escribir {


    public static void main(String[] args) {
        //aqui generaremos la clave cifraremos un texto y lo almacenaremos en un fichero.
        //Texto que se cifra se pide por teclado
        //Las acciones se realizan en la clase Funcionalidad
        Funcionalidad funcionalidad = new Funcionalidad();
        Scanner sc = new Scanner(System.in);
        String texto, password;

        do {
            System.out.println("Ingrese la contraseña: ");
            password = sc.nextLine();
        }while (password.length() != 16);

        System.out.println("Ingrese el texto a cifrar: ");
        texto = sc.nextLine();

        texto = funcionalidad.cifrar(password, texto);

        escribir(texto);
    }

    //metodo que escriba en un fichero el texto cifrado
    public static void escribir(String texto) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("pass.cre"));
            bw.write(texto);
            bw.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}