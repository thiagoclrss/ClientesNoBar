package GUI;

import javax.swing.*;
import java.awt.*;


public class AddCustomerButton extends JButton {
    public AddCustomerButton() {
        setText("Adicionar novo cliente");
        setPreferredSize(new Dimension(230, 30));
        setFont(new Font("courier", Font.BOLD, 15));
        setOpaque(true);
        setBackground(new Color(0xD9D9D9));
        setForeground(new Color(0x1D2782));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

    }
}
