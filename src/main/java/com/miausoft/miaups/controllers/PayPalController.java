package com.miausoft.miaups.controllers;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.services.PayPalService;
import com.miausoft.miaups.services.PaymentsService;
import com.miausoft.miaups.services.PriceCalculatorService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/paypal")
public class PayPalController {

    private final String paymentCurrency = "EUR";
    private final String paymentMethod = "PAYPAL";
    private final String paymentIntent = "SALE";

    @Autowired
    private PayPalService paypalService;
    @Autowired
    private PriceCalculatorService priceCalculatorService;
    @Autowired
    private PaymentsService paymentsService;

    @Value("${paypal.success.url}")
    private String successUrl;
    @Value("${paypal.cancel.url}")
    private String cancelUrl;
    @Value("${payment.on.success.client.redirect}")
    private String redirectUIOnSuccess;
    @Value("${payment.on.cancel.client.redirect}")
    private String redirectUIOnFailure;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody CreateParcelDto createParcelDto) {
        try {
            float price = priceCalculatorService.calculatePrice(createParcelDto);

            Payment payment = paypalService.createPayment(
                    price, paymentCurrency, paymentMethod,
                    paymentIntent, cancelUrl, successUrl);

            paymentsService.save(createParcelDto,payment.getId(), price, paymentCurrency);

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .location(URI.create(link.getHref()))
                            .build();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ResponseEntity successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {

                UUID parcelId = paymentsService.paymentCompleted(payment.getId());

                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(redirectUIOnSuccess+"/"+parcelId.toString()))
                        .build();
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(redirectUIOnFailure))
                .build();
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ResponseEntity cancelPay() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(redirectUIOnFailure))
                .build();
    }

}
