package com.example.jsimplerest;





import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Animal {

    @Id
    private String id;
    private String name;

    private String originCountry;

    public Animal() {
    }

    public Animal(String id, String name, String originCountry) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
    }

    public Animal(String name, String originCountry) {
        this(UUID.randomUUID().toString(), name, originCountry);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
