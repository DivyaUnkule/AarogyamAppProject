package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.PaymentDTO;
import com.app.entities.Payment;
import com.app.services.IPaymentService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/user/makePayment")
    public ResponseEntity<String> handlePayment(@RequestBody PaymentDTO paymentDTO) {
        // Verify the user's email
        if (paymentService.verifyUserByEmail(paymentDTO.getEmail())) {
            
            
        	 String promptMessage = "Please proceed to pay 2000RS for the selected payment mode.";
             System.out.println(promptMessage);
            // Process the payment
            Payment payment = paymentService.processPayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.OK)
                                 .body("Payment successful! Payment ID: " + payment.getPaymentId());
        } else {
            // If email is invalid, return an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User with email " + paymentDTO.getEmail() + " not found.");
        }
    }
    
    
    @GetMapping("/user/admin/getAllpayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
}
