package com.example.day4_productmvc.service;

import com.example.day4_productmvc.entity.Product;
import com.example.day4_productmvc.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
/**
 * @author Phong Bui
 */
@Service
public class ProductServiceIpm {
    @Autowired
    private ProductService producService;

    public Map<String, Product> getAllProduct() {
        List<Product> products;
        products = producService.findAll();
        Map<String, Product> mapProducts;
        mapProducts = products.stream().collect(Collectors.toMap(Product::getId, product -> product));
        return mapProducts;
    }

    public Product getProductName(String name) {
        for(Product product: producService.findAll()) {
            if(product.getName().contains(name)){
               return product;
            }
        }
        return null;
    }

    public List<Product> getProductsName(String name) {
        List<Product> products;
        products = producService.findAll().stream().filter(product -> product.getName().contains(name)).collect(Collectors.toList());
        return products;
    }

    public void createProduct(ProductModel productModel) {
        Product product = new Product();
        String id = UUID.randomUUID().toString();
        product.setId(id);
        product.setName(productModel.name());
        product.setDescription(productModel.description());
        product.setPrice(productModel.price());
        producService.save(product);
    }

    public void updateProduct(String id, ProductModel productModel) {
        Product existingProduct = producService.findById(id).orElse(null);
        if(existingProduct != null) {
            existingProduct.setName(productModel.name());
            existingProduct.setDescription(productModel.description());
            existingProduct.setPrice(productModel.price());
            producService.save(existingProduct);
        }
    }

    public void delete(String id) {
        Product existingProduct = producService.findById(id).orElse(null);
        if(existingProduct!=null) {
            producService.delete(existingProduct);
        }
    }


}
