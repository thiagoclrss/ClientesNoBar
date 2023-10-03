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
    JLabel nameLabel;
    JLabel timeAtTheBarLabel;
    JLabel timeAtHomeLabel;
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
        nameLabel = new JLabel("NOME ");
        nameLabel.setForeground(new Color(0xC60808));
        nameLabel.setFont(new Font("courier", Font.BOLD, 15));
        fiedlID = new CustomTextField();
        add(nameLabel);
        add(fiedlID);
        //tempo no bar
        timeAtTheBarLabel = new JLabel("TEMPO NO BAR");
        timeAtTheBarLabel.setForeground(new Color(0xC60808));
        timeAtTheBarLabel.setFont(new Font("courier", Font.BOLD, 15));
        fieldTimeAtTheBar = new CustomTextField();
        add(timeAtTheBarLabel);
        add(fieldTimeAtTheBar);
        //tempo em casa
        timeAtHomeLabel = new JLabel("TEMPO EM CASA");
        timeAtHomeLabel.setForeground(new Color(0xC60808));
        timeAtHomeLabel.setFont(new Font("courier", Font.BOLD, 15));
        fieldTimeAtHome = new CustomTextField();
        add(timeAtHomeLabel);
        add(fieldTimeAtHome);
        //
        button = new AddButton("OK");
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

            CustomerFactory.addCustomer(
                    id,
                    barTime,
                    homeTime,
                    guiInterface
                    );
        }
    }
}
