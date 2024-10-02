package com.example;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ReceptionControllerSimulation {

    ChainBuilder validate = exec(
        http("Validating VAT message")
            .post("/api/reception/validate")
            .body(RawFileBody("test-data/validation-request-body.json")).asJson()
            .check(
                status().is(200),
                jsonPath("$.valid").is("true")
            ),
        pause(1)
    );

    ChainBuilder submit = exec(
        http("Submit VAT message")
            .post("/api/reception/submit")
            .body(RawFileBody("test-data/validation-request-body.json")).asJson()
            .check(
                status().is(200),
                jsonPath("$.message").exists()
            ),
        pause(1)
    );

    public ScenarioBuilder scenarioBuilder() {
        return scenario("Validate & Submit VAT message").exec(validate, submit);
    }
}
