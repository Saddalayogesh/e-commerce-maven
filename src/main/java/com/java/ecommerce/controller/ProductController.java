package com.java.ecommerce.controller;

import com.java.ecommerce.exception.ProductExistsException;
import com.java.ecommerce.exception.ProductNotFoundException;
import com.java.ecommerce.model.Product;
import com.java.ecommerce.service.ProductService;
import com.java.ecommerce.service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

    public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService) throws IOException {
            this.productService = productService;
        }

        public Product save(Product product) throws ProductExistsException {
            return productService.save(product);
        }

        public Product getById(int id) throws ProductNotFoundException {
            return productService.getById(id);
        }

        public List<Product> getAll() {
            return productService.getAll();
        }

        public Product update(int id, Product product) throws ProductNotFoundException {
            return productService.update(id, product);
        }

        public void delete(int id) throws ProductNotFoundException {
            productService.delete(id);
        }

        public List<Product> getProductsByAvailability(boolean isAvailable) {
            return productService.getProductsByAvailability(isAvailable);
        }

        public List<Product> getProductsByCategory(String category) {
            return productService.getProductsByCategory(category);
        }

        public List<Product> getProductsByPriceGreaterThan(int price) {
            return productService.getProductsByPriceGreaterThan(price);
        }

        public List<Product> getProductsByPriceLessThan(int price) {
            return productService.getProductsByPriceLessThan(price);
        }

        public List<Product> getProductsAfterYear(int year) {
            return productService.getProductsAfterYear(year);
        }

        public List<Product> getAvailableProductsAbovePrice(double price) {
            return productService.getAvailableProductsAbovePrice(price);
        }

        public List<String> getAllProductNames() {
            return productService.getAllProductNames();
        }

        public long countProductsBasedOnAvailability(boolean availableStatus) {
            return productService.countProductsBasedOnAvailability(availableStatus);
        }

        public boolean hasProductFromCompany(String company) {
            return productService.hasProductFromCompany(company);
        }

        public boolean areAllProductsAvailable() {
            return productService.areAllProductsAvailable();
        }

        public Optional<Product> findFirstProduct() {
            return productService.findFirstProduct();
        }

        public List<String> getUniqueCategories() {
            return productService.getUniqueCategories();
        }

        public List<Product> getTopNExpensiveProducts(int n) {
            return productService.getTopNExpensiveProducts(n);
        }

        public List<Product> sortProductsByPriceAsc() {
            return productService.sortProductsByPriceAsc();
        }

        public List<Product> sortProductsByNameDesc() {
            return productService.sortProductsByNameDesc();
        }

        public Integer getTotalInventoryValue() {
            return productService.getTotalInventoryValue();
        }

        public double getTotalDiscountedValue() {
            return productService.getTotalDiscountedValue();
        }

        public Map<String, Long> countProductsByCategory() {
            return productService.countProductsByCategory();
        }

        public Map<String, List<Product>> groupProductsByCategory() {
            return productService.groupProductsByCategory();
        }

        public Map<String, List<Product>> groupProductsByCompany() {
            return productService.groupProductsByCompany();
        }

        public Map<Boolean, List<Product>> partitionByAvailability() {
            return productService.partitionByAvailability();
        }

        public Product getMaxPricedProduct() throws ProductNotFoundException {
            return productService.getMaxPricedProduct();
        }

        public Product getMinPricedProduct() throws ProductNotFoundException {
            return productService.getMinPricedProduct();
        }

        public Map<Integer, Product> getProductMapById() {
            return productService.getProductMapById();
        }

        public Map<String, Double> getAveragePriceByCategory() {
            return productService.getAveragePriceByCategory();
        }

        public Map<String, List<Product>> getTop3ProductsByCategory() {
            return productService.getTop3ProductsByCategory();
        }
    }