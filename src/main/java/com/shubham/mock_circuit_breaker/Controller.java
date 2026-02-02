package com.shubham.mock_circuit_breaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ExternalService externalService;

    public Controller(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/test")
    @CircuitBreaker(name = "externalServiceCB", fallbackMethod = "fallback")
    public String test(@RequestParam(name = "status", defaultValue = "success") String status) {
        return externalService.call(status);
    }

    public String fallback(Exception ex) {
        // THis method is executed when the breaker is in OPEN state or whenever any exception thrown by service method
        System.out.println("\uD83D\uDD25 FALLBACK TRIGGERED: External service failed");
        return "Fallback response: service unavailable";
    }

}