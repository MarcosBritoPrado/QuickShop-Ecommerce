package com.java.ecommerce.basketservice.request;

import java.util.List;

public record BasketRequest(Long clientId, List<PorductRequest> products) {
}
