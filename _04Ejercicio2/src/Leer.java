import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Leer {
    public static void main(String[] args) {
        //genere la clave y descifre el texto que se encuentra en el fichero
        //Las acciones se realizan en la clase Funcionalidad
        //mostrar el texto descifrado por pantalla
        Funcionalidad funcionalidad = new Funcionalidad();
        Scanner sc = new Scanner(System.in);
        String password, texto = "";
        System.out.println("Ingrese la contraseña: ");
        password = sc.nextLine();
        texto = leer();
        texto = funcionalidad.descifrar(password, texto);
        System.out.println("El texto es: " + texto);
    }

    private static String leer() {
        String password = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("pass.cre"));
            password = br.readLine();
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return password;
    }
}
