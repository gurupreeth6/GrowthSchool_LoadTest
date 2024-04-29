package Growthschool_UserPaths.Scenarios;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.text.DecimalFormat;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class PaymentAPI {

    private static Iterator<Map<String, Object>> userFeeder =
            Stream.generate((Supplier<Map<String,Object>>) () -> {
                Random rand = new Random();
                String uuid = UUID.randomUUID().toString().replace("-", "");

                int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
                int num2 = rand.nextInt(743);
                int num3 = rand.nextInt(10000);

                DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
                DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

                String phoneNumber = df3.format(num1) + df3.format(num2) + df4.format(num3);
                String name = uuid.substring(0, Math.min(8, uuid.length()));
                String code = uuid.substring(0, Math.min(6, uuid.length()));

                HashMap<String,Object> hmap = new HashMap<String,Object>();
                hmap.put("phoneNumber", phoneNumber);
                hmap.put("name", name);
                hmap.put("code", code);
                return hmap;
            }).iterator();

    // Payment Link API
    public static ChainBuilder PaymentLink =
            exec(http("Payment Link API")
                .get("/pay/1/2b9c7311-83bb-418f-ae82-8b879db026d7/INR"));

    // Payment Lead
    public static ChainBuilder PaymentLead =
            feed(userFeeder)
                .exec(http("Payment Leads")
                    .post("/leads")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"userName\": \"${name}\",\"userEmail\": \"test${name}@mailinator.com\",\"userPhone\": \"${phoneNumber}\",\"paymentLinkId\": \"25ff3982-035d-4728-9ba6-bb752cf49a48\",\"status\": \"LEAD\",\"isPaymentLinkExpired\": false,\"currency\": \"INR\"}")));

    
    public static ChainBuilder PaymentCheckout =
            feed(userFeeder)
                .exec(http("Payment Checkout")
                    .post("/pay/checkout/1/25ff3982-035d-4728-9ba6-bb752cf49a48/INR")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"userName\": \"${name}\",\"userEmail\": \"test${name}@mailinator.com\",\"userPhone\": \"${phoneNumber}\",\"whatsappComm\": \"\",\"userGstIn\": \"\",\"userPinCode\": \"\",\"userState\": \"\",\"userCity\": \"\",\"userAddress\": \"\"}")));
    
    public static ChainBuilder DiscountCodes =
            feed(userFeeder)
                .exec(http("Discount Codes")
                    .post("/discount-codes")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"code\": \"LOADTEST\",\"userEmail\": \"test${name}@mailinator.com\",\"productIds\": [1],\"paymentLinkId\": \"25ff3982-035d-4728-9ba6-bb752cf49a48\",\"currency\": \"INR\"}")));
    
    public static ChainBuilder Orders =
            feed(userFeeder)
                .exec(http("Orders API")
                    .post("/orders")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"userName\": \"${name}\",\"userEmail\": \"test${name}@mailinator.com\",\"userPhone\": \"${phoneNumber}\",\"paymentLinkId\": \"25ff3982-035d-4728-9ba6-bb752cf49a48\",\"productIds\": [1],\"gstin\": \"\",\"userPinCode\": \"\",\"userState\": \"\",\"userCity\": \"\",\"userAddress\": \"\",\"discountCode\": \"LOADTEST\",\"gateway\": \"RZP\",\"currency\": \"INR\"}")));
}
