package com.miausoft.miaups.paypal;

import com.miausoft.miaups.paypal.discounts.Discount;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class PayPalService {

    @Autowired
    private Discount discount;

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(BigDecimal total, String currency, String method, String intent, String cancelUrl, String successUrl) throws PayPalRESTException {
        total = discount.apply(total);

        Amount amount = new Amount(currency, String.format("%.2f", total));
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment(intent, payer);
        payment.setTransactions(Arrays.asList(transaction));
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
