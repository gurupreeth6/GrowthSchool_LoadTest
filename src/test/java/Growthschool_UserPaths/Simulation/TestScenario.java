package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.Choice;
import io.gatling.javaapi.core.ScenarioBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;
import java.time.Duration;
public class TestScenario {

    private static final Duration TEST_DURATION = Duration.ofSeconds(Integer.parseInt(System.getProperty("DURATION", "300")));
    public static ScenarioBuilder defaultLoadTest =
            scenario("Webinar Load Test")
                    .during(TEST_DURATION)
                    .on(
                            randomSwitch()
                                    .on(
                                            Choice.withWeight(100, exec(UserJourney_Login.CasualVisit))
                                    )
                    ); 

        public static ScenarioBuilder outskillLoadTest =
            scenario("Default Load Test")
                    .during(TEST_DURATION)
                    .on(
                            randomSwitch()
                                    .on(
                                            Choice.withWeight(100, exec(UserJourney_Outskill.CasualVisit))
                                    )
                    );

    public static ScenarioBuilder paymentLoadTest =
            scenario("Default Load Test")
                    .during(TEST_DURATION)
                    .on(
                            randomSwitch()
                                    .on(
                                            Choice.withWeight(100, exec(UserJourney_Payment.CasualVisit))
                                    )
                    );
}
