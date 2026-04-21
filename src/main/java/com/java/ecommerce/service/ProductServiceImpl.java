package com.java.ecommerce.service;

import com.java.ecommerce.exception.ProductExistsException;
import com.java.ecommerce.exception.ProductNotFoundException;
import com.java.ecommerce.model.Product;
import com.java.ecommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws ProductExistsException {
        productRepository.findById(product.getId())
                .ifPresent(p -> {
                    throw new ProductExistsException(
                            "Product already exists with id : " + product.getId());
                });

        return productRepository.save(product);
    }

    @Override
    public Product getById(int id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id : " + id + " not found!"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(int id, Product product)
            throws ProductNotFoundException {

        productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not found with id: " + id));

        return productRepository.update(id, product);
    }

    @Override
    public void delete(int id) {
        productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not found with id: " + id));

        productRepository.delete(id);
    }

    @Override
    public List<Product> getProductsByAvailability(boolean isAvailable) {
        return productRepository.findAll().stream()
                .filter(p -> p.isAvailable() == isAvailable)
                .toList();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAll().stream()
                .filter(product ->
                        product.getCategory().equals(category))
                .toList();
    }

    @Override
    public List<Product> getProductsByPriceGreaterThan(int price) {
        return productRepository.findAll().stream()
                .filter(product ->
                        product.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public List<Product> getProductsByPriceLessThan(int price) {
        return productRepository.findAll().stream()
                .filter(product ->
                        product.getMaxRetailPrice() < price)
                .toList();
    }

    @Override
    public List<String> getAllProductNames() {
        return productRepository.findAll().stream()
                .map(Product::getName)
                .toList();
    }

    @Override
    public long countProductsBasedOnAvailability(
            boolean availableStatus) {

        return productRepository.findAll().stream()
                .filter(product ->
                        product.isAvailable() == availableStatus)
                .count();
    }

    @Override
    public boolean hasProductFromCompany(String company) {
        return productRepository.findAll().stream()
                .anyMatch(p ->
                        p.getCompany().equalsIgnoreCase(company));
    }

    @Override
    public boolean areAllProductsAvailable() {
        return productRepository.findAll().stream()
                .allMatch(Product::isAvailable);
    }

    @Override
    public Optional<Product> findFirstProduct() {
        return productRepository.findAll().stream()
                .findFirst();
    }

    @Override
    public List<String> getUniqueCategories() {
        return productRepository.findAll().stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public List<Product> getTopNExpensiveProducts(int n) {
        return productRepository.findAll().stream()
                .sorted((a, b) ->
                        Double.compare(
                                b.getMaxRetailPrice(),
                                a.getMaxRetailPrice()))
                .limit(n)
                .toList();
    }

    @Override
    public List<Product> sortProductsByPriceAsc() {
        return productRepository.findAll().stream()
                .sorted(
                        Comparator.comparing(
                                Product::getMaxRetailPrice))
                .toList();
    }

    @Override
    public List<Product> sortProductsByNameDesc() {
        return productRepository.findAll().stream()
                .sorted(
                        Comparator.comparing(
                                Product::getName).reversed())
                .toList();
    }

    @Override
    public Integer getTotalInventoryValue() {
        return productRepository.findAll().stream()
                .map(Product::getMaxRetailPrice)
                .reduce(0, Integer::sum);
    }

    @Override
    public double getTotalDiscountedValue() {
        return productRepository.findAll().stream()
                .mapToDouble(p ->
                        p.getMaxRetailPrice()
                                * p.getDiscountPercentage() / 100)
                .reduce(0.0, Double::sum);
    }

    @Override
    public List<Product> getProductsAfterYear(int year) {
        return productRepository.findAll().stream()
                .filter(p ->
                        p.getManufacturedYear() > year)
                .toList();
    }

    @Override
    public List<Product> getAvailableProductsAbovePrice(
            double price) {

        return productRepository.findAll().stream()
                .filter(Product::isAvailable)
                .filter(p ->
                        p.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public Map<String, Long> countProductsByCategory() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()));
    }

    @Override
    public Map<String, List<Product>> groupProductsByCategory() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory));
    }

    @Override
    public Map<String, List<Product>> groupProductsByCompany() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCompany));
    }

    @Override
    public Map<Boolean, List<Product>> partitionByAvailability() {
        return productRepository.findAll().stream()
                .collect(Collectors.partitioningBy(
                        Product::isAvailable));
    }

    @Override
    public Product getMaxPricedProduct()
            throws ProductNotFoundException {

        return productRepository.findAll().stream()
                .max(Comparator.comparing(
                        Product::getMaxRetailPrice))
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "No products available"));
    }

    @Override
    public Product getMinPricedProduct()
            throws ProductNotFoundException {

        return productRepository.findAll().stream()
                .min(Comparator.comparing(
                        Product::getMaxRetailPrice))
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "No products available"));
    }

    @Override
    public Map<Integer, Product> getProductMapById() {
        return productRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Product::getId,
                        p -> p));
    }

    @Override
    public Map<String, Double> getAveragePriceByCategory() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(
                                Product::getMaxRetailPrice)));
    }

    @Override
    public Map<String, List<Product>> getTop3ProductsByCategory() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted((a, b) ->
                                                Double.compare(
                                                        b.getMaxRetailPrice(),
                                                        a.getMaxRetailPrice()))
                                        .limit(3)
                                        .toList()
                        )));
    }
}