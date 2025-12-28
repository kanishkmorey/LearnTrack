package com.airtribe.learntrack.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

public class Main {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService(studentService, courseService);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMainMenu();
            try {
                int option = InputValidator.readInt(scanner, "Choose an option: ");
                switch (option) {
                    case 1:
                        handleStudentMenu();
                        break;
                    case 2:
                        handleCourseMenu();
                        break;
                    case 3:
                        handleEnrollmentMenu();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("=== LearnTrack ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    private void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            printStudentMenu();
            try {
                int option = InputValidator.readInt(scanner, "Choose an option: ");
                switch (option) {
                    case 1:
                        addStudentFlow();
                        break;
                    case 2:
                        listStudentsFlow();
                        break;
                    case 3:
                        findStudentFlow();
                        break;
                    case 4:
                        deactivateStudentFlow();
                        break;
                    case 5:
                        activateStudentFlow();
                        break;
                    case 6:
                        updateStudentEmailFlow();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (EntityNotFoundException e) {
                System.out.println("Not found: " + e.getMessage());
            }
        }
    }

    private void printStudentMenu() {
        System.out.println();
        System.out.println("--- Student Management ---");
        System.out.println("1. Add new student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Deactivate student");
        System.out.println("5. Activate student");
        System.out.println("6. Update student email");
        System.out.println("0. Back");
    }

    private void addStudentFlow() throws InvalidInputException {
        System.out.println();
        String firstName = InputValidator.readNonEmptyString(scanner, "First name: ");
        String lastName = InputValidator.readNonEmptyString(scanner, "Last name: ");
        String batch = InputValidator.readNonEmptyString(scanner, "Batch: ");

        boolean hasEmail = InputValidator.readYesNo(scanner, "Do you want to add email? (Y/N): ");
        Student student;
        if (hasEmail) {
            String email = InputValidator.readNonEmptyString(scanner, "Email: ");
            student = studentService.addStudent(firstName, lastName, email, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, batch);
        }

        System.out.println("Student added with ID: " + student.getId());
    }

    private void listStudentsFlow() {
        System.out.println();
        ArrayList<Student> students = studentService.getAllStudents();
        if (students.size() == 0) {
            System.out.println("No students found.");
            return;
        }

        int i = 0;
        while (i < students.size()) {
            printStudent(students.get(i));
            i++;
        }
    }

    private void findStudentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter student ID: ");
        Student student = studentService.findStudentById(id);
        printStudent(student);
    }

    private void deactivateStudentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    private void activateStudentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter student ID to activate: ");
        studentService.activateStudent(id);
        System.out.println("Student activated.");
    }

    private void updateStudentEmailFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter student ID: ");
        String email = InputValidator.readNonEmptyString(scanner, "New email: ");
        studentService.updateStudentEmail(id, email);
        System.out.println("Student email updated.");
    }

    private void printStudent(Student student) {
        Person person = student;
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + person.getDisplayName());
        System.out.println("Email: " + valueOrDash(student.getEmail()));
        System.out.println("Batch: " + valueOrDash(student.getBatch()));
        System.out.println("Active: " + student.isActive());
        System.out.println("--------------------------");
    }

    private void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            printCourseMenu();
            try {
                int option = InputValidator.readInt(scanner, "Choose an option: ");
                switch (option) {
                    case 1:
                        addCourseFlow();
                        break;
                    case 2:
                        listCoursesFlow();
                        break;
                    case 3:
                        activateCourseFlow();
                        break;
                    case 4:
                        deactivateCourseFlow();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (EntityNotFoundException e) {
                System.out.println("Not found: " + e.getMessage());
            }
        }
    }

    private void printCourseMenu() {
        System.out.println();
        System.out.println("--- Course Management ---");
        System.out.println("1. Add new course");
        System.out.println("2. View all courses");
        System.out.println("3. Activate course");
        System.out.println("4. Deactivate course");
        System.out.println("0. Back");
    }

    private void addCourseFlow() throws InvalidInputException {
        System.out.println();
        String name = InputValidator.readNonEmptyString(scanner, "Course name: ");
        String description = InputValidator.readNonEmptyString(scanner, "Description: ");
        int duration = InputValidator.readInt(scanner, "Duration in weeks: ");

        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added with ID: " + course.getId());
    }

    private void listCoursesFlow() {
        System.out.println();
        ArrayList<Course> courses = courseService.getAllCourses();
        if (courses.size() == 0) {
            System.out.println("No courses found.");
            return;
        }

        int i = 0;
        while (i < courses.size()) {
            printCourse(courses.get(i));
            i++;
        }
    }

    private void activateCourseFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter course ID to activate: ");
        courseService.activateCourse(id);
        System.out.println("Course activated.");
    }

    private void deactivateCourseFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int id = InputValidator.readInt(scanner, "Enter course ID to deactivate: ");
        courseService.deactivateCourse(id);
        System.out.println("Course deactivated.");
    }

    private void printCourse(Course course) {
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration (weeks): " + course.getDurationInWeeks());
        System.out.println("Active: " + course.isActive());
        System.out.println("--------------------------");
    }

    private void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            printEnrollmentMenu();
            try {
                int option = InputValidator.readInt(scanner, "Choose an option: ");
                switch (option) {
                    case 1:
                        enrollStudentFlow();
                        break;
                    case 2:
                        listEnrollmentsForStudentFlow();
                        break;
                    case 3:
                        markEnrollmentCompletedFlow();
                        break;
                    case 4:
                        cancelEnrollmentFlow();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (EntityNotFoundException e) {
                System.out.println("Not found: " + e.getMessage());
            }
        }
    }

    private void printEnrollmentMenu() {
        System.out.println();
        System.out.println("--- Enrollment Management ---");
        System.out.println("1. Enroll a student in a course");
        System.out.println("2. View enrollments for a student");
        System.out.println("3. Mark enrollment as completed");
        System.out.println("4. Cancel enrollment");
        System.out.println("0. Back");
    }

    private void enrollStudentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int studentId = InputValidator.readInt(scanner, "Student ID: ");
        int courseId = InputValidator.readInt(scanner, "Course ID: ");
        try {
            Enrollment enrollment = enrollmentService.enrollStudentInCourse(studentId, courseId);
            System.out.println("Enrollment created with ID: " + enrollment.getId());
        } catch (InvalidInputException e) {
            System.out.println("Cannot enroll: " + e.getMessage());
        }
    }

    private void listEnrollmentsForStudentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int studentId = InputValidator.readInt(scanner, "Student ID: ");
        Student student = studentService.findStudentById(studentId);

        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        if (enrollments.size() == 0) {
            System.out.println("No enrollments found for " + student.getDisplayName() + ".");
            return;
        }

        System.out.println("Enrollments for " + student.getDisplayName() + ":");
        int i = 0;
        while (i < enrollments.size()) {
            printEnrollment(enrollments.get(i));
            i++;
        }
    }

    private void markEnrollmentCompletedFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int enrollmentId = InputValidator.readInt(scanner, "Enrollment ID to mark completed: ");
        enrollmentService.markEnrollmentCompleted(enrollmentId);
        System.out.println("Enrollment marked as COMPLETED.");
    }

    private void cancelEnrollmentFlow() throws InvalidInputException, EntityNotFoundException {
        System.out.println();
        int enrollmentId = InputValidator.readInt(scanner, "Enrollment ID to cancel: ");
        enrollmentService.cancelEnrollment(enrollmentId);
        System.out.println("Enrollment marked as CANCELLED.");
    }

    private void printEnrollment(Enrollment enrollment) {
        System.out.println("Enrollment ID: " + enrollment.getId());
        System.out.println("Student ID: " + enrollment.getStudentId());
        System.out.println("Course ID: " + enrollment.getCourseId());

        try {
            Course course = courseService.findCourseById(enrollment.getCourseId());
            System.out.println("Course Name: " + course.getCourseName());
        } catch (EntityNotFoundException e) {
            System.out.println("Course Name: (not found)");
        }

        System.out.println("Date: " + enrollment.getEnrollmentDate());
        System.out.println("Status: " + enrollment.getStatus());
        System.out.println("--------------------------");
    }

    private String valueOrDash(String value) {
        if (value == null || value.trim().length() == 0) {
            return "-";
        }
        return value;
    }
}
