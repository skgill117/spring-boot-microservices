package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/fallback/lending-service-fallback")
    public ResponseEntity<String> lendingServiceFallback() {
        return new ResponseEntity<>("Lending Service is currently unavailable. Please try again later.", HttpStatus.OK);
    }

    @RequestMapping("/fallback/auth-service-fallback")
    public ResponseEntity<String> authServiceFallback() {
        return new ResponseEntity<>("Auth Service is currently unavailable. Please try again later.", HttpStatus.OK);
    }
}
