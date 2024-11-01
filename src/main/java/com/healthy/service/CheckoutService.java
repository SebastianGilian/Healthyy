package com.healthy.service;

import com.healthy.dto.PaymentOrderResponse;
import com.healthy.dto.PaymentCaptureResponse;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId) ;
}