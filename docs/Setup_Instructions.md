# Setup Instructions (Java 21)

## JDK Version Used
- Java 21 (LTS)

## Verify Java Installation
Run:
- `java -version`
- `javac -version`

Both should show version 21.x.

## Hello World (Terminal)
1) Create a file named `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

2) Compile:
- `javac HelloWorld.java`

3) Run:
- `java HelloWorld`

Expected output:
- `Hello, World!`

## LearnTrack Compilation
From the project root:
- `javac -d out $(find src -name "*.java")`
- `java -cp out com.airtribe.learntrack.ui.Main`

