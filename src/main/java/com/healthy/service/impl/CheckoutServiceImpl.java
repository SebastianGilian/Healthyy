package com.healthy.service.impl;
import com.healthy.dto.PaymentCaptureResponse;
import com.healthy.dto.PaymentOrderResponse;
import com.healthy.dto.SubscriptionDTO;
import com.healthy.integration.payment.paypal.dto.OrderCaptureResponse;
import com.healthy.integration.payment.paypal.dto.OrderResponse;
import com.healthy.integration.payment.paypal.service.PayPalService;
import com.healthy.service.AdminSubscriptionService;
import com.healthy.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final AdminSubscriptionService subscriptionService;

    @Override
    public PaymentOrderResponse createPayment(Integer subscriptionId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse =payPalService.createOrder(subscriptionId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            SubscriptionDTO subscriptionDTO = subscriptionService.confirmPayment(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setSubscriptionId(subscriptionDTO.getId());
        }
        return paypalCaptureResponse;
    }
}