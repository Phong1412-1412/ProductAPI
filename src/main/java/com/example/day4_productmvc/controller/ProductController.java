package com.example.day4_productmvc.controller;

import com.example.day4_productmvc.entity.Product;
import com.example.day4_productmvc.model.ProductModel;
import com.example.day4_productmvc.service.ProductServiceIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductServiceIpm productServiceIpm;

    @GetMapping()
    public String getProducts(Model model) {
        List<Product> productList = new ArrayList<>(productServiceIpm.getAllProduct().values());
        model.addAttribute("productList", productList );
        return "index";
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> homePage() {
        List<Product> products = new ArrayList<>();
        products.addAll(productServiceIpm.getAllProduct().values());
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> findProduct(@RequestParam(value = "name") String name) {
        List<Product> findProducts;
        findProducts = productServiceIpm.getProductsName(name);
        return ResponseEntity.ok().body(findProducts);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) {
        productServiceIpm.createProduct(productModel);
        return ResponseEntity.ok().body(productModel);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductModel> upDateProduct(@PathVariable String id, @RequestBody ProductModel productModel) {
        productServiceIpm.updateProduct(id,productModel);
        return ResponseEntity.ok().body(productModel);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable String id) {
        productServiceIpm.delete(id);
        return ResponseEntity.noContent().build();
    }
}
