// package com.lld.and.hld.lldandhld.practice.resilience;

// import io.github.resilience4j.circuitbreaker.CircuitBreaker;
// import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
// import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// public class CircuitBreakerTest {

//     private static CircuitBreakerConfig getCircuitBreakerConfig() {
//         CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().failureRateThreshold(100)
//                 .slidingWindowSize(5).build();
//         return circuitBreakerConfig;
//     }

//     public static void main(String[] args) {
//         CircuitBreaker circuitBreaker = getCircuitBreaker();
//         System.out.println(circuitBreaker);
//     }

//     private static CircuitBreaker getCircuitBreaker() {
//         CircuitBreakerConfig circuitBreakerConfig = getCircuitBreakerConfig();
//         CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
//         CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("cb");
//         return circuitBreaker;
//     }

// }
