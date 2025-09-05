package com.java.ecommerce.basketservice.request;

import com.java.ecommerce.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;
}
