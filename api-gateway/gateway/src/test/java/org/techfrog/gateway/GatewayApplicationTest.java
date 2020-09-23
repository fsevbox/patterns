package org.techfrog.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"fooServiceUrl=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
class GatewayApplicationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void routing() {
        stubFor(get(urlEqualTo("/foo"))
                .willReturn(aResponse()
                        .withBody("Hello from Foo with delay 0 s !")));

        webClient
                .get().uri("/foo")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello from Foo with delay 0 s !");

    }

    @Test
    public void routingFallback() {
        stubFor(get(urlEqualTo("/foo?delay=5"))
                .willReturn(aResponse()
                        .withFixedDelay(5000)));

        webClient
                .get().uri("/foo?delay=5")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Service down");
    }
}