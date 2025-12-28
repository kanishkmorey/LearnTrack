package com.airtribe.learntrack.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    private final ArrayList<Enrollment> enrollments;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<Enrollment>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudentInCourse(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Student is inactive. Cannot enroll.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Course is inactive. Cannot enroll.");
        }

        if (hasActiveEnrollment(studentId, courseId)) {
            throw new InvalidInputException("Student is already enrolled in this course (ACTIVE).");
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, LocalDate.now(), STATUS_ACTIVE);
        enrollments.add(enrollment);
        return enrollment;
    }

    public ArrayList<Enrollment> getEnrollmentsForStudent(int studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId);

        ArrayList<Enrollment> result = new ArrayList<Enrollment>();
        int i = 0;
        while (i < enrollments.size()) {
            Enrollment enrollment = enrollments.get(i);
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
            i++;
        }
        return result;
    }

    public void markEnrollmentCompleted(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(STATUS_COMPLETED);
    }

    public void cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(STATUS_CANCELLED);
    }

    public Enrollment findEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        int i = 0;
        while (i < enrollments.size()) {
            Enrollment enrollment = enrollments.get(i);
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
            i++;
        }
        throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
    }

    private boolean hasActiveEnrollment(int studentId, int courseId) {
        int i = 0;
        while (i < enrollments.size()) {
            Enrollment enrollment = enrollments.get(i);
            boolean sameStudent = enrollment.getStudentId() == studentId;
            boolean sameCourse = enrollment.getCourseId() == courseId;
            boolean active = STATUS_ACTIVE.equals(enrollment.getStatus());
            if (sameStudent && sameCourse && active) {
                return true;
            }
            i++;
        }
        return false;
    }
}

