package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.mappers.ParcelMappers;
import com.miausoft.miaups.models.Parcel;
import com.miausoft.miaups.models.Payment;
import com.miausoft.miaups.models.PaymentStatus;
import com.miausoft.miaups.persistence.ParcelsRepository;
import com.miausoft.miaups.persistence.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentsService {

    @Autowired
    ParcelMappers parcelMappers;
    @Autowired
    PaymentsRepository paymentsRepository;
    @Autowired
    ParcelsRepository parcelsRepository;


    public void save(CreateParcelDto createParcelDto, String payPalId, Float amount, String currency) {
        Payment payment = new Payment(payPalId, amount, currency);
        paymentsRepository.save(payment);
        Parcel parcel = parcelMappers.fromCreateDtoToParcel(createParcelDto);
        parcel.setPayment(payment);
        parcelsRepository.save(parcel);
    }

    public UUID paymentCompleted(String payPalPaymentId) {
        Payment payment = paymentsRepository.findByPayPalId(payPalPaymentId);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentsRepository.save(payment);
        return payment.getParcel().getId();
    }
}
