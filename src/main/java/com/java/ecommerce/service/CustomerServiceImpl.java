package com.java.ecommerce.service;

import com.java.ecommerce.enums.Membership;
import com.java.ecommerce.enums.Status;
import com.java.ecommerce.exception.CustomerExistsException;
import com.java.ecommerce.exception.CustomerNotFoundException;
import com.java.ecommerce.model.Customer;
import com.java.ecommerce.repository.CustomerRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) throws IOException {
        this.customerRepository = customerRepository;
    }

    // CREATE
    @Override
    public Customer register(Customer customer) throws CustomerExistsException {

        if (customerRepository.exists(customer.getEmail())) {
            throw new CustomerExistsException(
                    "Customer already exists with email: " + customer.getEmail());
        }

        return customerRepository.save(customer);
    }

    // READ - By ID
    @Override
    public Customer getById(int id) throws CustomerNotFoundException {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer save(Customer customer) throws CustomerExistsException {

        if (customerRepository.exists(customer.getEmail())) {
            throw new CustomerExistsException(
                    "Customer already exists with email: " + customer.getEmail());
        }

        customer.setCreatedOn(LocalDateTime.now());
        customer.setLastLoggedIn(null);

        if (customer.getStatus() == null) {
            customer.setStatus(Status.ACTIVE);
        }

        if (customer.getMembership() == null) {
            customer.setMembership(Membership.BRONZE);
        }

        return customerRepository.save(customer);
    }

    // READ - All
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // READ - By Email
    @Override
    public Customer getByEmail(String email) throws CustomerNotFoundException {

        return customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with email: " + email));
    }

    // UPDATE
    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

        Customer existing = customerRepository.findById(customer.getId())
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with id: " + customer.getId()));

        if (!existing.getEmail().equalsIgnoreCase(customer.getEmail())
                && customerRepository.exists(customer.getEmail())) {
            throw new RuntimeException("Email already in use: " + customer.getEmail());
        }

        return customerRepository.update(customer)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found with Id : " + customer.getId()));
    }

    // DELETE
    @Override
    public void deleteCustomer(int id) {
        boolean deleted = customerRepository.deleteById(id);

        if (!deleted) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
    }

    @Override
    public boolean exists(String email) throws CustomerNotFoundException {
        return this.customerRepository.exists(email);
    }

    @Override
    public Customer login(String email, String password) throws IllegalArgumentException {

        email = email.trim().toLowerCase();

        try {
            String finalEmail = email;

            Customer customer = customerRepository
                    .findByEmail(email)
                    .orElseThrow(() ->
                            new CustomerNotFoundException(
                                    "Customer not found with email: " + finalEmail));

            if (!customer.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid email or password");
            }

            if (customer.getStatus() != null
                    && customer.getStatus() != Status.ACTIVE) {
                throw new IllegalArgumentException("Account is not active");
            }

            customer.setLastLoggedIn(LocalDateTime.now());
            customerRepository.update(customer);

            return customer;

        } catch (CustomerNotFoundException e) {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}