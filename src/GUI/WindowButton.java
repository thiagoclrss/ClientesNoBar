package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowButton extends JPanel implements ActionListener {

    public WindowButton(){

        setLayout(new FlowLayout());
        addButton();

    }

    private void addButton(){
        AddCustomerButton button = new AddCustomerButton();
        button.addActionListener(this);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
