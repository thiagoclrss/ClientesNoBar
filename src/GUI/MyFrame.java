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
        setLayout(new BorderLayout());
        WindowButton windowButton = new WindowButton();
        windowButton.setPreferredSize(new Dimension(260, 240));
        add(windowButton, BorderLayout.EAST);
        Display animation = new Display();
        add(animation, BorderLayout.CENTER);
        Log log = new Log();
        //add(animation, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame();


    }

}
