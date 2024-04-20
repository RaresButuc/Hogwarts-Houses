package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class StudentMemory implements StudentDAO {

    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public void deleteStudent(String name) {
        Student student = students.stream().filter(e -> e.getName().equals(name)).findAny().get();
        students.remove(student);
    }
}
