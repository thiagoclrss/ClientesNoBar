package logic;

import java.util.ArrayList;

public class CustomerFactory {

    public static ArrayList<ThreadCustomers> customers;
    public CustomerFactory(){
        customers = new ArrayList<>();
    }

    public void addCustomer(String id, Integer barTime, Integer homeTime, GUIInterface guiInterface){
        ThreadCustomers customer = new ThreadCustomers(id, barTime, homeTime, guiInterface);
        customers.add(customer);
        customer.start();

    }

}
