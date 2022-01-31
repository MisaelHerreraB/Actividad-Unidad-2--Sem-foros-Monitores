package com.parte2;

import java.util.Random;

public class CocheNorte extends Thread {
    private enum STATE{ENESPERANORTE, VERIFICADISPONIBILIDAD, CRUZANDONORTE};
    private STATE state;
    private String placa;
    private int id;
    private static int totalCoches;
    private MonitorDeTrafico monitor;
    private int vecesComido = 0;

    public CocheNorte(String p, MonitorDeTrafico m){
        id = totalCoches++;
        placa = p;
        state = STATE.ENESPERANORTE;
        monitor = m;
        start();
    }

    private void enEsperaNorte(){
        System.out.println(placa+" empieza a esperar para ir al norte. Id Norte: "+id);
        Random rnd = new Random();
        int esperarTime = rnd.nextInt(250 - 50 + 1) +50;

        try{
            sleep(esperarTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(placa+" dejar de esperar para ir al norte. Id Norte: "+id);
        state = STATE.VERIFICADISPONIBILIDAD;
    }

    private void verificaDisponibilidad(){
        System.out.println(placa+" quiere cruzar y va al puente a verificar si puede cruzar al norte. Id Norte: "+id);
        monitor.usarPuente();
        state= STATE.CRUZANDONORTE;
    }

    private void cruzandoNorte(){
        System.out.println(placa+" empieza a cruzar al norte. Id Norte: "+id);
        Random rnd = new Random();
        int cruzandoTime = rnd.nextInt(250 - 50 + 1) +50;
        try {
            sleep(cruzandoTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        vecesComido++;
        System.out.println(placa+" termina de cruzar al norte. Id Norte: "+id + " veces cruzado: "+vecesComido);
        monitor.liberarPuente();
        state = STATE.ENESPERANORTE;
    }


    @Override
    public void run(){
        while(true){
            switch (state){
                case ENESPERANORTE:
                    enEsperaNorte();
                    break;
                case VERIFICADISPONIBILIDAD:
                    verificaDisponibilidad();
                    break;
                case CRUZANDONORTE:
                    cruzandoNorte();
                    break;
            }
        }
    }
}
