package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.enums.PaymentStatus;
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
    PaymentsRepository paymentsRepository;
    @Autowired
    ParcelService parcelService;
    @Autowired
    ParcelMachineService parcelMachineService;

    public void createPaymentAndParcel(CreateParcelDto createParcelDto, String paymentId, BigDecimal amount, String currency) {
        Payment payment = new Payment(paymentId, amount, currency);
        Parcel parcel = parcelService.createParcel(createParcelDto);
        payment.setParcel(parcel);
        paymentsRepository.save(payment);
    }

    public UUID paymentCompleted(String payPalPaymentId) {
        Payment payment = paymentsRepository.findByPaymentId(payPalPaymentId);
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentsRepository.save(payment);

        // For the sake of simplicity we assume that Parcel is already in the locker
        if (payment.getParcel().getStartParcelMachine() != null) {
            parcelMachineService.insertParcelIntoLocker(payment.getParcel(), payment.getParcel().getStartParcelMachine());
        }

        return payment.getParcel().getId();
    }
}
