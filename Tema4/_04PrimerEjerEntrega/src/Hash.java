import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

    /**
     * Genera un resumen de un arreglo de bytes
     * @param contrasena Arreglo de bytes a resumir
     * @return Resumen del arreglo de bytes
     * @throws NoSuchAlgorithmException Si el algoritmo no existe
     */
    public byte[] getDigest(byte[] contrasena) throws NoSuchAlgorithmException {
        byte[] resumen = null;
        MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
        algoritmo.reset();
        algoritmo.update(contrasena);
        resumen = algoritmo.digest();

        return  resumen;
    }

    /**
     * Compara dos resumenes
     * @param resumen1 Resumen 1
     * @param resumen2 Resumen 2
     * @return true si son iguales, false si no lo son
     */
    public boolean compararResumenes(String resumen1, String resumen2){
        return resumen1.equals(resumen2);
    }
}
