package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import Growthschool_UserPaths.Scenarios.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney_Outskill_NewAPI {
    private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofMinutes(10);

    public static ChainBuilder CasualVisit =
            exec(NewOutskillAPI.GetFormResponse, NewOutskillAPI.GetFormsQuestionFlow, NewOutskillAPI.GetLeadDetails, NewOutskillAPI.GetWebinarForms)
            .pause(LOW_PAUSE)
            .exec(NewOutskillAPI.GetWebinarSchedules)
            .pause(LOW_PAUSE)
            .exec(NewOutskillAPI.WebinarSignups)
            .pause(HIGH_PAUSE);
}
