package GUI;

import logic.Bar;

import javax.swing.*;

public class MyFrame2 extends JFrame {
    MyPanel panel;

    public MyFrame2() {
        panel = new MyPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public MyPanel getPanel() {
        return panel;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public static void main(String[] args) {
        Bar bar = new Bar(1);
        new MyFrame2();


    }
}
