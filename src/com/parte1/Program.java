package com.parte1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer r = 0;
        Integer l = 0;
        System.out.println("El Buffer tiene un tamaño \"k\" de: 50");
        System.out.println("Introduce la cantidad \"r\" que desea reservar de golpe: ");
        System.out.print("[Respuesta]: ");
        String respect = br.readLine();
        r = Integer.parseInt(respect);
        System.out.println("Introduce la cantidad \"l\" que desea liberar de golpe: ");
        System.out.print("[Respuesta]: ");
        respect = br.readLine();
        l = Integer.parseInt( respect );

        for(int i = 0; i < r; i++){
            new Reservar();
        }
        for(int i = 0; i < l; i++){
            new Liberar();
        }
    }

}
