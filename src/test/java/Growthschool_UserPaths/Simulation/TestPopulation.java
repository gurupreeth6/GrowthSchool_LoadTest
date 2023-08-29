package Growthschool_UserPaths.Simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;
import java.time.Duration;
public class TestPopulation {
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("USERS", "1"));
    private static final Duration RAMP_DURATION = Duration.ofMinutes(Integer.parseInt(System.getProperty("RAMP_DURATION", "1")));

    public static PopulationBuilder instantUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            nothingFor(5),
                            atOnceUsers(USER_COUNT));

    public static PopulationBuilder rampUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            nothingFor(5),
                            rampUsers(USER_COUNT).during(RAMP_DURATION));

    public static PopulationBuilder complexInjection =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            constantUsersPerSec(10).during(20).randomized(),
                            rampUsersPerSec(10).to(20).during(20).randomized()
                    );

    public static PopulationBuilder closedModel =
            TestScenario.defaultLoadTest
                    .injectClosed(
                            constantConcurrentUsers(5000).during(5),
                            rampConcurrentUsers(5000).to(25000).during(40)
                    );
}
