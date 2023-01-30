package com.example.jsimplerest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {

    private final PetRepository petRepository;

    public DataLoader(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostConstruct
    public Iterable<Pet> loadData(){
        return petRepository.saveAll(List.of(
                new Pet("dog"),
                new Pet("cat"),
                new Pet("parrot"),
                new Pet("hamster")
        ));
    }
}
