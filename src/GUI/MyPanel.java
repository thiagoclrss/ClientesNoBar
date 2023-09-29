package GUI;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    final int FRAME_WIDTH = 1280;
    final int FRAME_HEIGHT = 720;
    public MyPanel(){
        //setSize();

        this.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        this.setBackground(new Color(0x787676));
        setLayout(new BorderLayout(10,10));
        setScreen();
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        //setResizable(false);
        setVisible(true);
    }

    private void setScreen(){
        //setLayout(null);
        //WindowButton windowButton = new WindowButton();
        //windowButton.setPreferredSize(new Dimension(260,240));
        //windowButton.setBounds(990, 25,260,240);
        //this.add(windowButton,BorderLayout.EAST);

        Display animation = new Display();
        animation.setPreferredSize(new Dimension(690,370));
        //animation.setBounds(288, 25,690,370);
        this.add(animation, BorderLayout.CENTER);

        CustomerStatus cardCustomer = new CustomerStatus();
        //cardCustomer.setBounds(15,25, 260, 620);
        cardCustomer.setPreferredSize(new Dimension(260,620));
        this.add(cardCustomer, BorderLayout.WEST);

        Log log = new Log();
        //log.setBounds(288, 415,690,230);
        log.setPreferredSize(new Dimension(690,180));
        this.add(log, BorderLayout.SOUTH);
    }
}
