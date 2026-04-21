package com.java.ecommerce.util;

import com.java.ecommerce.enums.Gender;
import com.java.ecommerce.enums.Membership;
import com.java.ecommerce.enums.Status;
import com.java.ecommerce.model.Address;
import com.java.ecommerce.model.Customer;
import com.java.ecommerce.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class CsvParser {

    public List<Product> getProductsFromCsv() throws IOException {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("products.csv");

        if (is == null) {
            throw new RuntimeException(
                    "products.csv not found in resources");
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     is,
                                     StandardCharsets.UTF_8))) {

            return br.lines()
                    .skip(1)
                    .map(this::parseProduct)
                    .toList();
        }
    }

    private Product parseProduct(String line) {

        String[] split = line.split(",");

        if (split.length < 8) {
            throw new RuntimeException(
                    "Invalid CSV row: " + line);
        }

        try {
            return Product.builder()
                    .id(Integer.parseInt(split[0].trim()))
                    .name(split[1].trim())
                    .maxRetailPrice(
                            Integer.parseInt(
                                    split[2].trim()))
                    .discountPercentage(
                            Float.parseFloat(
                                    split[3].trim()))
                    .isAvailable(
                            Boolean.parseBoolean(
                                    split[4].trim()))
                    .company(split[5].trim())
                    .category(split[6].trim())
                    .manufacturedYear(
                            Integer.parseInt(
                                    split[7].trim()))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error parsing row: " + line, e);
        }
    }

    public List<Customer> getCustomersFromCsv()
            throws IOException {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("customers.csv");

        if (is == null) {
            throw new RuntimeException(
                    "customers.csv not found in resources");
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     is,
                                     StandardCharsets.UTF_8))) {

            return br.lines()
                    .skip(1)
                    .map(this::parseCustomer)
                    .toList();
        }
    }

    private Customer parseCustomer(String data) {

        String[] split = data.split(",", 13);

        if (split.length < 13) {
            throw new RuntimeException(
                    "Invalid CSV row: " + data);
        }

        try {

            Address residential =
                    parseAddress(split[11]);

            Address shipping =
                    parseAddress(split[12]);

            return Customer.builder()
                    .id(Integer.parseInt(split[0].trim()))
                    .name(split[1].trim())
                    .email(split[2].trim())
                    .password(split[3].trim())
                    .phoneNo(split[4].trim())
                    .age(Byte.parseByte(split[5].trim()))
                    .gender(Gender.valueOf(
                            split[6].trim()
                                    .toUpperCase()))
                    .status(Status.valueOf(
                            split[7].trim()
                                    .toUpperCase()))
                    .membership(Membership.valueOf(
                            split[8].trim()
                                    .toUpperCase()))
                    .createdOn(parseDate(split[9]))
                    .lastLoggedIn(parseDate(split[10]))
                    .residentialAddress(residential)
                    .shippingAddress(shipping)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error parsing customer row: "
                            + data, e);
        }
    }

    private Address parseAddress(
            String addressData) {

        if (addressData == null
                || addressData.isEmpty()) {
            return null;
        }

        String[] parts =
                addressData.split(":");

        if (parts.length < 9) {
            throw new RuntimeException(
                    "Invalid address: "
                            + addressData);
        }

        return Address.builder()
                .houseNo(parts[0].trim())
                .building(parts[1].trim())
                .landMark(parts[2].trim())
                .street(parts[3].trim())
                .city(parts[4].trim())
                .district(parts[5].trim())
                .state(parts[6].trim())
                .country(parts[7].trim())
                .zipCode(
                        Integer.parseInt(
                                parts[8].trim()))
                .build();
    }

    private LocalDateTime parseDate(
            String value) {

        if (value == null
                || value.isBlank()) {
            return null;
        }

        return LocalDateTime.parse(
                value.trim());
    }
}