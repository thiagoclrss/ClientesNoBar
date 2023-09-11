package logic;

import java.util.concurrent.Semaphore;

public class Bar {
    public static Semaphore fullChairs, emptyChairs, mutex;


    public Bar(int chairQnt) {
        fullChairs = new Semaphore(0);
        emptyChairs = new Semaphore(chairQnt);
        mutex = new Semaphore(1);
    }
}
