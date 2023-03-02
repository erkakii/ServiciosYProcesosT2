package main;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Utilidades {

    /**
     * Método que genera un par de clave
     */

    public static KeyPair generadorClaves(){
        try{
            KeyPair claves;
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            claves = kpg.generateKeyPair();
            return claves;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No existe ese algoritmo");
            throw new RuntimeException(e);
        }

    }
}
