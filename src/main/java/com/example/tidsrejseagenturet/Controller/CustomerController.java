package com.example.tidsrejseagenturet.Controller;

import com.example.tidsrejseagenturet.Model.Customer;
import com.example.tidsrejseagenturet.Model.CustomerDatabaseHandler;

import java.util.List;

public class CustomerController {
    private final CustomerDatabaseHandler dbHandler;

    public CustomerController() {
        this.dbHandler = new CustomerDatabaseHandler();
    }

    public Customer createCustomer(String name, String email) {
        return dbHandler.createCustomerInDB(name, email);
    }

    public Customer login(String email, String password) {
        return dbHandler.loginCustomer(email, password);
    }

    public boolean deleteCustomer(int id) {
        return dbHandler.deleteCustomerFromDB(id);
    }

    public List<Customer> getAllCustomers() {
        return dbHandler.getAllCustomersFromDB();
    }

    public boolean editCustomer(int id, String name, String email) {
        return dbHandler.editCustomerInDB(id, name, email);
    }
}