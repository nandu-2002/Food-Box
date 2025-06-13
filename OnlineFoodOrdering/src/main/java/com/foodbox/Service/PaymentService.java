package com.foodbox.Service;

import com.foodbox.model.Order;
import com.foodbox.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
