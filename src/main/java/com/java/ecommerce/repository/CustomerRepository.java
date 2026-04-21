package com.java.ecommerce.repository;

import com.java.ecommerce.model.Customer;
import com.java.ecommerce.util.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    /*
     CRUD operations on Customers
     */

    private final List<Customer> customers;
    private final CsvParser csvParser;

    public CustomerRepository(CsvParser csvParser) throws IOException {
        this.csvParser = csvParser;
        customers = csvParser.getCustomersFromCsv();
    }

    // CREATE
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    // READ - Find by ID
    public Optional<Customer> findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    // READ - Find all
    public List<Customer> findAll() {
        return customers;
    }

    // UPDATE
    public Optional<Customer> update(Customer updatedCustomer) {
        Optional<Customer> existing = findById(updatedCustomer.getId());

        existing.ifPresent(c -> {
            Customer updated = Customer.builder()
                    .id(updatedCustomer.getId())
                    .name(updatedCustomer.getName())
                    .email(updatedCustomer.getEmail())
                    .password(updatedCustomer.getPassword())
                    .phoneNo(updatedCustomer.getPhoneNo())
                    .age(updatedCustomer.getAge())
                    .gender(updatedCustomer.getGender())
                    .status(updatedCustomer.getStatus())
                    .membership(updatedCustomer.getMembership())
                    .residentialAddress(updatedCustomer.getResidentialAddress())
                    .shippingAddress(updatedCustomer.getShippingAddress())
                    .createdOn(c.getCreatedOn())
                    .lastLoggedIn(c.getLastLoggedIn())
                    .build();

            int index = customers.indexOf(c);
            customers.set(index, updated);
        });

        return existing;
    }

    // DELETE
    public boolean deleteById(int id) {
        return customers.removeIf(c -> c.getId() == id);
    }

    // FIND BY EMAIL
    public Optional<Customer> findByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // EXISTS BY EMAIL
    public boolean exists(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }
}