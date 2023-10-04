package logic;

import java.util.concurrent.Semaphore;

public class Bar {
    public static Semaphore chairs, mutex;
    public static  int chairQnt;


    public Bar(int chairQnt) {
        chairs = new Semaphore(chairQnt);
        mutex = new Semaphore(1);
        this.chairQnt = chairQnt;
    }
}