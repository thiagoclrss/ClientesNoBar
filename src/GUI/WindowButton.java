package GUI;

import logic.Bar;
import logic.CustomerFactory;
import logic.GUIInterface;
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
    GUIInterface guiInterface;
    AddCustomerButton button;
    public WindowButton(GUIInterface guiInterface){
        setBackground(new Color(0xB0AEAC));
        //guiInterface =
        //setBorder(BorderFactory.createLineBorder(new Color(0xB1942D), 3));
        //setLayout(new FlowLayout());

        this.guiInterface = guiInterface;

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

            CustomerFactory addCustomer = new CustomerFactory();
            addCustomer.addCustomer(
                    id,
                    barTime,
                    homeTime,
                    guiInterface
                    );
            //Thread  customer = new Thread(new ThreadCustomers(id, barTime, homeTime, GUI.MyFrame.getAnimation()));
            //customer.start();
        }
    }
}
