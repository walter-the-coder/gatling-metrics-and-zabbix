package com.example;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;

public class SpringApplicationSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080")
            .acceptHeader("application/json");

    {
        setUp(
            new ReceptionControllerSimulation().scenarioBuilder()
                .injectOpen(rampUsers(1200).during(600)),
            new RetrievingControllerSimulation().scenarioBuilder()
                .injectOpen(rampUsers(120).during(600))
        ).protocols(httpProtocol);
    }
}
