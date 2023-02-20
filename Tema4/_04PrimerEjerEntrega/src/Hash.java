import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

    public byte[] getDigest(byte[] contrasena) throws NoSuchAlgorithmException {
        byte[] resumen = null;
        MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
        algoritmo.reset();
        algoritmo.update(contrasena);
        resumen = algoritmo.digest();

        return  resumen;
    }

    public boolean compararResumenes(byte[] resumen1, byte[] resumen2){
        boolean iguales = Arrays.equals(resumen1, resumen2);
        return iguales;
    }
}
