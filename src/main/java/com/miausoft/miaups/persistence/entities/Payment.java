package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miausoft.miaups.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String paymentId;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal amount;

    @Column(nullable = false)
    private String currencyCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true)
    private Parcel parcel;

    public Payment(String paymentId, BigDecimal amount, String currencyCode){
        this.paymentId = paymentId;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.status = PaymentStatus.PENDING;
    }
}
