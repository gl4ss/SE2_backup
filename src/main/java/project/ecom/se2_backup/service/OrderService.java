package project.ecom.se2_backup.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.model.CheckOutItem;


import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    public Session createSession(List<CheckOutItem> checkOutItemList) throws StripeException {

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for(CheckOutItem checkOutItem : checkOutItemList){
            sessionItemList.add(createSessionLineItem(checkOutItem));
        }
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(baseUrl + "payment/success")
                        .setCancelUrl(baseUrl + "payment/cancel")
                        .addAllLineItem(sessionItemList)
                        .build();

        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckOutItem checkOutItem) {

        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkOutItem))
                .setQuantity(Long.parseLong(String.valueOf(checkOutItem.getQuantity())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutItem checkOutItem) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("dong")
                .setUnitAmount((long)checkOutItem.getPrice())
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkOutItem.getProductName())
                                .build()
                        ).build();
    }
}
