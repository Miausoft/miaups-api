package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentsRepository extends JpaRepository<Payment,Long> {
    @Query("SELECT t FROM Payment t WHERE t.paymentId = ?1")
    Payment findByPaymentId(String paymentId);
}
