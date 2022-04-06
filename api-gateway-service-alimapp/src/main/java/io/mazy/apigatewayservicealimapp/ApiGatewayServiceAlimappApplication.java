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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return builder.routes()
                .route(r -> r
                        .path("/**")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "351906158emshae91b1f03f78323p1cf294jsn33b01df9c3ef")
                                .rewritePath("/(?<segment>.*)", "/${segment}")
                                .circuitBreaker(h -> h.setName("countries").setFallbackUri("forward:/break/countrystatecity_api"))
                        )
                        .uri("https://wft-geo-db.p.rapidapi.com/v1/geo/adminDivisions"))
                .route(r -> r                                           //endpoint salat hour not working -> must be to fix !!!!
                        .path("/muslimsalat/**")
                        .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "351906158emshae91b1f03f78323p1cf294jsn33b01df9c3ef")
                                .rewritePath("/muslimsalat/(?<segment>.*)", "/${segment}"))
                        .uri("https://muslimsalat.p.rapidapi.com"))
                .build();
    }

}
@RestController
@RequestMapping("/break")
class CircuitBreakerRestController {
    @GetMapping("/message")
    public String message(){
        return "Hystrix called fallback services ";
    }
    @GetMapping("/countrystatecity_api")
    public RouteLocator country_state_city_api(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/**")
                        .filters(f -> f
                                .addRequestHeader("X-CSCAPI-KEY", "https://countrystatecity.in/")
                                .rewritePath("/(?<segment>.*)", "/${segment}")
                                .circuitBreaker(h -> h.setName("countries").setFallbackUri("forward:/break/message"))
                        )
                        .uri("https://countrystatecity.in/"))
                .build();
    }
}