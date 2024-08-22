package com.app.dto;

import com.app.enums.PaymentMode;

public class PaymentDTO {
    private String email;
    private PaymentMode paymentMode;

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
