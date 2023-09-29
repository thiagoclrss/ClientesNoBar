package GUI;

import logic.Bar;
import logic.GUIInterface;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    final int FRAME_WIDTH = 1280;
    final int FRAME_HEIGHT = 720;
    public GUIInterface teste;
    Display animation;
    public MyFrame() {
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        getContentPane().setBackground(new Color(0x787676));
        //this.pack();
        setScreen();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setScreen(){
        setLayout(null);

        this.animation = new Display();
        animation.setBounds(288, 25,690,370);
        this.teste = animation.getGuiInterface();
        add(animation);

        WindowButton windowButton = new WindowButton(teste);
        windowButton.setBounds(990, 25,260,240);
        add(windowButton);

        CustomerStatus cardCustomer = new CustomerStatus();
        cardCustomer.setBounds(15,25, 260, 620);
        add(cardCustomer);

        Log log = new Log();
        log.setBounds(288, 415,690,230);
        add(log);
    }

    public Display getAnimation() {
        return animation;
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame();


    }

}
