package GUI;

import logic.Bar;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        setSize(1280,720);
        getContentPane().setBackground(new Color(0x787676));
        setScreen();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setScreen(){
        setLayout(null);
        WindowButton windowButton = new WindowButton();
        windowButton.setBounds(990, 15,260,240);
        //windowButton.setPreferredSize(new Dimension(260, 240));
        add(windowButton);

        Display animation = new Display();
        animation.setBounds(288, 15,690,370);
        //animation.setPreferredSize(new Dimension(320, 400));
        add(animation);

        CustomerStatus cardCustomer = new CustomerStatus();
        cardCustomer.setBounds(15,15, 260, 620);
        add(cardCustomer);

        Log log = new Log();
        log.setBounds(288, 405,690,230);
        //log.setPreferredSize(new Dimension(320,200));
        add(log);
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame();


    }

}
