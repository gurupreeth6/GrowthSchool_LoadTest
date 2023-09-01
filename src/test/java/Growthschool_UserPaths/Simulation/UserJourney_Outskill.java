package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import Growthschool_UserPaths.Scenarios.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney_Outskill {

    private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofMinutes(10);

    public static ChainBuilder CasualVisit =
            exec(OutskillAPI.BroadcastingWorkshop, OutskillAPI.WorkshopOfTheDay, OutskillAPI.TopicsAndMentor, OutskillAPI.UpcomingWorkshop, OutskillAPI.UpcomingWorkshop2, OutskillAPI.GetRecentFeedback, OutskillAPI.SubmitFeedback)
            .pause(HIGH_PAUSE);
}
