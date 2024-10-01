package com.example;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ReceptionControllerSimulation {

    ChainBuilder validate = exec(
        http("Validate")
            .post("/api/reception/validate")
            .header("Content-Type", "application/json")
            .body(RawFileBody("test-data/validation-request-body.json")).asJson()
            .check(
                status().is(200),
                jsonPath("$.valid").is("true")
            ),
        pause(1)
    );

    public ScenarioBuilder scenarioBuilder() {
        return scenario("Validate & Submit").exec(validate);
    }
}
