package GUI;

import logic.Bar;
import logic.ThreadCustomers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowButton extends JPanel implements ActionListener {
    JTextField fiedlID;
    JTextField fieldTimeAtTheBar;
    JTextField fieldTimeAtHome;
    Integer barTime;
    Integer homeTime;
    AddCustomerButton button;
    public WindowButton(){
        setBackground(new Color(0xB0AEAC));
        //setBorder(BorderFactory.createLineBorder(new Color(0xB1942D), 3));
        //setLayout(new FlowLayout());
        addButton();

    }

    private void addButton(){
        //id
        fiedlID = new CustomTextField("NOME: ");
        add(fiedlID);
        //tempo no bar
        fieldTimeAtTheBar = new CustomTextField("TEMPO NO BAR:");
        add(fieldTimeAtTheBar);
        //tempo em casa
        fieldTimeAtHome = new CustomTextField("TEMPO EM CASA:");
        add(fieldTimeAtHome);
        //
        button = new AddCustomerButton();
        button.addActionListener(this);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            String id = fiedlID.getText();
            try {
                barTime = Integer.parseInt(fieldTimeAtTheBar.getText());
                homeTime = Integer.parseInt(fieldTimeAtHome.getText());
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }

            Thread  customer = new Thread(new ThreadCustomers(id, barTime, homeTime));
            customer.start();
        }
    }
}
