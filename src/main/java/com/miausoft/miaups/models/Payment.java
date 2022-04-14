package com.miausoft.miaups.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "PayPalId", nullable = false)
    private String payPalId;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "Amount", nullable = false)
    private Float amount;

    @Column(name = "Currency", nullable = false)
    private String currency;

    @OneToOne(mappedBy = "payment")
    private Parcel parcel;

    public Payment(String payPalId, Float amount, String currency){
        this.payPalId = payPalId;
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = PaymentStatus.NEW;
    }
}
