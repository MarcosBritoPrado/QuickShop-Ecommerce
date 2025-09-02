package com.java.ecommerce.basketservice.controller;

import com.java.ecommerce.basketservice.client.PlatziStoreClient;
import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import com.java.ecommerce.basketservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAllproducts() {
        return ResponseEntity.ok(productService.getAllproducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable long id) {

        return ResponseEntity.ok(productService.getProductsById(id));
    }
}
