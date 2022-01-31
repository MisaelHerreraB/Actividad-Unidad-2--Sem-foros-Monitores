package com.parte1;

import java.util.Random;

public class Liberar extends Thread{

    private static int consumersCount = 0;
    private int consumerId;

    public Liberar() {
        consumerId = ++consumersCount;
        start();
    }

    private void consume(){
        Random rnmNum = new Random();
        int sleepTime = rnmNum.nextInt(250 - 25 +1) +25;

        try{
            sleep(sleepTime);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Consumir el elemento
        int consumedNumber = Buffer.getStore().poll();
        System.out.println("Consumidor_" +consumerId+": Número "+ consumedNumber + " consumido.");

    }

    @Override
    public void run(){
        while (true){

            if(Buffer.getStore().size() == 0){
                System.out.println("Consumidor_"+consumerId+": El buffer está vacío, esperando a que algún productor trabaje.");
            }
            try {
                Buffer.getsNoVacio().acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            consume();
            Buffer.getsNoLleno().release();
        }
    }

}
