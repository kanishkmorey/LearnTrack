# LearnTrack (Core Java Console App)

LearnTrack is a beginner-friendly, console-based Student & Course Management System built using only Core Java fundamentals.

## Features
- Student Management: add, list, search by ID, activate/deactivate, update email
- Course Management: add, list, activate/deactivate
- Enrollment Management: enroll student, view enrollments for a student, mark completed/cancelled

## Project Structure
- `src/com/airtribe/learntrack/entity` – Entity classes (`Student`, `Course`, `Enrollment`, ...)
- `src/com/airtribe/learntrack/service` – Business logic (`StudentService`, `CourseService`, `EnrollmentService`)
- `src/com/airtribe/learntrack/ui` – Console UI (`Main`)
- `src/com/airtribe/learntrack/exception` – Custom exceptions
- `src/com/airtribe/learntrack/util` – Utility classes (`IdGenerator`, `InputValidator`)
- `docs/` – Learning documentation

## Requirements
- Java 21 (LTS)
- No external libraries, no Maven/Gradle

## Compile and Run (Terminal)
From the project root:

1) Compile:
`javac -d out $(find src -name "*.java")`

2) Run:
`java -cp out com.airtribe.learntrack.ui.Main`

