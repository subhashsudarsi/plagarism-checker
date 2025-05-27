import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OUTER:
            while (true) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int authChoice = scanner.nextInt();
                scanner.nextLine();
                User user;
                switch (authChoice) {
                    case 1 -> {
                        // Register user and then return to main menu to login
                        user = UserManager.register(scanner);
                        if (user != null) {
                            System.out.println("Registration successful!");
                        }
                    }
                    case 2 -> // Login existing user
                        user = UserManager.login(scanner);
                    case 3 -> {
                        System.out.println("Exiting program.");
                        break OUTER; // Exit the program
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        continue;
                    }
                }
                if (user == null) {
                    System.out.println("Authentication failed.");
                    continue;
                }
                System.out.println("Welcome, " + user.getUsername() + "!");
                boolean logout = false;
                while (!logout) {
                    switch (user) {
                        case Student student -> logout = studentRoleMenu(scanner, student);
                        case Faculty faculty -> logout = facultyRoleMenu(scanner, faculty);
                        case Admin admin -> logout = adminRoleMenu(scanner, admin);
                        default -> {
                            System.out.println("Invalid role.");
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean studentRoleMenu(Scanner scanner, Student student) {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Upload Document");
            System.out.println("2. Log Out");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> student.uploadDocument(scanner);
                case 2 -> {
                    System.out.println("Logging out...");
                    return true; // Return to main menu after logging out
                }
                case 3 -> {
                    System.out.println("Exiting program.");
                    System.exit(0); // Exit the program completely
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static boolean facultyRoleMenu(Scanner scanner, Faculty faculty) {
        while (true) {
            System.out.println("\n--- Faculty Menu ---");
            System.out.println("1. Check Plagiarism");
            System.out.println("2. Log Out");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> faculty.checkForPlagiarism(scanner);
                case 2 -> {
                    System.out.println("Logging out...");
                    return true; // Return to main menu after logging out
                }
                case 3 -> {
                    System.out.println("Exiting program.");
                    System.exit(0); // Exit the program completely
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static boolean adminRoleMenu(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Reports");
            System.out.println("2. View All Logins");
            System.out.println("3. Log Out");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
    
            switch (choice) {
                case 1 -> admin.viewReports();
                case 2 -> admin.viewAllLogins();
                case 3 -> {
                    System.out.println("Logging out...");
                    return true;
                }
                case 4 -> {
                    System.out.println("Exiting program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }    
}