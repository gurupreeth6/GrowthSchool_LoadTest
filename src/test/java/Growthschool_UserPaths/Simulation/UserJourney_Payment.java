package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import Growthschool_UserPaths.Scenarios.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney_Payment {

    // private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofSeconds(15);

    public static ChainBuilder CasualVisit =
            exec(PaymentAPI.PaymentLink,PaymentAPI.PaymentLead,PaymentAPI.PaymentCheckout,PaymentAPI.DiscountCodes, PaymentAPI.Orders)
            .pace(HIGH_PAUSE);
}
