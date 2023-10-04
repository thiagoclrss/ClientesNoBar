package GUI;

import logic.CustomerFactory;
import logic.GUIInterface;
import logic.StatusInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CustomerStatus extends JPanel {
    CustomerCard customerCard;
    //ArrayList<Semaphore> customerAnimationMutexList;
    //String statusDinner, statusRestingAtHome, statusWaiting;
    //ArrayList<StatusActions> actionsList;
    public StatusInterface statusInterface;

    public CustomerStatus(){
        setBackground(new Color(0xB0AEAC));
        setCard();
    }

    private void setCard() {
        /*
        for(int i = 0; i < CustomerFactory.customers.size(); i++){
            customerCard = new CustomerCard(CustomerFactory.customers.get(i).getThreadId(),CustomerFactory.customers.get(i).getStatus());
            add(customerCard);
        }

         */
        customerCard = new CustomerCard("fulano", "comendo");
        customerCard.setBounds(10,10,360,100);
        add(customerCard);
    }

    private int getThreadByIndex(String id) {
        var customer = CustomerFactory.customers;

        for(int index = 0; index < customer.size(); index++){
            if(customer.get(index).getThreadId().equals(id)){
                return index;
            }
        }
        return -1;
    }

}
