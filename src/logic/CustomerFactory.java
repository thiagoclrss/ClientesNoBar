package logic;

import java.util.ArrayList;

public class CustomerFactory {

    public CustomerFactory() {

    }

    public static ArrayList<ThreadCustomers> customers = new ArrayList<>();

    public static void addCustomer(String id, Integer barTime, Integer homeTime, GUIInterface guiInterface) {
        ThreadCustomers customer = new ThreadCustomers(id, barTime, homeTime, guiInterface);
        customers.add(customer);
        customer.start();
    }
}