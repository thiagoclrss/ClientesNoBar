package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField(){
        setPreferredSize(new Dimension(210,30));
        setFont(new Font("Consolas", Font.PLAIN,15));
        setForeground(new Color(0xC60808));
        setBackground(new Color(0xD9D9D9));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        setCaretColor(new Color(0xB1942D));
    }

}
