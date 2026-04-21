package com.java.ecommerce.controller;

import com.java.ecommerce.exception.CustomerNotFoundException;
import com.java.ecommerce.model.Customer;
import com.java.ecommerce.service.CustomerService;

import java.io.IOException;
import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) throws IOException {
        this.customerService = customerService;
    }

    public Customer getCustomerById(int id) throws CustomerNotFoundException {
        return customerService.getById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
        return customerService.getByEmail(email);
    }

    public Customer updateCustomer(Customer customer) throws Exception {
        return customerService.updateCustomer(customer);
    }

    public void deleteCustomer(int id) throws Exception {
        customerService.deleteCustomer(id);
    }
}
