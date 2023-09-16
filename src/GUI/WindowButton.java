package GUI;

import logic.Bar;
import logic.ThreadCustomers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowButton extends JPanel implements ActionListener {

    public WindowButton(){
        setBackground(Color.BLACK);
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
        System.out.println("teste");

        Thread  t1 = new Thread(new ThreadCustomers(1, 10, 10));
        t1.start();
    }
}
