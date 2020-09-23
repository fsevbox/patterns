package org.techfrog.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Bean
    public RouteLocator myroutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        return builder.routes()
                .route(p -> p
                        .path("/bar")
                        .filters(f -> f.addRequestHeader("gateway_timestamp", String.valueOf(System.currentTimeMillis())))
                        .uri(uriConfiguration.getBarServiceUrl()))
                .route(p -> p
                        .path("/foo")
                        .filters(f -> {
                            f.addRequestHeader("gateway_timestamp", String.valueOf(System.currentTimeMillis()));
                            f.hystrix(config -> config
                                    .setName("foo_call")
                                    .setFallbackUri("forward:/fallback"));
                            return f;
                        })
                        .uri(uriConfiguration.getFooServiceUrl()))
                .build();
    }

}
