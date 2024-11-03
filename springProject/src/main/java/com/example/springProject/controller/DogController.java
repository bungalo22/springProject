package com.example.springProject.controller;

import com.example.springProject.entity.Dog;
import com.example.springProject.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogRepository dogRepository;

    @GetMapping("/all")
    public List<Dog> getAllDogsFromDB() {
        return dogRepository.findAll();
    }

    @GetMapping
    public ResponseEntity <Dog> getDogByName(@RequestParam("name") String name) {

        var dog = dogRepository.findByName(name);
        if (dog == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return new ResponseEntity<>(dog, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public Dog putDogIntoDB(@RequestBody Dog dog) {
        dog.setId(UUID.randomUUID());
        return dogRepository.save(dog);
    }
}
