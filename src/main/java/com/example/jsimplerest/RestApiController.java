package com.example.jsimplerest;

import com.example.jsimplerest.config.ConfigMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class RestApiController {

    @Autowired
    public AnimalRepository animalRepository;

    @Autowired
    private ConfigMap configMap;

    private static final Logger log = LogManager.getLogger(RestApiController.class);

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
        String requiredCountry = configMap.getCountries().get(countryId);
        log.info("Value of required country is: {}", requiredCountry);

        return animalRepository.findAnimalsByOriginCountry(requiredCountry);
    }

    @PostMapping
    Animal postAnimal(@RequestBody Animal animal){
        log.info("Post new animal: {}", animal.toString());
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
