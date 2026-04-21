package com.java.ecommerce.ui;

import com.java.ecommerce.controller.AuthController;
import com.java.ecommerce.enums.Gender;
import com.java.ecommerce.model.Address;
import com.java.ecommerce.model.Customer;

public class AuthUI {

    private AuthController authController;

    public AuthUI(AuthController authController) {
        this.authController = authController;
    }

    public void signup() {
        try {

            System.out.println("\nEnter Basic Details:");

            int id = InputUtil.getInt("ID: ");
            String name = InputUtil.getString("Name: ");
            String email = InputUtil.getString("Email: ");
            String phone = InputUtil.getString("Phone No: ");
            String password = InputUtil.getString("Password: ");
            byte age = InputUtil.getByte("Age: ");

            Gender gender = Gender.valueOf(
                    InputUtil.getString("Gender (MALE/FEMALE): ")
                            .toUpperCase()
            );

            Address residential = readAddress("Residential");

            String same = InputUtil.getString(
                    "Is Shipping same as Residential? (yes/no): ");

            Address shipping;

            if (same.equalsIgnoreCase("yes")) {
                shipping = residential;
            } else {
                shipping = readAddress("Shipping");
            }

            Customer customer = Customer.builder()
                    .id(id)
                    .name(name)
                    .email(email)
                    .phoneNo(phone)
                    .password(password)
                    .age(age)
                    .gender(gender)
                    .residentialAddress(residential)
                    .shippingAddress(shipping)
                    .build();

            Customer savedCustomer =
                    authController.signup(customer);

            System.out.println(
                    "Welcome, "
                            + savedCustomer.getGender()
                            .getSalutation()
                            + " "
                            + savedCustomer.getName()
                            + " ✅ Registration Successful"
            );

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void login() {
        try {
            String email =
                    InputUtil.getString("Email: ");

            String password =
                    InputUtil.getString("Password: ");

            Customer customer =
                    authController.getCustomerByEmail(email);

            if (!customer.getPassword()
                    .equals(password)) {

                System.out.println(
                        "❌ Invalid credentials");
                return;
            }

            System.out.println(
                    "✅ Welcome, "
                            + customer.getGender()
                            .getSalutation()
                            + " "
                            + customer.getName()
            );

            new DashboardUI(customer).show();

        } catch (Exception e) {
            System.out.println(
                    "❌ Login failed: "
                            + e.getMessage());
        }
    }

    private Address readAddress(String type) {

        System.out.println(
                "\nEnter " + type + " Address:");

        return Address.builder()
                .houseNo(
                        InputUtil.getString(
                                "House No: "))
                .building(
                        InputUtil.getString(
                                "Building: "))
                .landMark(
                        InputUtil.getString(
                                "Landmark: "))
                .street(
                        InputUtil.getString(
                                "Street: "))
                .city(
                        InputUtil.getString(
                                "City: "))
                .district(
                        InputUtil.getString(
                                "District: "))
                .state(
                        InputUtil.getString(
                                "State: "))
                .country(
                        InputUtil.getString(
                                "Country: "))
                .zipCode(
                        InputUtil.getInt(
                                "Zip Code: "))
                .build();
    }
}