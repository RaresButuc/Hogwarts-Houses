package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.PetType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private final PetType pet = PetType.values()[new Random().nextInt(PetType.values().length - 1)];

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PetType getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }
}
