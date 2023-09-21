package logic;

public class ThreadCustomers implements Runnable {

    private String id;
    private Integer timeAtTheBar;
    private Integer timeAtHome;
    private Boolean bar = true;

    public ThreadCustomers(String id, Integer timeAtTheBar, Integer timeAtHome) {
        this.id = id;
        this.timeAtTheBar = timeAtTheBar;
        this.timeAtHome = timeAtHome;
    }

    @Override
    public void run() {
        while (true){
            goToTheBar();
            cpuBound(bar, timeAtTheBar);
            goHome();
            cpuBound(bar, timeAtHome);
        }
    }

    public void goToTheBar() {
        try {
            Bar.emptyChairs.acquire();
            Bar.mutex.acquire();
            bar = true;
            System.out.println(id + " entrou no bar!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Bar.mutex.release();
            Bar.fullChairs.release();
        }
    }
    public void goHome() {
        try {
            Bar.fullChairs.acquire();
            Bar.mutex.acquire();
            bar = false;
            System.out.println(id + " saiu do bar!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Bar.mutex.release();
            Bar.emptyChairs.release();
        }


    }

    public void cpuBound(Boolean bar, long time){
        long auxPlayingTime = timeAtTheBar;
        long auxQuietTime = timeAtHome;

        if (this.bar) {
            System.out.println("Cliente " + this.id + " esta no bar. ");
        } else {
            System.out.println("Cliente " + this.id + " esta em casa. ");
        }

        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = 0, milisegundos = 0;
        while (tempoDecorrido < time) {
            milisegundos = (System.currentTimeMillis() - tempoAtual);
            if ((milisegundos / 1000) > tempoDecorrido) {
                if (bar) auxPlayingTime--;
                else auxQuietTime--;
            }
            if (bar && auxPlayingTime == 0) {
                break;
            }
            if (bar && auxQuietTime == 0) {
                break;
            }
            tempoDecorrido = milisegundos / 1000;
        }
    }

}
