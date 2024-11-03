package com.example.springProject.controller;

import com.example.springProject.DTO.CatDTO;
import com.example.springProject.entity.Cat;
import com.example.springProject.repository.CatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepository catRepository;

    @Operation(
            summary = "Кладет нового котика в базу",
            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row " + catRepository.save(Cat.builder()
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())
                        .name(catDTO.getName())
                        .build())
        );
    }

    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepository.deleteById(id);
    }

    @PutMapping("/api/put")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepository.save(cat).toString();
    }

}