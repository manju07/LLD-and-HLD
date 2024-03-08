Rate Limiter System Design

Denying requests if he exhausted the usages.
- 10R/Minute - basis on customerId


Functional Requirments:
    - Usage exhausted, start denying requests till next window start. 
    - Usage not exhausted, send the request to the respective service.
    - Usage must be configurable on the basis of Customer/Service by Admin.


Non-Functional Requirements:
    - Highly available, because we are evaluating each customer requests usage.
    - Consistency is also an important factor.
    - Latency should be minimal.


Capacity Estimation:

    Write is less compared to read, ratio we would say 1:100 (write:read)

    Traffic:
        Write Traffic:
            - Lets say we onboard 10K new customers per day, 10k*30  = 3L/Month

        Read Traffic:
            - 3L * 100 = 3 Crores = 30M/Month

    Storage:
        - Next 5 Years estimation
            - A Customer object info and config considering 200 bytes, 200bytes * 3L * 12 * 5 = 5 GB 

    Memory:
        -  50% of storage = 2.5GB, so it will drastically improve the latency and avoid DB call most of the time.
    
    Network Bandwidth
        In traffic:
            - 10K/Day = 10 requests/Min
        


System API's:
    - (GET) rateLimit(customerId) -> true/false
    - (POST/PUT) config(customerId, allowNoOfRequests, windowDurationInMS) ->  200 (Configured), 404 (Not Found)    


Data Models:

    - customer(id, fName, lName, .... )
    - rate_limit_config(id, customerId, allowed_no_of_requests, window_duration_in_ms, created_at, updated_at, created_time, updated_time)
    - rate_limit_data(id, customerId, served_requests_count, first_served_timestamp, last_served_timestamp, created_at, updated_at, created_time, updated_time)


High level Design:

    - admin (config) -----> load_balancer/gateway ----> rate_limiter_service ---> cache -->  DB (SQL) 
    - customer (rateLimit) -----> load_balancer/gateway ----> rate_limiter_service ---> Cache ---> DB (SQL) 






    




