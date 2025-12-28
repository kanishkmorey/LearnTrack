package com.airtribe.learntrack.util;

import java.util.Scanner;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    private InputValidator() {
    }

    public static int readInt(Scanner scanner, String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return parseInt(input);
    }

    public static int parseInt(String input) throws InvalidInputException {
        if (input == null) {
            throw new InvalidInputException("Input cannot be null.");
        }

        String trimmed = input.trim();
        if (trimmed.length() == 0) {
            throw new InvalidInputException("Please enter a number.");
        }

        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number: " + trimmed);
        }
    }

    public static String readNonEmptyString(Scanner scanner, String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return requireNonEmpty(input, "Input cannot be empty.");
    }

    public static String requireNonEmpty(String value, String message) throws InvalidInputException {
        if (value == null || value.trim().length() == 0) {
            throw new InvalidInputException(message);
        }
        return value.trim();
    }

    public static boolean readYesNo(Scanner scanner, String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input == null) {
            throw new InvalidInputException("Please enter Y or N.");
        }

        String trimmed = input.trim().toUpperCase();
        if ("Y".equals(trimmed) || "YES".equals(trimmed)) {
            return true;
        }
        if ("N".equals(trimmed) || "NO".equals(trimmed)) {
            return false;
        }

        throw new InvalidInputException("Please enter Y or N.");
    }
}

