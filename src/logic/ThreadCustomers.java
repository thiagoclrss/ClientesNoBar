package logic;

public class ThreadCustomers implements Runnable {

    private int id;
    private int timeAtTheBar;
    private int timeAtHome;
    private Boolean bar = true;

    public ThreadCustomers(int id, int timeAtTheBar, int timeAtHome) {
        this.id = id;
        this.timeAtTheBar = timeAtTheBar;
        this.timeAtHome = timeAtHome;
    }

    @Override
    public void run() {

    }

    public void goToTheBar() {
        try {
            Bar.chairs.acquire();
            cpuBound(bar, timeAtTheBar);
            System.out.printf(id + "está jantando!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Bar.chairs.release();
        }
    }
    public void goHome() {
        cpuBound(!bar, timeAtHome);
    }

    public void cpuBound(Boolean bar, long time){
        long auxPlayingTime = timeAtTheBar;
        long auxQuietTime = timeAtHome;

        if (this.bar) {
            System.out.println("Criança " + this.id + " está no bar. ");
        } else {
            System.out.println("Criança " + this.id + " está em casa. ");
        }

        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = 0, milisegundos = 0;
        while (tempoDecorrido < time) {
            milisegundos = (System.currentTimeMillis() - tempoAtual);
            if ((milisegundos / 1000) > tempoDecorrido) {
                if (ball) auxPlayingTime--;
                else auxQuietTime--;
            }
            if (ball && auxPlayingTime == 0) {
                break;
            }
            if (ball && auxQuietTime == 0) {
                break;
            }
            tempoDecorrido = milisegundos / 1000;
        }
    }
    }
}
