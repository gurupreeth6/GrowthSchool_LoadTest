package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import Growthschool_UserPaths.Scenarios.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney_Payment {

    private static final Duration LOW_PAUSE = Duration.ofMillis(3000);
    private static final Duration HIGH_PAUSE = Duration.ofMinutes(5);

    public static ChainBuilder CasualVisit =
            exec(PaymentAPI.Orders)
            .pause(HIGH_PAUSE);
}
