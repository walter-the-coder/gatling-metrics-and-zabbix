package com.example;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.http;

public class SpringApplicationSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080")
            .acceptHeader("application/json");

    {
        setUp(
            new ReceptionControllerSimulation().scenarioBuilder()
                .injectOpen(atOnceUsers(1)),
            new RetrievingControllerSimulation().scenarioBuilder()
                .injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
