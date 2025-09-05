package com.java.ecommerce.basketservice.repository;

import com.java.ecommerce.basketservice.entity.Basket;
import com.java.ecommerce.basketservice.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, Status status);

    String id(String id);
}
