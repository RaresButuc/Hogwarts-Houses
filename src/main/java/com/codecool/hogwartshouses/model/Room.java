package com.codecool.hogwartshouses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Room {
    private boolean renovated = false;

    private int id = new Random().nextInt(1000);
    private Set<Student> studentsInsideRoom = new HashSet<>();

    public int getId() {
        return id;
    }

    public boolean isRenovated() {
        return renovated;
    }

    public Set<Student> getStudentsInsideRoom() {
        return studentsInsideRoom;
    }

    public void setRenovated() {
        this.renovated = true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "renovated=" + renovated +
                ", id=" + id +
                ", studentsInsideRoom=" + studentsInsideRoom +
                '}';
    }
}
