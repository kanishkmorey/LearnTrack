package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

public class StudentService {
    private final ArrayList<Student> students;

    public StudentService() {
        this.students = new ArrayList<Student>();
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> copy = new ArrayList<Student>();
        int i = 0;
        while (i < students.size()) {
            copy.add(students.get(i));
            i++;
        }
        return copy;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        int i = 0;
        while (i < students.size()) {
            Student student = students.get(i);
            if (student.getId() == id) {
                return student;
            }
            i++;
        }
        throw new EntityNotFoundException("Student not found with ID: " + id);
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void activateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(true);
    }

    public void updateStudentEmail(int id, String newEmail) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setEmail(newEmail);
    }

    public void removeStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        students.remove(student);
    }
}

