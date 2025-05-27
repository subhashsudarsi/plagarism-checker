import java.util.Scanner;

public class Admin extends User {
    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    public void viewReports() {
        System.out.println("Viewing reports...");
        // You can enhance this to read actual reports if stored somewhere
    }

    public void viewAllLogins() {
        System.out.println("\n--- All Registered Users ---");
        UserManager.printAllUsers();  // <-- Ensure this line is typed correctly
    }    

    // Optional: Admin menu logic (if handled inside Admin class)
    public void adminMenu(Scanner scanner) {
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
                case 1 -> viewReports();
                case 2 -> viewAllLogins();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
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
