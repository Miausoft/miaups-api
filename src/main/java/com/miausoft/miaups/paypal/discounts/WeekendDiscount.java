package com.miausoft.miaups.paypal.discounts;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "app.paypal.discount", havingValue = "weekend")
public class WeekendDiscount implements Discount {

    public BigDecimal apply(BigDecimal total) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

        if (dayOfWeek.equals(DayOfWeek.SATURDAY) ||
            dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return total.multiply(new BigDecimal("0.9"));
        }

        return total;
    }
}
