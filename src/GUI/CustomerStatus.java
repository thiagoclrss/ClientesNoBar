package GUI;

import logic.CustomerFactory;

import javax.swing.*;
import java.awt.*;

public class CustomerStatus extends JPanel {

    public CustomerStatus(){
        setBackground(new Color(0xB0AEAC));
        setCard();    }

    private void setCard() {
    }

    private int getThreadByIndex(String id) {
        var customer = CustomerFactory.customers;

        for(int index = 0; index < customer.size(); index++){
            if(index == 1){
                //System.out.println(customer.get(1).getThreadId());
            }
            if(customer.get(index).getThreadId().equals(id)){
                return index;
            }
        }
        return -1;
    }
}
