package com.example.jsimplerest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {

    private final AnimalRepository animalRepository;

    public DataLoader(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    public Iterable<Animal> loadData(){
        return animalRepository.saveAll(List.of(
                new Animal("wolf", "france"),
                new Animal("bear", "russia"),
                new Animal("parrot", "china"),
                new Animal("snake", "england"),
                new Animal("pinguin", "japan"),
                new Animal("tiger", "china"),
                new Animal("owl", "england"),
                new Animal("fox", "france"),
                new Animal("whale", "japan")
        ));
    }
}
