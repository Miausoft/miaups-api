package com.miausoft.miaups.paypal.discounts;

import java.math.BigDecimal;

public interface Discount {
    public BigDecimal apply(BigDecimal total);
}
