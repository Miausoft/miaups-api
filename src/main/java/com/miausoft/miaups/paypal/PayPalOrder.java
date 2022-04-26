package com.miausoft.miaups.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayPalOrder {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
}