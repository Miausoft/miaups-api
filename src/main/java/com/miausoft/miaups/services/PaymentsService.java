package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.enums.PaymentStatus;
import com.miausoft.miaups.mappers.ParcelMappers;
import com.miausoft.miaups.persistence.ParcelMachinesRepository;
import com.miausoft.miaups.persistence.ParcelsRepository;
import com.miausoft.miaups.persistence.PaymentsRepository;
import com.miausoft.miaups.persistence.entities.Parcel;
import com.miausoft.miaups.persistence.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentsService {

    @Autowired
    ParcelMappers parcelMappers;
    @Autowired
    PaymentsRepository paymentsRepository;
    @Autowired
    ParcelMachinesRepository parcelMachinesRepository;
    @Autowired
    ParcelsRepository parcelsRepository;


    public void createPayment(CreateParcelDto createParcelDto, String paymentId, BigDecimal amount, String currency) {
        Payment payment = new Payment(paymentId, amount, currency);
        Parcel parcel = parcelMappers.fromCreateDtoToParcel(createParcelDto);
        payment.setParcel(parcel);
        if(parcel.getStartParcelMachine() != null){
            parcel.getStartParcelMachine().decreaseAvailableLockersCount();
            parcelMachinesRepository.save(parcel.getStartParcelMachine());
        }
        if(parcel.getDestinationParcelMachine() != null){
            parcel.getDestinationParcelMachine().decreaseAvailableLockersCount();
            parcelMachinesRepository.save(parcel.getDestinationParcelMachine());
        }
        paymentsRepository.save(payment);
    }

    public UUID paymentCompleted(String payPalPaymentId) {
        Payment payment = paymentsRepository.findByPaymentId(payPalPaymentId);
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentsRepository.save(payment);
        return payment.getParcel().getId();
    }
}
