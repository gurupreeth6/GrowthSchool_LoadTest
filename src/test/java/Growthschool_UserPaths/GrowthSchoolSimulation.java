package Growthschool_UserPaths;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.core.Predef.global;
import static io.gatling.javaapi.http.HttpDsl.*;
import Growthschool_UserPaths.Simulation.*;

public class GrowthSchoolSimulation extends Simulation{

    private static final String TEST_TYPE = System.getProperty("TEST_TYPE", "CLOSED_MODEL");
    private static final String DOMAIN = "outskill-api.growthschool.io";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://" +DOMAIN)
            .authorizationHeader("")
            .useAllLocalAddresses();
    {
        if (TEST_TYPE == "INSTANT_USERS") {
            setUp(TestPopulation.instantUsers).protocols(httpProtocol);
        } else if (TEST_TYPE == "RAMP_USERS") {
            setUp(TestPopulation.rampUsers).protocols(httpProtocol);
        } else if (TEST_TYPE == "COMPLEX_INJECTION") {
            setUp(TestPopulation.complexInjection).protocols(httpProtocol);
        } else if (TEST_TYPE == "CLOSED_MODEL") {
            setUp(TestPopulation.closedModel).protocols(httpProtocol);
        } else {
            setUp(TestPopulation.instantUsers).protocols(httpProtocol);
        }
    }
}
