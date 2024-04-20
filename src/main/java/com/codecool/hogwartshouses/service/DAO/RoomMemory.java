package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.PetType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoomMemory implements RoomDAO {

    private Set<Room> rooms = new HashSet<>();

    public Set<Room> getRooms() {
        return rooms;
    }

    public void addRoom() {
        rooms.add(new Room());
    }

    public void deleteRoom(int id) {
        Room room = rooms.stream().filter(e -> e.getId() == id).findAny().get();
        rooms.remove(room);
    }

    public void setRoomAsRenovated(int id) {
        rooms.stream().filter(e -> e.getId() == id).findAny().get().setRenovated();
    }

    public void addStudentToRandomRoom(Student student) {
        for (Room room :
                rooms) {
            if (room.getStudentsInsideRoom().size() >= 3) {
                System.out.println("No more students can be added here!");
            } else {
                room.getStudentsInsideRoom().add(student);
                System.out.println("Student " + student.getName() + " was added to the Room #" + room.getId());
                break;
            }
        }
    }

    public void addStudentToASpecificRoom(Student student, int id) {
        Room room = rooms.stream().filter(e -> e.getId() == id).findAny().get();

        if (room.getStudentsInsideRoom().size() >= 3) {
            System.out.println("No more students can be added here!");
        } else {
            room.getStudentsInsideRoom().add(student);
            System.out.println("Student " + student.getName() + " was added to the Room #" + room.getId());
        }
    }

    public Set<Room> roomsForRatOwners() {
        return rooms.stream().filter(e -> e.getStudentsInsideRoom().stream().noneMatch(j -> j.getPet() == PetType.OWL || j.getPet() == PetType.CAT)).collect(Collectors.toSet());
    }

}
