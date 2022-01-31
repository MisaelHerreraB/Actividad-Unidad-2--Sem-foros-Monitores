package com.parte2;

public class MonitorDeTrafico {
    private boolean puenteDisponible = true;

    public synchronized void usarPuente(){
        while(!puenteDisponible){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        puenteDisponible = false;
    }

    public synchronized void liberarPuente(){
        puenteDisponible = true;
        notify();
    }
}
