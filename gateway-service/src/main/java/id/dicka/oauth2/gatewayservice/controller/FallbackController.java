package id.dicka.oauth2.gatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping(value = "/productServiceFallback")
    public Mono<String> productServiceFallBack(){
        return Mono.just("product service is taking too long to response or is down. Please try again later");
    }
}
