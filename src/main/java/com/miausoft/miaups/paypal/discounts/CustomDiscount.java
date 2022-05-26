package com.miausoft.miaups.paypal.discounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(name = "app.paypal.discount", havingValue = "custom")
public class CustomDiscount implements Discount {

    @Value("${app.discount}")
    private String discount;

    public BigDecimal apply(BigDecimal total) {
        return total.multiply(new BigDecimal(discount));
    }
}
