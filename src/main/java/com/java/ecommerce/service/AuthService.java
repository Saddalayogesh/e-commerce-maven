package com.java.ecommerce.service;

import com.java.ecommerce.exception.CustomerExistsException;
import com.java.ecommerce.exception.CustomerNotFoundException;
import com.java.ecommerce.exception.InvalidCredentialsException;
import com.java.ecommerce.model.Customer;

public interface AuthService {
    Customer signup(Customer customer) throws CustomerExistsException;

    Customer login(String email, String password) throws InvalidCredentialsException;

    Customer getCustomerByEmail(String email) throws CustomerNotFoundException;
}
