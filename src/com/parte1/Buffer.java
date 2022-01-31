package com.parte1;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class Buffer {

    private static ConcurrentLinkedDeque<Integer> store = new ConcurrentLinkedDeque<Integer>();
    public static final int bSize = 50;

    private static Semaphore sNoVacio = new Semaphore(0, true);
    private static Semaphore sNoLleno = new Semaphore(bSize, true);

    public static ConcurrentLinkedDeque<Integer> getStore() {
        return store;
    }

    public static Semaphore getsNoVacio() {
        return sNoVacio;
    }

    public static Semaphore getsNoLleno() {
        return sNoLleno;
    }
}
