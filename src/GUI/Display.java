package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    Image background, customer, restaurant, home;
    Timer timer;
    public Display(){
        setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground.jpg").getImage();
        background = background.getScaledInstance(690,370, Image.SCALE_SMOOTH);
        timer = new Timer(10,this);
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background,0,0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
