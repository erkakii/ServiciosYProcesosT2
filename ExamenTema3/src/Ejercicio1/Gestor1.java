package Ejercicio1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Gestor1 extends Thread{

    // Atributos
    private Socket socket;

    // Constructor
    public Gestor1(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            realizarProceso();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método que realiza la petición del cliente
     */
    private void realizarProceso() {
        InputStream inputStream;
        String mensajeCliente;
        int resultado = 0;

        try {
            inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            mensajeCliente = bufferedReader.readLine();
            System.out.println("(Servidor) Comprobamos que la cadena es correcta");
            String [] numeros = mensajeCliente.split(";");


            if (comprobarCadena(numeros)){
                switch (numeros[1]){
                    case "+" ->{
                        resultado = sumar(numeros);
                    }
                    case "-" -> {
                        resultado = restar(numeros);
                    }
                    case "*" -> {
                        resultado = multiplicar(numeros);
                    }
                    case "/" -> {
                        resultado = dividir(numeros);
                    }
                }
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("(Servidor) Enviamos respuesta al cliente");
            bufferedWriter.write(String.valueOf(resultado));
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que divide dos números
     * @param numeros array de números
     * @return resultado de la división
     */
    private int dividir(String[] numeros) {
        int division;

        division = Integer.parseInt(numeros[0]) / Integer.parseInt(numeros[2]);

        return division;
    }

    /**
     * Método que multiplica dos números
     * @param numeros array de números
     * @return resultado de la multiplicación
     */
    private int multiplicar(String[] numeros) {
        int multiplicacion;

        multiplicacion = Integer.parseInt(numeros[0]) * Integer.parseInt(numeros[2]);

        return multiplicacion;
    }

    /**
     * Método que resta dos números
     * @param numeros array de números
     * @return resultado de la resta
     */
    private int restar(String[] numeros) {
        int resta;

        resta = Integer.parseInt(numeros[0]) - Integer.parseInt(numeros[2]);

        return resta;
    }

    /**
     * Método que suma dos números
     * @param numeros array de números
     * @return resultado de la suma
     */
    private int sumar(String[] numeros) {
        int suma;

        suma = Integer.parseInt(numeros[0]) + Integer.parseInt(numeros[2]);

        return suma;
    }

    /**
     * Método que comprueba que la cadena es correcta
     * @param numeros array de números
     * @return true si la cadena es correcta, false si no lo es
     */
    private boolean comprobarCadena(String[] numeros) {
        boolean cadenaCorrecta = true;

        if (!numeros[1].equals("+") &&  !numeros[1].equals("-") && !numeros[1].equals("*") &&  !numeros[1].equals("/")){
            cadenaCorrecta = false;
        }else {
            try {
                Integer.parseInt(numeros[0]);
                Integer.parseInt(numeros[2]);
            }catch (Exception e){
                cadenaCorrecta = false;
            }
        }

        return cadenaCorrecta;
    }


}
