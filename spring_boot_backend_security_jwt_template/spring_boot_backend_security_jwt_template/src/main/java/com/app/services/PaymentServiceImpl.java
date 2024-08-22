package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.PaymentDTO;
import com.app.entities.Login;
import com.app.entities.Payment;
import com.app.repositories.LoginRepo;
import com.app.repositories.PaymentRepository;

import com.app.services.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final double FIXED_AMOUNT = 1500.0;  // Fixed amount

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LoginRepo userRepo;

    @Override
    public boolean verifyUserByEmail(String email) {
        Login user = userRepo.findByEmailIgnoreCase(email);
        return user != null;
    }

    @Override
    public Payment processPayment(PaymentDTO paymentDTO) {
        // Verify the user's email
        Login user = userRepo.findByEmailIgnoreCase(paymentDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("User with email " + paymentDTO.getEmail() + " not found");
        }

        // Create and save the payment record with the fixed amount
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        payment.setAmount(FIXED_AMOUNT);  // Set the fixed amount
        payment.setUserEmail(paymentDTO.getEmail());

        return paymentRepository.save(payment);
    }
    
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
