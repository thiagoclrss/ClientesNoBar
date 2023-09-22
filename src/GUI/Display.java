package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 690;
    final int PANEL_HEIGHT = 370;
    Image background, customer, restaurant, home, backgroundScaled;
    int x;
    Timer timer;
    public Display(){
        //
        x= 1;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        background = new ImageIcon("assets/cityBackground2.jpg").getImage();
        backgroundScaled = background.getScaledInstance(600,300, Image.SCALE_SMOOTH);

        //timer = new Timer(10,this);
        //timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(backgroundScaled,0,0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        while (x==1){repaint();}

    }

}
