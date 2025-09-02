package com.java.ecommerce.basketservice.service;

import com.java.ecommerce.basketservice.client.PlatziStoreClient;
import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public List<PlatziProductResponse> getAllproducts(){
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductsById(Long id){
        return platziStoreClient.getProductById(id);
    }
}
