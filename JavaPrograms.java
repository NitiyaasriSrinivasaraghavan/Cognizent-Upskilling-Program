import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaPrograms {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            int task = Integer.parseInt(args[0]);
            runTask(task);
        } else {
            System.out.println("Provide a task number 1-41 as argument.");
        }
    }

    private static void runTask(int task) throws Exception {
        switch (task) {
            case 1 -> task1();
            case 2 -> task2();
            case 3 -> task3();
            case 4 -> task4();
            case 5 -> task5();
            case 6 -> task6();
            case 7 -> task7();
            case 8 -> task8();
            case 9 -> task9();
            case 10 -> task10();
            case 11 -> task11();
            case 12 -> task12();
            case 13 -> task13();
            case 14 -> task14();
            case 15 -> task15();
            case 16 -> task16();
            case 17 -> task17();
            case 18 -> task18();
            case 19 -> task19();
            case 20 -> task20();
            case 21 -> task21();
            case 22 -> task22();
            case 23 -> task23();
            case 24 -> task24();
            case 25 -> task25();
            case 26 -> task26();
            case 27 -> task27();
            case 28 -> task28();
            case 29 -> task29();
            case 30 -> task30();
            case 31 -> task31();
            case 32 -> task32();
            case 33 -> task33();
            case 34 -> task34();
            case 35 -> task35();
            case 36 -> task36();
            case 37 -> task37();
            case 38 -> task38();
            case 39 -> task39();
            case 40 -> task40();
            case 41 -> task41();
            default -> System.out.println("Invalid task number.");
        }
    }

    private static void task1() {
        System.out.println("Hello, World!");
    }

    private static void task2() {
        System.out.println("Enter first number:");
        double a = input.nextDouble();
        System.out.println("Enter second number:");
        double b = input.nextDouble();
        System.out.println("Choose operation (+ - * /):");
        String op = input.next();
        double result;
        switch (op) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = b != 0 ? a / b : Double.NaN;
            default -> {
                System.out.println("Invalid operation");
                return;
            }
        }
        System.out.println("Result: " + result);
    }

    private static void task3() {
        System.out.println("Enter an integer:");
        int number = input.nextInt();
        System.out.println(number % 2 == 0 ? "Even" : "Odd");
    }

    private static void task4() {
        System.out.println("Enter a year:");
        int year = input.nextInt();
        boolean leap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        System.out.println(leap ? "Leap year" : "Not a leap year");
    }

    private static void task5() {
        System.out.println("Enter a number:");
        int number = input.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }

    private static void task6() {
        int n = 42;
        float f = 3.14f;
        double d = 2.71828;
        char c = 'A';
        boolean b = true;
        System.out.println(n);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(b);
    }

    private static void task7() {
        double d = 9.99;
        int i = (int) d;
        System.out.println(i);
        int j = 7;
        double x = j;
        System.out.println(x);
    }

    private static void task8() {
        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        System.out.println(result1);
        System.out.println(result2);
    }

    private static void task9() {
        System.out.println("Enter marks out of 100:");
        int marks = input.nextInt();
        String grade;
        if (marks >= 90) grade = "A";
        else if (marks >= 80) grade = "B";
        else if (marks >= 70) grade = "C";
        else if (marks >= 60) grade = "D";
        else grade = "F";
        System.out.println("Grade: " + grade);
    }

    private static void task10() {
        int target = new Random().nextInt(100) + 1;
        int guess = 0;
        while (guess != target) {
            System.out.println("Guess a number between 1 and 100:");
            guess = input.nextInt();
            if (guess < target) System.out.println("Too low");
            else if (guess > target) System.out.println("Too high");
            else System.out.println("Correct");
        }
    }

    private static void task11() {
        System.out.println("Enter a non-negative integer:");
        int n = input.nextInt();
        long fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        System.out.println(fact);
    }

    private static void task12() {
        System.out.println(add(3, 5));
        System.out.println(add(2.5, 4.1));
        System.out.println(add(1, 2, 3));
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static int add(int a, int b, int c) {
        return a + b + c;
    }

    private static void task13() {
        System.out.println("Enter a positive integer:");
        int n = input.nextInt();
        System.out.println(fibonacci(n));
    }

    private static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static void task14() {
        System.out.println("Enter number of elements:");
        int count = input.nextInt();
        int[] array = new int[count];
        int sum = 0;
        for (int i = 0; i < count; i++) {
            array[i] = input.nextInt();
            sum += array[i];
        }
        double average = count > 0 ? (double) sum / count : 0;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    private static void task15() {
        System.out.println("Enter a string:");
        input.nextLine();
        String text = input.nextLine();
        String reversed = new StringBuilder(text).reverse().toString();
        System.out.println(reversed);
    }

    private static void task16() {
        System.out.println("Enter a string:");
        input.nextLine();
        String text = input.nextLine();
        String normalized = text.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(normalized).reverse().toString();
        System.out.println(normalized.equals(reversed) ? "Palindrome" : "Not a palindrome");
    }

    private static void task17() {
        Car car1 = new Car("Toyota", "Corolla", 2020);
        Car car2 = new Car("Honda", "Civic", 2022);
        car1.displayDetails();
        car2.displayDetails();
    }

    private static class Car {
        String make;
        String model;
        int year;

        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        void displayDetails() {
            System.out.println(make + " " + model + " " + year);
        }
    }

    private static void task18() {
        Animal animal = new Animal();
        Dog dog = new Dog();
        animal.makeSound();
        dog.makeSound();
    }

    private static class Animal {
        void makeSound() {
            System.out.println("Some sound");
        }
    }

    private static class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Bark");
        }
    }

    private static void task19() {
        Playable guitar = new Guitar();
        Playable piano = new Piano();
        guitar.play();
        piano.play();
    }

    private interface Playable {
        void play();
    }

    private static class Guitar implements Playable {
        public void play() {
            System.out.println("Guitar is playing");
        }
    }

    private static class Piano implements Playable {
        public void play() {
            System.out.println("Piano is playing");
        }
    }

    private static void task20() {
        System.out.println("Enter dividend:");
        int a = input.nextInt();
        System.out.println("Enter divisor:");
        int b = input.nextInt();
        try {
            int result = a / b;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }

    private static void task21() {
        System.out.println("Enter your age:");
        int age = input.nextInt();
        try {
            validateAge(age);
            System.out.println("Age is valid");
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Age must be at least 18");
    }

    private static class InvalidAgeException extends Exception {
        InvalidAgeException(String message) {
            super(message);
        }
    }

    private static void task22() throws IOException {
        System.out.println("Enter text to write:");
        input.nextLine();
        String text = input.nextLine();
        Files.writeString(Path.of("output.txt"), text);
        System.out.println("Written to output.txt");
    }

    private static void task23() throws IOException {
        Path path = Path.of("output.txt");
        if (Files.exists(path)) {
            Files.lines(path).forEach(System.out::println);
        } else {
            System.out.println("output.txt not found");
        }
    }

    private static void task24() {
        System.out.println("Enter number of names:");
        int count = input.nextInt();
        List<String> names = new ArrayList<>();
        input.nextLine();
        for (int i = 0; i < count; i++) {
            names.add(input.nextLine());
        }
        names.forEach(System.out::println);
    }

    private static void task25() {
        System.out.println("Enter number of entries:");
        int count = input.nextInt();
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Enter ID:");
            int id = input.nextInt();
            input.nextLine();
            System.out.println("Enter name:");
            String name = input.nextLine();
            map.put(id, name);
        }
        System.out.println("Enter ID to look up:");
        int lookup = input.nextInt();
        System.out.println(map.getOrDefault(lookup, "Not found"));
    }

    private static void task26() throws InterruptedException {
        MessageThread thread1 = new MessageThread("Thread 1");
        MessageThread thread2 = new MessageThread("Thread 2");
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static class MessageThread implements Runnable {
        private final String message;

        MessageThread(String message) {
            this.message = message;
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(message + " " + (i + 1));
            }
        }
    }

    private static void task27() {
        List<String> items = new ArrayList<>();
        items.add("Banana");
        items.add("Apple");
        items.add("Cherry");
        Collections.sort(items, (a, b) -> a.compareTo(b));
        items.forEach(System.out::println);
    }

    private static void task28() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(evens);
    }

    private static void task29() {
        List<Person> people = List.of(new Person("Alice", 25), new Person("Bob", 17), new Person("Carol", 30));
        people.stream().filter(p -> p.age() >= 18).forEach(System.out::println);
    }

    public record Person(String name, int age) {}

    private static void task30() {
        Object[] values = {42, "hello", 3.14, true};
        for (Object value : values) {
            switch (value) {
                case Integer i -> System.out.println("Integer: " + i);
                case String s -> System.out.println("String: " + s);
                case Double d -> System.out.println("Double: " + d);
                case Boolean b -> System.out.println("Boolean: " + b);
                default -> System.out.println("Unknown type");
            }
        }
    }

    private static void task31() throws SQLException {
        String url = "jdbc:sqlite:students.db";
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY, name TEXT)");
            stmt.execute("INSERT OR IGNORE INTO students(id, name) VALUES(1, 'Alice'), (2, 'Bob')");
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM students");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
            }
        }
    }

    private static void task32() throws SQLException {
        String url = "jdbc:sqlite:students.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            StudentDAO dao = new StudentDAO(conn);
            System.out.println("Enter new student id:");
            int id = input.nextInt();
            input.nextLine();
            System.out.println("Enter new student name:");
            String name = input.nextLine();
            dao.insertStudent(id, name);
            System.out.println("Enter id to update:");
            int updateId = input.nextInt();
            input.nextLine();
            System.out.println("Enter name to update:");
            String updateName = input.nextLine();
            dao.updateStudent(updateId, updateName);
            System.out.println("Completed");
        }
    }

    private static class StudentDAO {
        private final Connection conn;

        StudentDAO(Connection conn) {
            this.conn = conn;
        }

        void insertStudent(int id, String name) throws SQLException {
            try (PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO students(id, name) VALUES(?, ?)") ) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.executeUpdate();
            }
        }

        void updateStudent(int id, String name) throws SQLException {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE students SET name = ? WHERE id = ?")) {
                ps.setString(1, name);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
    }

    private static void task33() throws SQLException {
        String url = "jdbc:sqlite:accounts.db";
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts(id INTEGER PRIMARY KEY, balance INTEGER)");
            stmt.execute("INSERT OR IGNORE INTO accounts(id, balance) VALUES(1, 1000), (2, 1000)");
            transfer(conn, 1, 2, 100);
        }
    }

    private static void transfer(Connection conn, int fromId, int toId, int amount) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement debit = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
             PreparedStatement credit = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            debit.setInt(1, amount);
            debit.setInt(2, fromId);
            credit.setInt(1, amount);
            credit.setInt(2, toId);
            debit.executeUpdate();
            credit.executeUpdate();
            conn.commit();
            System.out.println("Transfer complete");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transfer failed");
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void task34() {
        System.out.println("Create module-info.java in separate module folders to define com.greetings and com.utils modules.");
    }

    private static void task35() throws IOException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000); Socket clientSocket = serverSocket.accept()) {
                latch.countDown();
                clientSocket.getOutputStream().write("Hello from server\n".getBytes());
            } catch (IOException ignored) {
            }
        }).start();
        try {
            latch.await();
            try (Socket socket = new Socket("localhost", 5000)) {
                byte[] buffer = new byte[1024];
                int read = socket.getInputStream().read(buffer);
                if (read > 0) System.out.println(new String(buffer, 0, read));
            }
        } catch (InterruptedException ignored) {
        }
    }

    private static void task36() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.github.com")).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    private static void task37() {
        System.out.println("Compile a class and run javap -c ClassName to inspect bytecode.");
    }

    private static void task38() {
        System.out.println("Use a decompiler such as CFR or JD-GUI on a compiled .class file to view source.");
    }

    private static void task39() throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        var methods = clazz.getDeclaredMethods();
        for (var method : methods) {
            System.out.println(method.getName());
        }
    }

    private static void task40() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100000);
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int index = i;
            Thread.startVirtualThread(() -> {
                if (index < 5) System.out.println("Virtual thread " + index);
                latch.countDown();
            });
        }
        latch.await();
        long duration = System.nanoTime() - start;
        System.out.println("Virtual threads completed in " + duration / 1_000_000 + " ms");
    }

    private static void task41() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Callable<String>> tasks = List.of(
                () -> "Result 1",
                () -> "Result 2",
                () -> "Result 3"
        );
        List<Future<String>> futures = executor.invokeAll(tasks);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        executor.shutdown();
    }
}
