import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Funcionalidad {

    static  final int LONGITUD_CLAVE = 16;
    static final String ALGORITMO = "AES/ECB/PKCS5Padding";


    /**
     * Consigue la key a partir de la contraseña
     * @param password Contraseña
     * @return Key
     */
    public Key obtenerClave(String password) {
        Key clave = new SecretKeySpec(password.getBytes(), 0, LONGITUD_CLAVE, "AES");
        return clave;
    }

    /**
     * Cifra el texto
     * @param password Contraseña
     * @param texto Texto a cifrar
     * @return Texto cifrado
     */
    public String cifrar(String password, String texto) {
        String result = "";
        try{

            Key key = obtenerClave(password);
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            cifrador.init(Cipher.ENCRYPT_MODE, key);
            byte[] textoCifrado = cifrador.doFinal(texto.getBytes());

            result = Base64.getEncoder().encodeToString(textoCifrado);

        } catch (NoSuchPaddingException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return result;
    }

    /**
     * Descifra el texto
     * @param password Contraseña
     * @param texto Texto a descifrar
     * @return Texto descifrado
     */
    public String descifrar(String password, String texto) {
        String result = "";
        try{
            Key key = obtenerClave(password);
            Cipher descifrador = Cipher.getInstance(ALGORITMO);
            descifrador.init(Cipher.DECRYPT_MODE, key);
            byte[] textoDescifrado = descifrador.doFinal(Base64.getDecoder().decode(texto));

            result = new String(textoDescifrado);

        } catch (NoSuchPaddingException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println("La contrasena es incorrecta");
        } catch (InvalidKeyException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

}
