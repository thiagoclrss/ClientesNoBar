package GUI;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        setSize(500,500);
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
        new MyFrame();
    }

}
