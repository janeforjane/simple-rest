package com.example.jsimplerest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class RestApiController {

    @Autowired
    public PetRepository petRepository;

    public RestApiController(PetRepository petRepository) {
        petRepository.saveAll(List.of(
                new Pet("dog"),
                new Pet("cat"),
                new Pet("parrot"),
                new Pet("hamster")
        ));
    }

    @GetMapping
    Iterable<Pet> getPets(){
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Pet> getPetById(@PathVariable String id){
        return petRepository.findById(id);
    }

    @PostMapping
    Pet postPet(@RequestBody Pet pet){
        return petRepository.save(pet);
    }

    @PutMapping("/{id}")
    ResponseEntity<Pet> putPet(@PathVariable String id, @RequestBody Pet pet){

        return (!petRepository.existsById(id)) ?
                new ResponseEntity<>(petRepository.save(pet), HttpStatus.CREATED) :
                new ResponseEntity<>(petRepository.save(pet), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deletePet(@PathVariable String id) {
        petRepository.deleteById(id);
    }
}
