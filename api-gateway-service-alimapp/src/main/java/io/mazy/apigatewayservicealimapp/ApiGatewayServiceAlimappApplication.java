package io.mazy.apigatewayservicealimapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServiceAlimappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceAlimappApplication.class, args);
    }

    /**
     * Dynamic route to local services
     * @param rdc
     * @param dlp
     * @return
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }

    /**
     * Static route to external api services
     * @param builder
     * @return
     */
    @Bean
    RouteLocator staticRoutes(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }

}
