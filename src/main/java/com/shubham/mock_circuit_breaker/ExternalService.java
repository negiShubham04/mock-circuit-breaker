package com.shubham.mock_circuit_breaker;

public interface ExternalService {
    String call(String status);
}
