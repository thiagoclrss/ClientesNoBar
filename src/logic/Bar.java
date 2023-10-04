package logic;

import java.util.concurrent.Semaphore;

public class Bar {
    public static Semaphore chairs, mutex;
    public static  int chairQnt;
    public static int count = 0;


    public Bar(int chairQnt) {
        chairs = new Semaphore(chairQnt);
        mutex = new Semaphore(1);
        this.chairQnt = chairQnt;
    }
}