package com.parte2;

public class Program {
    public static void main(String[] args){
        MonitorDeTrafico monitor = new MonitorDeTrafico();

        new CocheNorte("N456-45", monitor);
        new CocheNorte("N457-45", monitor);
        new CocheNorte("N455-56", monitor);
        new CocheNorte("N45645", monitor);
        new CocheSur("S987-45", monitor);
        new CocheSur("S456-45", monitor);
        new CocheSur("S896-65", monitor);
        new CocheSur("S890-65", monitor);
        new CocheSur("S800-65", monitor);
    }
}
