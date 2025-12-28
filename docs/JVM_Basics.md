# JVM Basics (Simple Explanation)

## JDK, JRE, JVM
- **JVM (Java Virtual Machine)**: The program that runs Java bytecode. It acts like a “virtual computer” for Java programs.
- **JRE (Java Runtime Environment)**: Everything you need to *run* a Java program (JVM + core libraries).
- **JDK (Java Development Kit)**: Everything you need to *build and run* Java programs (JRE + developer tools like `javac`).

## What is Bytecode?
When you compile a `.java` file using `javac`, it produces a `.class` file. The `.class` file contains **bytecode**, which is not directly machine code. The JVM reads this bytecode and runs it on your computer.

## “Write Once, Run Anywhere”
You write and compile your Java code into bytecode once. Then the same bytecode can run on any machine that has a compatible JVM (Windows, macOS, Linux), without changing your program code.

