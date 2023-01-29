package com.example.jsimplerest;

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

    private List<Pet> pets = new ArrayList<>();

    public RestApiController() {
        pets.addAll(List.of(
                new Pet("dog"),
                new Pet("cat"),
                new Pet("parrot"),
                new Pet("hamster")
        ));
    }

    @GetMapping
    Iterable<Pet> getPets(){
        return pets;
    }

    @GetMapping("/{id}")
    Optional<Pet> getPetById(@PathVariable String id){
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                return Optional.of(pet);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Pet postPet(@RequestBody Pet pet){
        pets.add(pet);
        return pet;
    }

    @PutMapping("/{id}")
    ResponseEntity<Pet> putPet(@PathVariable String id, @RequestBody Pet pet){
        int petIndex = -1;

        for (Pet p : pets) {
            if (p.getId().equals(id)){
                petIndex = pets.indexOf(p);
                pets.set(petIndex, pet);
            }
        }
        return (petIndex == -1) ?
                new ResponseEntity<>(postPet(pet), HttpStatus.CREATED) :
                new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deletePet(@PathVariable String id) {
        pets.removeIf(pet -> pet.getId().equals(id));
    }
}
