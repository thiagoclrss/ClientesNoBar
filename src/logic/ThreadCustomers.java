package logic;

public class ThreadCustomers extends Thread {

    private String id;
    private  int freeChairsVerification;
    private  int availableChairsVerification;
    private Integer timeAtTheBar;
    private Integer timeAtHome;
    private Boolean bar = true;
    private Boolean firstTime;
    private GUIInterface guiInterface;

    public ThreadCustomers(String id, Integer timeAtTheBar, Integer timeAtHome, GUIInterface guiInterface) {
        this.id = id;
        this.timeAtTheBar = timeAtTheBar;
        this.timeAtHome = timeAtHome;
        this.guiInterface = guiInterface;
    }

    @Override
    public void run() {
        guiInterface.newCustomerAnimation();
        freeChairsVerification = 0;
        availableChairsVerification = Bar.chairQnt;
        firstTime = true;
        while (true){
            goToTheBar();
            cpuBound(bar, timeAtTheBar);
            guiInterface.dinner(id);
            goHome();
            cpuBound(bar, timeAtHome);
            guiInterface.rest(id);
        }
    }

    public void goToTheBar() {
        try {
            //if(freeChairsVerification == Bar.emptyChairs.availablePermits()) firstTime = false;
            //while (Bar.emptyChairs.availablePermits()!=availableChairsVerification){guiInterface.rest(id);}
            if(firstTime){
                Bar.emptyChairs.acquire();
                Bar.mutex.acquire();
                guiInterface.goToTheBar(id);

                bar = true;
                System.out.println(id + " entrou no bar!");
            }
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
            guiInterface.goHome(id);
            bar = false;
            System.out.println(id + " saiu do bar!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Bar.mutex.release();
            Bar.emptyChairs.release();
            if(availableChairsVerification == Bar.emptyChairs.availablePermits()) firstTime = true;
        }


    }

    public void cpuBound(Boolean bar, long time){
        long auxPlayingTime = timeAtTheBar;
        long auxQuietTime = timeAtHome;

        if (this.bar) {
            System.out.println("Cliente " + this.id + " está no bar. ");
        } else {
            System.out.println("Cliente " + this.id + " está em casa. ");
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

    public String getThreadId() {
        return id;
    }
}
