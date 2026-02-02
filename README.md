**About this repo** <br>
This repo contains a hands-on Spring Boot demo that makes Circuit Breaker behavior easy to understand by simulating failures and successes, visualizing state transitions (CLOSED, OPEN, HALF_OPEN), and showcasing fallback execution using Resilience4j.

**Introduction**<br>
A circuit breaker continuously observes recent calls to a dependency. If failures cross a defined threshold, it opens the circuit to prevent further load and cascading failures. After a cooldown period, it cautiously allows a limited number of test requests to check if the dependency has recovered. Based on their outcome, it either resumes normal traffic or reopens the circuit.


🟢 CLOSED (Normal Operation)
- All requests are allowed. 
- Calls are recorded in a sliding window. 
- Once `minimumNumberOfCalls` is reached, the failure rate is evaluated. 
- If the failure rate exceeds `failureRateThreshold` (e.g. 50%), the circuit transitions to OPEN.

🔴 OPEN (Fail Fast Mode)
- No calls are forwarded to the downstream service. 
- Requests fail immediately and fallback logic is executed. 
- This state lasts for `waitDurationInOpenState`. 
- Prevents unnecessary load and cascading failures.

🟡 HALF_OPEN (Recovery Check)

- After the wait period, the circuit allows a limited number of test calls (`permittedNumberOfCallsInHalfOpenState`). Rest calls goes to fall back method
- These calls determine whether the dependency has recovered:
- If failure rate is below threshold → transition to CLOSED 
- If failure rate is above threshold → transition back to OPEN



DEMO VIDEO


https://github.com/user-attachments/assets/44971448-f9d2-4a39-a5c6-0df33aa708d3


How to run ? <Br>
- Just run the spring boot app
- In browser go to http://localhost:8080/test?status=fail or http://localhost:8080/test

