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
    String id;
    GUIInterface guiInterface;
    AddButton button;
    public WindowButton(GUIInterface guiInterface){
        setBackground(new Color(0xB0AEAC));
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
        button = new AddButton("Adicionar novo cliente");
        button.addActionListener(this);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            id = fiedlID.getText();
            try {
                barTime = Integer.parseInt(fieldTimeAtTheBar.getText());
                homeTime = Integer.parseInt(fieldTimeAtHome.getText());
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }

            CustomerFactory customerCustomer = new CustomerFactory();
            customerCustomer.addCustomer(
                    id,
                    barTime,
                    homeTime,
                    guiInterface
                    );

        }
    }
}
