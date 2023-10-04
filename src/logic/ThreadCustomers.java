package logic;

public class ThreadCustomers extends Thread {

    private final String id;
    private final Integer timeAtTheBar;
    private final Integer timeAtHome;
    private Boolean bar = true;
    private String status;
    private final int ocupiedVerification;
    private final int availableVerification;
    private final GUIInterface guiInterface;
    private int count;

    public ThreadCustomers(String id, Integer timeAtTheBar, Integer timeAtHome, GUIInterface guiInterface) {
        this.id = id;
        this.timeAtTheBar = timeAtTheBar;
        this.timeAtHome = timeAtHome;
        this.guiInterface = guiInterface;
        this.count = 0;
        availableVerification = Bar.chairQnt;
        ocupiedVerification = 0;
    }

    @Override
    public void run() {
        guiInterface.newCustomerAnimation();
        while (true){
            goToTheBar();
            cpuBound(bar, timeAtTheBar);
            goHome();
            cpuBound(bar, timeAtHome);

        }
    }

    public void goToTheBar() {
        try {
            if(ocupiedVerification == Bar.chairs.availablePermits()){
                guiInterface.wait(this.id);
                Bar.mutex.acquire();
                status = "está esperando!";
                Bar.mutex.release();
                System.out.println(this.id + " está esperando!");
                Bar.chairs.acquire();
                Bar.mutex.acquire();
                status = "esta indo ao bar";
                bar = true;
                count++;
                Bar.mutex.release();
                guiInterface.goToTheBar(this.id);
            } else {
                Bar.chairs.acquire();
                guiInterface.goToTheBar(this.id);
                Bar.mutex.acquire();
                status = "esta indo ao bar";
                count++;
                bar = true;
                Bar.mutex.release();

                //System.out.println(id + " entrou no bar!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
    public void goHome() {
        try {
            Bar.mutex.acquire();
            bar = false;
            count--;
            Bar.mutex.release();
            status = "Indo para casa";
            guiInterface.goHome(this.id);
            System.out.println(this.id + " esta indo para casa!");
            Bar.mutex.acquire();
            if(count == 0){
                Bar.chairs.release(Bar.chairQnt);
            }
            Bar.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {


        }
    }

    public void cpuBound(Boolean bar, long time){
        long auxPlayingTime = timeAtTheBar;
        long auxQuietTime = timeAtHome;

        if (this.bar) {
            guiInterface.dinner(id);
            status = "Está no bar";
            System.out.println("Cliente " + this.id + " está no bar. ");
        } else {
            guiInterface.rest(id);
            status = "Está em casa";
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
    public String getStatus() {
        return status;
    }
}