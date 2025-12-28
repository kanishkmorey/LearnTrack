package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {
    private final ArrayList<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<Course>();
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> copy = new ArrayList<Course>();
        int i = 0;
        while (i < courses.size()) {
            copy.add(courses.get(i));
            i++;
        }
        return copy;
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        int i = 0;
        while (i < courses.size()) {
            Course course = courses.get(i);
            if (course.getId() == id) {
                return course;
            }
            i++;
        }
        throw new EntityNotFoundException("Course not found with ID: " + id);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(false);
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
    }
}

