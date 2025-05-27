import java.util.HashSet;

public class PlagiarismChecker {

    public static double checkPlagiarism(String content1, String content2) {
        int totalWords = content1.split("\\s+").length;
        int matchedWords = countCommonWords(content1, content2);

        double score = ((double) matchedWords / totalWords) * 100;
        return score;
    }

    private static int countCommonWords(String content1, String content2) {
        HashSet<String> set1 = new HashSet<>();
        for (String word : content1.split("\\s+")) {
            set1.add(word.toLowerCase());
        }

        HashSet<String> set2 = new HashSet<>();
        for (String word : content2.split("\\s+")) {
            set2.add(word.toLowerCase());
        }

        set1.retainAll(set2); // Keeps only common words
        return set1.size();
    }
}
