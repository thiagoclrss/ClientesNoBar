package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 690;
    final int PANEL_HEIGHT = 370;
    Image background, customer, restaurant, home;
    Timer timer;
    public Display(){
        //
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground.jpg").getImage();
        //background = background.getScaledInstance(200,100, Image.SCALE_DEFAULT);
        //timer = new Timer(10,this);
        //timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        //g2D.setPaint(Color.blue);
        //g2D.drawOval(8,8, 5,5);
        g2D.drawImage(background,0,0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
