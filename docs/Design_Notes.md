# Design Notes

## Why ArrayList instead of an array?
- Arrays have a fixed size, so you must decide the size upfront.
- `ArrayList` grows automatically as you add more students/courses/enrollments, which fits a menu-driven app where the number of items is unknown.

## Where static members are used (and why)
- `com.airtribe.learntrack.util.IdGenerator` uses static counters and static methods so IDs can be generated without creating an object.
- `com.airtribe.learntrack.util.InputValidator` uses static helper methods to validate and parse user input in one place.

## Where inheritance is used (and what it gives)
- `Student` extends `Person` to reuse common fields like `id`, `firstName`, `lastName`, and `email`.
- `Student` overrides `getDisplayName()` to show a customized name format, demonstrating basic polymorphism.

