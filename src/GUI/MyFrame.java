package GUI;

import logic.Bar;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        setSize(1280,720);
        setScreen();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setScreen(){
        setLayout(new BorderLayout());
        WindowButton windowButton = new WindowButton();
        windowButton.setPreferredSize(new Dimension(233, 60));
        add(windowButton, BorderLayout.EAST);

        //Teclado teclado = new Teclado();
        //add(teclado, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame();

    }

}
