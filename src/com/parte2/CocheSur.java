package com.parte2;

import java.util.Random;

public class CocheSur extends Thread {
    private enum STATE{ENESPERASUR, VERIFICADISPONIBILIDAD, CRUZANDOSUR};
    private STATE state;
    private String placa;
    private int id;
    private static int totalCoches;
    private MonitorDeTrafico monitor;
    private int vecesComido = 0;

    public CocheSur(String p, MonitorDeTrafico m){
        id = totalCoches++;
        placa = p;
        state = STATE.ENESPERASUR;
        monitor = m;
        start();
    }

    private void enEsperaSur(){
        System.out.println(placa+" empieza a esperar para ir al sur. Id Sur: "+id);
        Random rnd = new Random();
        int esperarTime = rnd.nextInt(250 - 50 + 1) +50;

        try{
            sleep(esperarTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(placa+" dejar de esperar para ir al sur. Id Sur: "+id);
        state = STATE.VERIFICADISPONIBILIDAD;
    }

    private void verificaDisponibilidad(){
        System.out.println(placa+" quiere cruzar y va al puente a verificar si puede cruzar al sur. Id Sur: "+id);
        monitor.usarPuente();
        state= STATE.CRUZANDOSUR;
    }

    private void cruzandoSur(){
        System.out.println(placa+" empieza a cruzar al sur. Id Sur: "+id);
        Random rnd = new Random();
        int cruzandoTime = rnd.nextInt(250 - 50 + 1) +50;
        try {
            sleep(cruzandoTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        vecesComido++;
        System.out.println(placa+" termina de cruzar al sur. Id Sur: "+id + " veces cruzado: "+vecesComido);
        monitor.liberarPuente();
        state = STATE.ENESPERASUR;
    }


    @Override
    public void run(){
        while(true){
            switch (state){
                case ENESPERASUR:
                    enEsperaSur();
                    break;
                case VERIFICADISPONIBILIDAD:
                    verificaDisponibilidad();
                    break;
                case CRUZANDOSUR:
                    cruzandoSur();
                    break;
            }
        }
    }
}
