package com.java.ecommerce.basketservice.service;

import com.java.ecommerce.basketservice.client.PlatziStoreClient;
import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;


    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllproducts(){
        log.info("Pegando todos os produtos");
        return platziStoreClient.getAllProducts();
    }
@Cacheable(value = "product", key = "#id")
    public PlatziProductResponse getProductsById(Long id){
        log.info("Pegando produto por id {}", id);
        return platziStoreClient.getProductById(id);
    }
}
