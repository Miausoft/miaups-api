package com.miausoft.miaups.paypal.discounts;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(name = "app.paypal.discount", havingValue = "none", matchIfMissing = true)
public class NoDiscount implements Discount {
    public BigDecimal apply(BigDecimal total) {
        return total;
    }
}
