package com.shubham.mock_circuit_breaker;

import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Override
    public String call(String status) {
        if (status.equalsIgnoreCase("Fail")) {
            throw new RuntimeException("External service failed");
        }
        System.out.println("success in service");
        return "External service success";
    }
}
