package GUI;

import logic.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowBarButton extends JPanel implements ActionListener {
    JTextField chairsField;
    Integer chairsQnt;
    AddButton button;
    public WindowBarButton(){
        setBackground(new Color(0xB0AEAC));
        addButton();
    }
    private void addButton(){
        //cadeiras no bar
        chairsField = new CustomTextField("CADEIRAS: ");
        add(chairsField);

        button = new AddButton("Ok");
        button.addActionListener(this);
        add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            try {
                chairsQnt = Integer.parseInt(chairsField.getText());
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }
            Bar bar = new Bar(chairsQnt);
        }
    }
}
