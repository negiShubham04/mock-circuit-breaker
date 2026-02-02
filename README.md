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
![](https://github.com/user-attachments/assets/52582878-09d0-4669-bd28-b1620ac7f5dd)