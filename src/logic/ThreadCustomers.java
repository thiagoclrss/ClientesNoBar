package logic;

import GUI.CustomerCard;

public class ThreadCustomers extends Thread {

    private final String id;
    private final Integer timeAtTheBar;
    private final Integer timeAtHome;
    private Boolean bar = true;
    private String status;
    private final int ocupiedVerification;
    private final int availableVerification;
    private final GUIInterface guiInterface;


    public ThreadCustomers(String id, Integer timeAtTheBar, Integer timeAtHome, GUIInterface guiInterface) {
        this.id = id;
        this.timeAtTheBar = timeAtTheBar;
        this.timeAtHome = timeAtHome;
        this.guiInterface = guiInterface;
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
            if(Bar.count == Bar.chairQnt){
                guiInterface.wait(this.id);
                Bar.mutex.acquire();
                status = "esta esperando!";
                Bar.mutex.release();
                System.out.println(this.id + " esta esperando!");
                Bar.chairs.acquire();
                System.out.println(id + " esta indo ao bar!");
                Bar.mutex.acquire();
                status = "esta indo ao bar";
                bar = true;
                Bar.count++;
                Bar.mutex.release();
                guiInterface.goToTheBar(this.id);
            } else {
                Bar.chairs.acquire();
                guiInterface.goToTheBar(this.id);
                System.out.println(id + " esta indo ao bar!");
                Bar.mutex.acquire();
                status = "esta indo ao bar";
                Bar.count++;
                bar = true;
                Bar.mutex.release();

                //
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
            Bar.count--;
            Bar.mutex.release();
            status = "Indo para casa";
            guiInterface.goHome(this.id);
            System.out.println("\n" + this.id + " esta indo para casa!");
            Bar.mutex.acquire();
            if(Bar.count == 0){
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
        String auxLabel = "";

        if (this.bar) {
            guiInterface.dinner(id);
            status = "Está no bar";
            System.out.println("\n" + this.id + " esta no bar. \n");
        } else {
            guiInterface.rest(id);
            status = "Está em casa";
            System.out.println("\n" + this.id + " esta em casa. \n");
        }

        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = 0, milisegundos = 0;
        while (tempoDecorrido < time) {
            milisegundos = (System.currentTimeMillis() - tempoAtual);
            if ((milisegundos / 1000) > tempoDecorrido) {
                if (bar) {
                    auxPlayingTime--;
                    auxLabel = Long.toString(auxPlayingTime);
                    CustomerCard.statusLabel.setText(this.id + ": " + auxLabel);
                    // System.out.print(auxPlayingTime + 1);
                }
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