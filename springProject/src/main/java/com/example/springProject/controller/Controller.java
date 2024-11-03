package com.example.springProject.controller;

import com.example.springProject.kafka.KafkaProducer;
import com.example.springProject.repository.CatRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final KafkaProducer kafkaProducer;
    private final CatRepository catRepository;

    public Controller(KafkaProducer kafkaProducer, CatRepository catRepository) {
        this.kafkaProducer = kafkaProducer;
        this.catRepository = catRepository;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var cat = catRepository.findById(id).orElseThrow();
        kafkaProducer.sendMessage(cat.toString());
        return "Success";
    }
}
