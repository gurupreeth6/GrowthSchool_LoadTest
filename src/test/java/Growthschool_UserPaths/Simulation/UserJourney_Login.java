package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import Growthschool_UserPaths.Scenarios.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney_Login {

    private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofMinutes(10);

    public static ChainBuilder CasualVisit =
            exec(UserLoginAPI.UserSignup)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.WebinarLiveSignup, UserLoginAPI.GetSignupDetails, UserLoginAPI.GetWebinarScheduleDetails, UserLoginAPI.GetWebinarDetails, UserLoginAPI.GetStaticManifest, UserLoginAPI.GetWebinarHostsDetails, UserLoginAPI.GetWebinarResourcesDetails, UserLoginAPI.GetWebinarMetadata, UserLoginAPI.GetLivePeopleGraph, UserLoginAPI.GenerateFirebaseToken, UserLoginAPI.SendMessages, UserLoginAPI.GetAllMessages)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetVideoStream)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl1, UserLoginAPI.GetGumletUrl2, UserLoginAPI.GetGumletUrl3, UserLoginAPI.GetGumletUrl4, UserLoginAPI.GetGumletUrl5)
            .pause(HIGH_PAUSE);
}
