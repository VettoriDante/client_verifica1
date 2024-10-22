package com.client_verifica;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", 3000);
        String input = "";
        // creazione delle variabili di lavoro interne
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        Scanner inputReader = new Scanner(System.in);
        boolean go = true;
        String score = "";
        
        do {
            // tentativo
            System.out.println("Prova ad indovinare il numero:");
            input = inputReader.nextLine();
            out.writeBytes(input + "\n");

            String status = in.readLine();
            switch (status) {
                case "<":
                    System.out.println("Numero Troppo Piccolo");
                break;
                case ">":
                    System.out.println("Numero Troppo Grande");
                break;
                case "<>":
                    System.out.println("### Il Valore è Fuori Range ###");
                break;
                case "NN": 
                    System.out.println("### Non hai inserito un numero ###");
                case "!":
                    System.out.println("# # # ERRORE # # #");
                break;
                case "=":
                    String tentativi = in.readLine();
                    System.out.println("Complimenti hai indovinato dopo " + tentativi + " tentativi");
                    go = false;
                break;
                    default:
                    break;
            }

        } while (go);
        inputReader.close();
    }
}