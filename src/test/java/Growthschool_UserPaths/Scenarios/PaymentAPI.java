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
                .get("/pay/9/16acf603-5c43-41ae-bce4-82bad654801d/INR"));

    // Payment Lead
    public static ChainBuilder PaymentLead =
                    feed(userFeeder)
                        .exec(http("Payment Leads")
                            .post("/leads")
                            .header("content-type", "application/json")
                            .body(StringBody("{\"userName\": \"${name}\",\"userEmail\": \"${name}@mailinator.com\",\"userPhone\": \"${phoneNumber}\",\"paymentLinkId\": \"16acf603-5c43-41ae-bce4-82bad654801d\",\"status\": \"LEAD\",\"isPaymentLinkExpired\": false,\"currency\": \"INR\"}")));
    
    public static ChainBuilder DiscountCodes =
            feed(userFeeder)
                .exec(http("Discount Codes")
                    .post("/discount-codes")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"code\": \"${code}\",\"userEmail\": \"${name}@mailinator.com\",\"productIds\": [16],\"paymentLinkId\": \"16acf603-5c43-41ae-bce4-82bad654801d\",\"currency\": \"INR\"}")));
    
    public static ChainBuilder Orders =
            feed(userFeeder)
                .exec(http("Orders API")
                    .post("/orders")
                    .header("content-type", "application/json")
                    .body(StringBody("{\"userName\": \"${name}\",\"userEmail\": \"${name}@mailinator.com\",\"userPhone\": \"${phoneNumber}\",\"paymentLinkId\": \"16acf603-5c43-41ae-bce4-82bad654801d\",\"productIds\": [16],\"gstin\": \"\",\"userPinCode\": \"\",\"userState\": \"\",\"userCity\": \"\",\"userAddress\": \"\",\"discountCode\": \"\",\"gateway\": \"RZP\",\"currency\": \"INR\"}")));

}
