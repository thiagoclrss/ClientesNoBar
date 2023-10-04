package GUI;

import logic.CustomerFactory;
import logic.GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Display extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 690;
    final int PANEL_HEIGHT = 370;
    Image background, home, bar, customer;


    ArrayList<Integer> customerX;
    ArrayList<Integer> customerY;
    ArrayList<Semaphore> customerAnimationMutexList;
    ArrayList<Semaphore> labelAnimationMutexList;
    ArrayList<GUI.Action> actionsList;
    public GUIInterface guiInterface;
    int velocity = 2;
    JLabel label;
    Timer timer;
    public Display(){

        customerX = new ArrayList<>();
        customerY = new ArrayList<>();
        customerAnimationMutexList = new ArrayList<>();
        labelAnimationMutexList = new ArrayList<>();
        actionsList = new ArrayList<GUI.Action>();
        label = new JLabel();
        implementGUIInterface();

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        this.setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground2.jpg").getImage();
        background = background.getScaledInstance(690,370, Image.SCALE_SMOOTH);
        home = new ImageIcon("assets/newHouse.png").getImage();
        home = home.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        bar = new ImageIcon("assets/restaurant.png").getImage();
        bar = bar.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        customer = new ImageIcon("assets/hatGuy.png").getImage();
        customer = customer.getScaledInstance(150,150,Image.SCALE_SMOOTH);

        timer = new Timer(10,this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background,0,0, null);
        for(int i = 0; i < customerX.size(); i++){
            //label.setText(getThreadIdByIndex(i));
            g2D.drawImage(customer, customerX.get(i),260,null);
            g2D.setPaint(Color.RED);
            g2D.setFont(new Font("Ink Free", Font.BOLD,17));
            g2D.drawString(CustomerFactory.customers.get(i).getThreadId(),customerX.get(i) + 60, 300);


        }

        g2D.drawImage(bar, 495,385 - bar.getHeight(null), null);
        g2D.drawImage(home, 20, 390 - home.getHeight(null) , null);
    }

     void implementGUIInterface() {
        guiInterface = new GUIInterface() {
            @Override
            public void newCustomerAnimation() {
                actionsList.add(null);
                customerAnimationMutexList.add(new Semaphore(1));
                labelAnimationMutexList.add(new Semaphore(1));
                customerX.add(30);
                customerY.add(260);
                doAction(Action.NEW_CUSTOMER, actionsList.size()-1);
            }

            @Override
            public void goToTheBar(String id) {
                var index = getThreadByIndex(id);
                try{
                    customerAnimationMutexList.get(index).acquire();
                    labelAnimationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.GOTOBAR, index);
            }

            @Override
            public void goHome(String id) {
                var index = getThreadByIndex(id);
                try{
                    customerAnimationMutexList.get(index).acquire();
                    labelAnimationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.GOHOME, index);
            }

            @Override
            public void dinner(String id) {
                var index = getThreadByIndex(id);
                try {
                    customerAnimationMutexList.get(index).acquire();
                    labelAnimationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.DINNER, index);
            }

            @Override
            public void rest(String id) {
                var index = getThreadByIndex(id);
                try {
                    customerAnimationMutexList.get(index).acquire();
                    labelAnimationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.REST, index);
            }

            @Override
            public void wait(String id) {
                var index = getThreadByIndex(id);
                try {
                    customerAnimationMutexList.get(index).acquire();
                    labelAnimationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.WAIT, index);
            }
        };
    }

    private int getThreadByIndex(String id) {
        var customer = CustomerFactory.customers;
        for(int index = 0; index < customer.size(); index++){
            if(customer.get(index).getThreadId().equals(id)){
                return index;
            }
        }
        return -1;
    }

    private void doAction(GUI.Action action, int index) {
        actionsList.set(index, action);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < actionsList.size(); i++){
            var action = actionsList.get(i);

            if(action == Action.NEW_CUSTOMER){
                repaint();
            }
            if(action == Action.GOHOME){
                goHomeXY(i);
            }
            if(action == Action.REST){
                goHomeXY(i);
            }
            if(action == Action.GOTOBAR){
                goToBarXY(i);
            }
            if(action == Action.DINNER){
                goToBarXY(i);
            }
            if(action == Action.WAIT){
                waitXY(i);
            }

        }

    }

    private void waitXY(int index) {
        goToXY(430 - (index * 40) ,260, index);
    }

    private void goToBarXY(int index) {
        goToXY(490,260, index);
    }

    private void goHomeXY(int index) {
        goToXY(30,260,index);
    }

    private void goToXY(int x, int y, int index) {

        int currentX = customerX.get(index);

        if(currentX < x){
            customerX.set(index, currentX + velocity);
        } else if(currentX > x){
            customerX.set(index, currentX - velocity);
        }


        int currentY = customerY.get(index);
        if(currentY < y) {
            customerY.set(index, currentY + velocity);
        } else if(currentY > y) {
            customerY.set(index, currentY - velocity);
        }

        if(currentY == y && currentX == x) {
            actionsList.set(index, null);
            customerAnimationMutexList.get(index).release();
            labelAnimationMutexList.get(index).release();
        }
        repaint();
    }

    public GUIInterface getGuiInterface() {
        return guiInterface;

    }
}
