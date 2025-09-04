package com.java.ecommerce.basketservice.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;


}
