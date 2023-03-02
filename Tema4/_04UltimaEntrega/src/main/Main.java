package main;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        File fichero = new File("mensaje.txt");

        KeyPair claveEmisor = Utilidades.generadorClaves();
        KeyPair claveReceptor = Utilidades.generadorClaves();

        //Llamamos a los metodos para cifrar y descifrar
        cifrarFichero(fichero,claveEmisor,claveReceptor);
        descifrarFichero(claveEmisor,claveReceptor);
    }

    /**
     * El metodo leera un fichero donde se encuentra el mensaje cifrado y lo descifrará para postreiormente mostrarlo
     * por pantalla
     * @param claveEmisor clave del emisor
     * @param claveReceptor clave del receptor
     */
    private static void descifrarFichero(KeyPair claveEmisor, KeyPair claveReceptor) {

        int blockSize, offset = 0;

        try {
            PublicKey claveEmisorPublic = claveEmisor.getPublic();
            PrivateKey claveReceptorPrivate = claveReceptor.getPrivate();
            FileReader fr = new FileReader(new File("mensajeCifrado.txt"));
            BufferedReader bfr = new BufferedReader(fr);

            String textoCifrado = bfr.readLine().trim();

            byte[] textoCifradoBytes = Base64.getDecoder().decode(textoCifrado);
            Cipher cipherEmisor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher cipherReceptor = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipherEmisor.init(Cipher.DECRYPT_MODE, claveEmisorPublic);
            cipherReceptor.init(Cipher.DECRYPT_MODE, claveReceptorPrivate);

            blockSize = (((RSAPublicKey)claveEmisorPublic).getModulus().bitLength() + 7) / 8;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (offset < textoCifradoBytes.length) {
                int bloqueActual = Math.min(blockSize, textoCifradoBytes.length - offset);
                byte[] textoCifradoBloque = Arrays.copyOfRange(textoCifradoBytes, offset, offset + bloqueActual);
                byte[] textoDescifradoBloque = cipherReceptor.doFinal(textoCifradoBloque);

                baos.write(textoDescifradoBloque);
                offset += bloqueActual;
            }

            byte[] textoDescifradoBytes = baos.toByteArray();

            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();

            offset = 0;
            while (offset < textoDescifradoBytes.length) {
                int bloqueActual = Math.min(blockSize, textoDescifradoBytes.length - offset);
                byte[] textoDescifradoBloque = Arrays.copyOfRange(textoDescifradoBytes, offset, offset + bloqueActual);
                byte[] textoDescifradoBloque2 = cipherEmisor.doFinal(textoDescifradoBloque);

                baos2.write(textoDescifradoBloque2);
                offset += bloqueActual;
            }

            byte[] textoDescifradoBytes2 = baos2.toByteArray();

            String mensaje = new String(textoDescifradoBytes2);
            System.out.println("El mensaje escrito en el fichero es: " + mensaje);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * El método cifrará el contenido del fichero en donde escribimos nuestro mensaje
     * y usando las claves del receptor y del emisor lo cifrará y guaradará el resultado
     * cifrado en otro fichero
     * @param fichero fichero donde se encunetra el mensaje
     * @param claveEmisor clave del emisor
     * @param claveReceptor clave del receptor
     */
    private static void cifrarFichero(File fichero, KeyPair claveEmisor, KeyPair claveReceptor) {
        StringBuilder cadena = new StringBuilder();
        String linea;
        byte[] textoCifrado, textoACifrar;
        int blockSize = 0;
        int offset = 0;

        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fichero));
            FileWriter fw = new FileWriter(new File("mensajeCifrado.txt"));

            Cipher cipherEmisor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher cipherReceptor = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            PrivateKey claveEmisorPrivate = claveEmisor.getPrivate();
            PublicKey claveReceptorPublic = claveReceptor.getPublic();

            cipherEmisor.init(Cipher.ENCRYPT_MODE, claveEmisorPrivate);
            cipherReceptor.init(Cipher.ENCRYPT_MODE, claveReceptorPublic);

            linea = bfr.readLine();
            while(linea != null){
                cadena.append(linea);
                linea = bfr.readLine();
            }

             textoACifrar = cadena.toString().getBytes();
             textoCifrado = cipherEmisor.doFinal(textoACifrar);

             blockSize = (((RSAPublicKey)claveReceptorPublic).getModulus().bitLength() +7) / 8 - 11;

            ByteArrayOutputStream bs = new ByteArrayOutputStream();

            while(offset < textoCifrado.length){
                int bloqueActual = Math.min(blockSize, textoCifrado.length - offset);
                byte[] textoCifradoBloque = cipherReceptor.doFinal(textoCifrado, offset, bloqueActual);

                bs.write(textoCifradoBloque);
                offset += bloqueActual;
            }

            byte[] textoCifradoFinal = bs.toByteArray();
            bs.close();

            String textoCifradoFinalString = Base64.getEncoder().encodeToString(textoCifradoFinal);
            fw.write(textoCifradoFinalString);
            fw.close();
            bfr.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }


    }
}
