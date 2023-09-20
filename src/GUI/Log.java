package GUI;

import javax.swing.*;
import java.awt.*;

public class Log extends JPanel {
    private JTextArea logTextArea;

    public Log(){
        // setBackground(Color.BLACK);
        logTextArea = new JTextArea(20, 50);
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane);
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }
}
