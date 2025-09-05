package com.java.ecommerce.basketservice.service;

import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import com.java.ecommerce.basketservice.entity.Basket;
import com.java.ecommerce.basketservice.entity.Product;
import com.java.ecommerce.basketservice.entity.Status;
import com.java.ecommerce.basketservice.repository.BasketRepository;
import com.java.ecommerce.basketservice.request.BasketRequest;
import com.java.ecommerce.basketservice.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService  {
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket getBasketById(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest) {
    basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN).ifPresent(basket -> {
        throw new IllegalArgumentException("Basket already exists");
    });




        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(product -> {
            PlatziProductResponse platziProductResponse = productService.getProductsById(product.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(product.quantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                 .products(products)
                .build();

        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket basketUpdate(String basketid, BasketRequest basketRequest) {
        Basket basket = getBasketById(basketid);

        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(product -> {
            PlatziProductResponse platziProductResponse = productService.getProductsById(product.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(product.quantity())
                    .build());
        });

        basket.setProducts(products);
        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }

    public Basket payBasket(String basketid, PaymentRequest paymentRequest) {
        Basket savebasket = getBasketById(basketid);
        savebasket.setPaymentMethod(paymentRequest.getPaymentMethod());
        savebasket.setStatus(Status.SOLD);
        return basketRepository.save(savebasket);

    }

    public void deleteBasketId(String basketid) {
         basketRepository.delete(getBasketById(basketid));
    }
}
