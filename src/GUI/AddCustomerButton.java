package GUI;

import javax.swing.*;
import java.awt.*;


public class AddCustomerButton extends JButton {
    public AddCustomerButton() {
        setText("Adicionar novo cliente.");

        setFont(new Font("courier", Font.PLAIN, 25));
        setOpaque(true);
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }
}
