import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private static final Map<String, User> users = new HashMap<>();

    public static User register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return null;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Select role (1. Student, 2. Faculty, 3. Admin): ");
        int role = scanner.nextInt();
        scanner.nextLine(); // consume newline

        User user;
        switch (role) {
            case 1 -> user = new Student(username, email, password);
            case 2 -> user = new Faculty(username, email, password);
            case 3 -> user = new Admin(username, email, password);
            default -> {
                System.out.println("Invalid role selected.");
                return null;
            }
        }

        users.put(username, user);
        return user;
    }

    public static User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public static void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
        } else {
            users.forEach((username, user) -> {
                if (user != null) {
                    String role = "Unknown";
                    if (user instanceof Student) role = "Student";
                    else if (user instanceof Faculty) role = "Faculty";
                    else if (user instanceof Admin) role = "Admin";

                    System.out.printf("Username: %s | Email: %s | Role: %s%n",
                            user.getUsername(), user.getEmail(), role);
                } else {
                    System.out.println("Encountered a null user entry.");
                }
            });
        }
    }
}
