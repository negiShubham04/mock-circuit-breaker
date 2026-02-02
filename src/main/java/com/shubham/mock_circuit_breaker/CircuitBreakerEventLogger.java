package com.shubham.mock_circuit_breaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerEventLogger {

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerEventLogger(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void registerEventListeners() {

        CircuitBreaker cb = registry.circuitBreaker("externalServiceCB");

        cb.getEventPublisher()
                .onStateTransition(event ->
                        System.out.println(
                                "⚡ CIRCUIT BREAKER STATE CHANGE: "
                                        + event.getStateTransition()
                        )
                )
                .onFailureRateExceeded(event ->
                        System.out.println("❌ Failure rate exceeded")
                )
                .onCallNotPermitted(event ->
                        System.out.println("🚫 Call not permitted (OPEN state)")
                );
    }
}
