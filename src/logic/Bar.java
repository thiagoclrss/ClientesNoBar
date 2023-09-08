package logic;

import java.util.concurrent.Semaphore;

public class Bar {
    public static Semaphore chairs;
    public static Semaphore mutex;


    public Bar(int chairQnt) {
        chairs = new Semaphore(chairQnt);
        mutex = new Semaphore(1);
    }
}