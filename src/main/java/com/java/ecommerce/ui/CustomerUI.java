package com.java.ecommerce.ui;

import com.java.ecommerce.controller.CustomerController;
import com.java.ecommerce.factory.AppFactory;
import com.java.ecommerce.model.Address;
import com.java.ecommerce.model.Customer;

public class CustomerUI {

    private Customer customer;
    private CustomerController controller;

    public CustomerUI(Customer customer) {
        this.customer = customer;

        try {
            controller = AppFactory.getCustomerController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {

        System.out.println("\n1. Account Details");
        System.out.println("2. Edit Account");
        System.out.println("3. Delete Account");

        int choice =
                InputUtil.getInt("Enter choice: ");

        switch (choice) {
            case 1 -> showDetails();
            case 2 -> editAccount();
            case 3 -> deleteAccount();
        }
    }

    private void showDetails() {
        printHeader();
        printCustomerRow(customer);
    }

    private void editAccount() {
        try {
            customer.setName(
                    InputUtil.getString(
                            "New Name: "));

            controller.updateCustomer(customer);

            System.out.println(
                    "Updated successfully");

        } catch (Exception e) {
            System.out.println(
                    e.getMessage());
        }
    }

    private void deleteAccount() {
        try {
            controller.deleteCustomer(
                    customer.getId());

            System.out.println(
                    "Deleted successfully");

            System.exit(0);

        } catch (Exception e) {
            System.out.println(
                    e.getMessage());
        }
    }

    public static void printHeader() {

        System.out.printf(
                "%-5s %-15s %-25s %-15s %-5s %-8s %-10s %-12s %-20s %-20s %-50s %-50s%n",
                "ID",
                "Name",
                "Email",
                "Phone",
                "Age",
                "Gender",
                "Status",
                "Membership",
                "Created On",
                "Last Login",
                "Residential Address",
                "Shipping Address"
        );

        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static String formatAddress(
            Address a) {

        if (a == null) {
            return "N/A";
        }

        return String.format(
                "%s, %s, %s, %s, %s, %s, %s - %d",
                a.getHouseNo(),
                a.getBuilding(),
                a.getLandMark(),
                a.getStreet(),
                a.getCity(),
                a.getState(),
                a.getCountry(),
                a.getZipCode()
        );
    }

    public static void printCustomerRow(
            Customer c) {

        System.out.printf(
                "%-5d %-15s %-25s %-15s %-5d %-8s %-10s %-12s %-20s %-20s %-50s %-50s%n",
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getPhoneNo(),
                c.getAge(),
                c.getGender(),
                c.getStatus(),
                c.getMembership(),
                c.getCreatedOn(),
                c.getLastLoggedIn(),
                formatAddress(
                        c.getResidentialAddress()),
                formatAddress(
                        c.getShippingAddress())
        );
    }
}