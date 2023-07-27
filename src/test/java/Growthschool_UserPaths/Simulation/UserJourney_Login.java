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
            .exec(UserLoginAPI.WebinarLiveSignup)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetSignupDetails)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetWebinarScheduleDetails)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetAirbrakeAPI)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetWebinarDetails)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetStaticManifest)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetWebinarHostsDetails)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetWebinarResourcesDetails)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetWebinarMetadata)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetLivePeopleGraph)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GenerateFirebaseToken)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetVideoStream)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl1)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl2)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl3)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl4)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetGumletUrl5)
            .pause(LOW_PAUSE)
            /*.exec(UserLoginAPI.GetThumbnail)
            .pause(LOW_PAUSE)*/
            .exec(UserLoginAPI.SendMessages)
            .pause(LOW_PAUSE)
            .exec(UserLoginAPI.GetAllMessages)
            .pause(HIGH_PAUSE);
}
