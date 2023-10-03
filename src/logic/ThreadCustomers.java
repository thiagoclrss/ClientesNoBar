package logic;

public class ThreadCustomers extends Thread {

    private String id;
    private  int freeChairsVerification;
    private  int availableChairsVerification;
    private Integer timeAtTheBar;
    private Integer timeAtHome;
    private Boolean bar = true;
    private Boolean permission;
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
        permission = true;
        while (true){
            goToTheBar();
            cpuBound(bar, timeAtTheBar);
            goHome();
            cpuBound(bar, timeAtHome);

        }
    }

    public void goToTheBar() {
        try {
            Bar.mutex.acquire();
            //verifco se existem threads esperando e lanço a animação de espera
            if(freeChairsVerification == Bar.emptyChairs.availablePermits()){
                guiInterface.wait(this.id);
                System.out.println(this.id + " está esperando!");
                permission = false;
            }
            //
            /*
            if(Bar.emptyChairs.hasQueuedThreads()){
                System.out.println(id + " está esperando!");
                guiInterface.wait();
            }

             */
            Bar.mutex.release();


            //se o numero de permissões é 0, o bar está lotado e devo correr por esse fluxo
            if(!permission) {
                Bar.door.acquire();
                Bar.emptyChairs.acquire();
                Bar.mutex.acquire();
                guiInterface.goToTheBar(this.id);
            } else {
                //se for true o código segue o fluxo normal

                Bar.emptyChairs.acquire();
                Bar.mutex.acquire();
                guiInterface.goToTheBar(this.id);

                //System.out.println(id + " entrou no bar!");
                bar = true;
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
            guiInterface.goHome(this.id);
            bar = false;
            System.out.println(this.id + " saiu do bar!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            if(availableChairsVerification == Bar.emptyChairs.availablePermits()) {
                Bar.door.release();
                permission = true;
            };
            Bar.mutex.release();
            Bar.emptyChairs.release();
        }


    }

    public void cpuBound(Boolean bar, long time){
        long auxPlayingTime = timeAtTheBar;
        long auxQuietTime = timeAtHome;

        if (this.bar) {
            guiInterface.dinner(id);
            System.out.println("Cliente " + this.id + " está no bar. ");
        } else {
            guiInterface.rest(id);
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
