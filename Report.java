import java.util.HashMap;
import java.util.Map;

public class Report {
    private static final HashMap<String, Double> reports = new HashMap<>();

    public static void generateReport(String studentName, double score) {
        reports.put(studentName, score);
        System.out.println("Report generated for " + studentName + ": " + score + "% plagiarized.");
    }

    public static void viewAllReports() {
        System.out.println("All Plagiarism Reports:");
        for (Map.Entry<String, Double> entry : reports.entrySet()) {
            System.out.println("Student: " + entry.getKey() + " | Score: " + entry.getValue() + "%");
        }
    }
}
