import java.util.*;

public class Faculty extends User {
    public Faculty(String username, String email, String password) {
        super(username, email, password);
    }

    public void checkForPlagiarism(Scanner scanner) {
        System.out.print("Enter student username to check: ");
        String studentName = scanner.nextLine();

        Map<String, String> studentDocs = DocumentManager.getDocumentsByUser(studentName);
        if (studentDocs == null || studentDocs.isEmpty()) {
            System.out.println("No documents found for the student.");
            return;
        }

        // Get the latest document (based on doc key order)
        String latestDocKey = Collections.max(studentDocs.keySet());
        String latestDoc = studentDocs.get(latestDocKey);

        System.out.println("Checking plagiarism for latest document: " + latestDocKey);

        double highestScore = 0;
        String mostSimilarUser = "N/A";
        String mostSimilarDoc = "N/A";

        // Compare with all other documents in the system (from all users)
        Map<String, Map<String, String>> allDocs = DocumentManager.getAllDocuments();
        for (Map.Entry<String, Map<String, String>> entry : allDocs.entrySet()) {
            String otherUser = entry.getKey();
            if (otherUser.equals(studentName)) continue; // Skip comparing to self

            for (Map.Entry<String, String> docEntry : entry.getValue().entrySet()) {
                double score = PlagiarismChecker.checkPlagiarism(latestDoc, docEntry.getValue());
                if (score > highestScore) {
                    highestScore = score;
                    mostSimilarUser = otherUser;
                    mostSimilarDoc = docEntry.getKey();
                }
            }
        }

        System.out.printf("Highest plagiarism score found: %.2f%% with user '%s', document '%s'%n",
                highestScore, mostSimilarUser, mostSimilarDoc);

        Report.generateReport(studentName, highestScore);
    }
}
