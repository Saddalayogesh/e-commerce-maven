package com.java.ecommerce.service;

import com.java.ecommerce.exception.CustomerExistsException;
import com.java.ecommerce.exception.CustomerNotFoundException;
import com.java.ecommerce.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer register(Customer customer) throws CustomerExistsException;

    Customer getById(int id) throws CustomerNotFoundException;

    Customer save(Customer customer) throws CustomerExistsException;

    List<Customer> getAllCustomers();

    Customer getByEmail(String email) throws CustomerNotFoundException;

    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

    void deleteCustomer(int id);

    boolean exists(String email) throws CustomerNotFoundException;

    Customer login(String email, String password) throws IllegalArgumentException;

}
