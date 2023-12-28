package org.shamal.apigateway.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/currency-exchange/**").uri("lb://currency-exchange"))
                .route(p -> p.path("/api/v1/currency-conversion/**").uri("lb://currency-conversion"))
                .build();
    }

}
