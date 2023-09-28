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
    ArrayList<Semaphore> animationMutexList;
    ArrayList<GUI.Action> actionsList;
    public GUIInterface guiInterface;
    int velocity = 2;
    Timer timer;
    public Display(){

        customerX = new ArrayList<>();
        customerY = new ArrayList<>();
        animationMutexList = new ArrayList<>();
        actionsList = new ArrayList<GUI.Action>();

        implementGUIInterface();

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setLayout(new FlowLayout());
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

    private void implementGUIInterface() {
        guiInterface = new GUIInterface() {
            @Override
            public void newCustomerAnimation() {
                actionsList.add(null);
                animationMutexList.add(new Semaphore(1));
                customerX.add(30);
                customerY.add(260);
                doAction(Action.NEW_CUSTOMER, actionsList.size()-1);
            }

            @Override
            public void goToTheBar(String id) {
                var index = getThreadByIndex(id);
                try{
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.GOTOBAR, index);
            }

            @Override
            public void goHome(String id) {
                var index = getThreadByIndex(id);
                try{
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                doAction(Action.GOHOME, index);
            }
        };
    }

    private int getThreadByIndex(String id) {
        var customer = CustomerFactory.customers;
        for(int index = 0; index < customer.size(); index++){
            if(Objects.equals(customer.get(index).getId(), id)){
                return index;
            }
        }
        return -1;
    }

    private void doAction(GUI.Action action, int index) {
        actionsList.set(index, action);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background,0,0, this);
        g2D.drawImage(customer, 40,260,null);
        g2D.drawImage(bar, 495,385 - bar.getHeight(null), null);
        g2D.drawImage(home, 20, 390 - home.getHeight(null) , null);

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

            if(action == Action.GOTOBAR){
                goToBarXY(i);
            }
        }

    }

    private void goToBarXY(int index) {
        goToXY(1,1, index);
    }

    private void goHomeXY(int index) {
        goToXY(1,1,index);
    }

    private void goToXY(int x, int y, int index) {

        int currentX = customerX.get(index);

        if(currentX < x){
            customerX.set(index, currentX + velocity);
        } else if(currentX > x){
            customerX.set(index, currentX + velocity);
        }

        int currentY = customerY.get(index);
        if(currentY < y) {
            customerY.set(index, currentY + velocity);
        } else if(currentY > y) {
            customerY.set(index, currentY - velocity);
        }

        if(currentY == y && currentX == x) {
            actionsList.set(index, null);
            animationMutexList.get(index).release();
        }

        repaint();

    }

}
