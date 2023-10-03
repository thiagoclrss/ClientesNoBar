package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField {
    private String placeholder;

    public CustomTextField(String placeholder) {
        this.placeholder = placeholder;
        setPreferredSize(new Dimension(250, 40));
        setFont(new Font("Consolas", Font.PLAIN, 15));
        setForeground(new Color(0xC60808));
        setBackground(new Color(0xD9D9D9));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        setCaretColor(new Color(0xB1942D));

        setText(placeholder);
        setForeground(Color.GRAY);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }
            }
        });
    }

    @Override
    public String getText() {
        String currentText = super.getText();
        if (currentText.equals(placeholder)) {
            return "";
        }
        return currentText;
    }
}
