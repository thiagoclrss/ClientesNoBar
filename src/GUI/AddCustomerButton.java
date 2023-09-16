package GUI;

import javax.swing.*;
import java.awt.*;


public class AddCustomerButton extends JButton {
    public AddCustomerButton() {
        setText("Adicionar novo cliente");
        //setBounds(20,20, 60,60);
        setFont(new Font("courier", Font.PLAIN, 15));
        setOpaque(true);
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }
}
