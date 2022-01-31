package com.parte1;

import java.util.Random;

public class Reservar extends Thread {

    private static int producersCount = 0;
    private int producerId;

    public Reservar(){
        producerId = ++producersCount;
        start();
    }

    private void producer(){
        Random rdmNum = new Random();
        int numP = rdmNum.nextInt(999)+1;
        int sleeptime = rdmNum.nextInt(250 - 25 + 1) + 25; //Rango(max - min + 1) + min

        try{
            sleep(sleeptime);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Productor_"+ producerId +": Número "+ numP + "producido.");

        //Añadir al buffer
        Buffer.getStore().add(numP);

    }

    @Override
    public void run(){
        while (true){
            if(Buffer.getStore().size() == Buffer.bSize){
                System.out.println("Productor_" + producerId +": El buffer está lleno, esperando a que algún consumidor trabaje.");
            }

            try{
                Buffer.getsNoLleno().acquire();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            producer();

            Buffer.getsNoVacio().release();
        }
    }
}
