package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 690;
    final int PANEL_HEIGHT = 370;
    Image background, home, bar, customer;
    //Image customer;
    int x=0;
    Timer timer;
    public Display(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground2.jpg").getImage();
        background = background.getScaledInstance(690,370, Image.SCALE_SMOOTH);

        home = new ImageIcon("assets/newHouse.png").getImage();
        home = home.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        bar = new ImageIcon("assets/restaurant.png").getImage();
        bar = bar.getScaledInstance(180,180,Image.SCALE_SMOOTH);

        customer = new ImageIcon("assets/hatGuy.png").getImage();
        customer = customer.getScaledInstance(150,150,Image.SCALE_SMOOTH);

        timer = new Timer(10,this);
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background,0,0, this);
        g2D.drawImage(bar, 495,385 - bar.getHeight(null), null);
        g2D.drawImage(home, 20, 390 - home.getHeight(null) , null);
        g2D.drawImage(customer, 150,260,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        while (x < 370){
            repaint();
            x++;
        }

    }

}
