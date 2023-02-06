package com.example.jsimplerest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, String> {

    @Override
    List<Animal> findAll();

    List<Animal> findAnimalsByOriginCountry(String originCountry);


}
