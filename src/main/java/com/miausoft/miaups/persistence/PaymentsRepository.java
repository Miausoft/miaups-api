package com.miausoft.miaups.persistence;

import com.miausoft.miaups.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentsRepository extends JpaRepository<Payment,Integer> {

    @Query("SELECT t FROM Payment t WHERE t.payPalId = ?1")
    Payment findByPayPalId(String payPalId);
}
