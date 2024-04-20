package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.service.DAO.RoomMemory;
import com.codecool.hogwartshouses.service.DAO.StudentMemory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class GreetingController {

    private RoomMemory roomMemory = new RoomMemory();
    private StudentMemory studentMemory = new StudentMemory();

    @GetMapping
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Witches and Wizards") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(value = "rooms")
    public String returnAllRooms(Model model) {
        model.addAttribute("allRooms", roomMemory.getRooms());
        return "allRooms";
    }

    @PostMapping(value = "rooms")
    public String postNewRoom() {
        roomMemory.addRoom();
        return "newRoomPosted";
    }

    @GetMapping(value = "rooms/{roomId}")
    public String returnAllRooms(@PathVariable int roomId, Model model) {
        Room room = roomMemory.getRooms().stream().filter(e -> e.getId() == roomId).findAny().get();
        model.addAttribute("chosenRoom", room);
        return "roomChosen";
    }

    @DeleteMapping(value = "rooms/{roomId}")
    public String deleteChosenRoom(@PathVariable int roomId) {
        roomMemory.deleteRoom(roomId);
        return "chosenRoomToBeDeleted";
    }

    @PutMapping(value = "rooms/{roomId}")
    public String renovateChosenRoom(@PathVariable int roomId) {
        roomMemory.setRoomAsRenovated(roomId);
        return "chosenRoomToBeRenovated";
    }

    @GetMapping(value = "students")
    public String returnAllStudents(Model model) {
        model.addAttribute("allStudents", studentMemory.getStudents());
        return "allStudents";
    }

    @GetMapping(value = "students/{studentName}")
    public String returnSpecificStudent(@PathVariable String studentName, Model model) {
        Student student = studentMemory.getStudents().stream().filter(e -> e.getName().equals(studentName)).findAny().get();
        model.addAttribute("chosenStudent", student);
        return "studentChosen";
    }

    @PostMapping(value = "students/add/{studentName}")
    public String postNewStudent(@PathVariable String studentName) {
        studentMemory.addStudent(studentName);
        return "newStudentPosted";
    }

    @DeleteMapping(value = "students/delete/{studentName}")
    public String deleteChosenStudent(@PathVariable String studentName) {
        studentMemory.deleteStudent(studentName);
        return "chosenStudentToBeDeleted";
    }

    @PostMapping(value = "rooms/addStudent/{studentName}")
    public String addNewStudentInsideRandomRoom(@PathVariable String studentName) {
        Student student = studentMemory.getStudents().stream().filter(e -> e.getName().equals(studentName)).findAny().get();
        roomMemory.addStudentToRandomRoom(student);

        return "newStudentAddedRandomly";
    }

    @PostMapping(value = "rooms/addStudent/{studentName}/{roomId}")
    public String addNewStudentInsideSpecificRoom(@PathVariable String studentName, @PathVariable int roomId) {
        Student student = studentMemory.getStudents().stream().filter(e -> e.getName().equals(studentName)).findAny().get();
        roomMemory.addStudentToASpecificRoom(student, roomId);

        return "newStudentAddedSpecifically";
    }

    @GetMapping(value = "rooms/available")
    public String seeAvailableRooms(Model model) {
        Set<Room> roomsAvailable = roomMemory.getRooms().stream().filter(e -> e.getStudentsInsideRoom().size() < 3).collect(Collectors.toSet());
        model.addAttribute("availableRooms", roomsAvailable);

        return "availableRoomsHogwarts";
    }

    @GetMapping(value = "rooms/rat-owners")
    public String seeAvailableRoomsForRatOwners(Model model) {
        Set<Room> roomsAvailable = roomMemory.roomsForRatOwners();
        model.addAttribute("ratRooms", roomsAvailable);

        return "availableRoomsRats";
    }
}
