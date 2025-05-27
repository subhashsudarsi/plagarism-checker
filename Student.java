import java.util.Scanner;

public class Student extends User {
    public Student(String username, String email, String password) {
        super(username, email, password);
    }

    public void uploadDocument(Scanner scanner) {
        System.out.println("Start typing your document. Type 'END' on a new line to finish:");

        StringBuilder contentBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            contentBuilder.append(line).append("\n");
        }

        String content = contentBuilder.toString().trim();
        if (content.isEmpty()) {
            System.out.println("Document is empty. Not saved.");
        } else {
            DocumentManager.addDocument(getUsername(), content);
            System.out.println("Document uploaded successfully.");
        }
    }
}
