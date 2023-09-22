package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 690;
    final int PANEL_HEIGHT = 370;
    Image background, home, bar;
    //Image customer;
    int x=0;
    Timer timer;
    public Display(){
        //
        //x= 1;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground2.jpg").getImage();
        background = background.getScaledInstance(690,370, Image.SCALE_SMOOTH);

        home = new ImageIcon("assets/house.jpg").getImage();
        home = home.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        bar = new ImageIcon("assets/restaurant.png").getImage();
        bar = bar.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        timer = new Timer(10,this);
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background,0,0, this);
        g2D.drawImage(bar, 495,385 - bar.getHeight(null), null);
        g2D.drawImage(home, 20, 385 - home.getHeight(null), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        while (x < 370){
            repaint();
            x++;
        }

    }

}
