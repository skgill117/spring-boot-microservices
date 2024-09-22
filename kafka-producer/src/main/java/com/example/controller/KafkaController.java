package com.example.controller;

import com.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage("topic1", message);
        return "Message sent successfully!";
    }
}
