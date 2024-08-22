package com.app.services;

import java.util.List;

import com.app.dto.PaymentDTO;
import com.app.entities.Payment;

public interface IPaymentService {
    boolean verifyUserByEmail(String email);
    Payment processPayment(PaymentDTO paymentDTO);
    List<Payment> getAllPayments();
}
