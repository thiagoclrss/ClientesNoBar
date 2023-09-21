package GUI;

import logic.Bar;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    final int FRAME_WIDTH = 1280;
    final int FRAME_HEIGHT = 720;
    public MyFrame() {
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        getContentPane().setBackground(new Color(0x787676));
        //this.pack();
        setScreen();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setScreen(){
        setLayout(null);
        WindowButton windowButton = new WindowButton();
        windowButton.setBounds(990, 25,260,240);
        //windowButton.setPreferredSize(new Dimension(260, 240));
        add(windowButton);

        Display animation = new Display();
        animation.setBounds(288, 25,690,370);
        //animation.setPreferredSize(new Dimension(690, 370));
        add(animation);

        CustomerStatus cardCustomer = new CustomerStatus();
        cardCustomer.setBounds(15,25, 260, 620);
        add(cardCustomer);

        Log log = new Log();
        log.setBounds(288, 415,690,230);
        //log.setPreferredSize(new Dimension(320,200));
        add(log);
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame();


    }

}
