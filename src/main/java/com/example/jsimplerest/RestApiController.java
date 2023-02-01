package com.example.jsimplerest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class RestApiController {

    @Autowired
    public AnimalRepository animalRepository;

    @Autowired
    private ConfigMap configMap;

    @GetMapping
    Iterable<Animal> getAnimals(){
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Animal> getAnimalById(@PathVariable String id){
        return animalRepository.findById(id);
    }

    @GetMapping("/countries/{countryId}")
    Iterable<Animal> getAnimalsByCountryId(@PathVariable String countryId){
        List<Animal> animals = new ArrayList<>();
        animalRepository.findAll().forEach(animals::add);

        String requiredCountry = configMap.getCountries().get(countryId);
        animals.removeIf(animal -> !(animal.getOriginCountry().equals(requiredCountry)));

        return animals;
    }

    @PostMapping
    Animal postAnimal(@RequestBody Animal animal){
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    ResponseEntity<Animal> putAnimal(@PathVariable String id, @RequestBody Animal animal){

        return (animalRepository.existsById(id)) ?
                new ResponseEntity<>(animalRepository.save(animal), HttpStatus.OK) :
                new ResponseEntity<>(animalRepository.save(animal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteAnimal(@PathVariable String id) {
        animalRepository.deleteById(id);
    }
}
