package logic;

import java.util.ArrayList;

public class CustomerFactory {

    //String id;
    //Integer barTime;
    //Integer homeTime;
    public static ArrayList<Thread> customers;
    public CustomerFactory(){
        //this.id = id;
        //this.barTime = barTime;
        //this.homeTime = homeTime;
        customers = new ArrayList<>();
    }

    public void addCustomer(String id, Integer barTime, Integer homeTime, GUIInterface guiInterface){
        Thread  customer = new Thread(new ThreadCustomers(id, barTime, homeTime, guiInterface));
        customers.add(customer);
        customer.start();

    }

}
