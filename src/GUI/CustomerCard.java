package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomerCard extends JPanel {
    //private JTextArea textAreaTime;

    public static JLabel iDThreadLabel = new JLabel("");
    public static JLabel statusLabel = new JLabel("");

    JLabel timeLabel;

    public CustomerCard(){
        setLayout(new BorderLayout());
        //iDThreadLabel.setText(id);
        //statusLabel.setText(status);
        //timeLabel.setText(time);
        setBackground(new Color(0x787676));
        setPreferredSize(new Dimension(250,100));
        setFields();
    }

    void setFields(){
        //
        iDThreadLabel.setForeground(new Color(0xC60808));
        iDThreadLabel.setFont(new Font("courier", Font.BOLD, 20));
        //iDThreadLabel.setLocation(130, 30);
        statusLabel.setForeground(new Color(0xC60808));
        statusLabel.setFont(new Font("courier", Font.BOLD, 25));
        //statusLabel.setLocation(130,130);

        //timeLabel.setForeground(new Color(0xC60808));
        //timeLabel.setFont(new Font("courier", Font.BOLD, 15));

        //add(iDThreadLabel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        //add(timeLabel);
    }

    // public JTextArea getCardTextArea( return textAreaTime );

}