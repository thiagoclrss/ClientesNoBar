package GUI;

import javax.swing.*;
import java.awt.*;


public class AddButton extends JButton {
    public AddButton(String text) {
        setText(text);
        setPreferredSize(new Dimension(200, 40));
        setFont(new Font("courier", Font.BOLD, 15));
        setOpaque(true);
        setBackground(new Color(0xD9D9D9));
        setForeground(new Color(0x1D2782));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

    }
}
