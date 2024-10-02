package com.example;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RetrievingControllerSimulation {
    ChainBuilder unprocessed = exec(
        http("Retrieving unprocessed VAT messages")
            .get("/api/retrieving/unprocessed")
            .check(
                status().is(200),
                jsonPath("$.data").exists()
            ),
        pause(1)
    );

    public ScenarioBuilder scenarioBuilder() {
        return scenario("Retrieve unprocessed VAT messages").exec(unprocessed);
    }
}
