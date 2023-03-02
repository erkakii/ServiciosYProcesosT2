package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class KeyManager {
    private static final String PUBLIC_KEY_FILE = "public_key.key";
    private static final String PRIVATE_KEY_FILE = "private_key.key";

    public static KeyPair generateKeyPair() {
        KeyPair claves = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            claves = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return claves;
    }

    public static void saveKeyPair(KeyPair claves) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(PUBLIC_KEY_FILE);
            fos.write(claves.getPublic().getEncoded());
            fos.close();
            fos = new FileOutputStream(new File(PRIVATE_KEY_FILE));
            fos.write(claves.getPrivate().getEncoded());
            fos.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static PublicKey getPublicKey() {
       File ficheroClavePublica = new File(PUBLIC_KEY_FILE);
         PublicKey clavePublica = null;
         try{
             byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
             KeyFactory keyFactory = KeyFactory.getInstance("RSA");
             EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bytesClavePublica);
             clavePublica = keyFactory.generatePublic(clavePublicaSpec);

         } catch (IOException e) {
             throw new RuntimeException(e);
         } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException(e);
         } catch (InvalidKeySpecException e) {
             throw new RuntimeException(e);
         }

         return clavePublica;
    }

    public static PrivateKey getClavePrivada(){
        File ficheroClavePrivada = new File(PRIVATE_KEY_FILE);
        PrivateKey clavePrivada = null;
        try{
            byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec clavePrivadaSpec = new X509EncodedKeySpec(bytesClavePrivada);
            clavePrivada = keyFactory.generatePrivate(clavePrivadaSpec);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return clavePrivada;
    }

    public static void main(String[] args) {
        KeyPair claves = generateKeyPair();
        saveKeyPair(claves);
    }
}
