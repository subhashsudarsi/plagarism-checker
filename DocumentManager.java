import java.util.HashMap;
import java.util.Map;

public class DocumentManager {
    private static final Map<String, Map<String, String>> userDocuments = new HashMap<>();

    public static void addDocument(String username, String content) {
        userDocuments.putIfAbsent(username, new HashMap<>());
        Map<String, String> docs = userDocuments.get(username);
        String docName = "doc" + (docs.size() + 1);
        docs.put(docName, content);
    }

    public static Map<String, String> getDocumentsByUser(String username) {
        return userDocuments.getOrDefault(username, new HashMap<>());
    }

    public static Map<String, Map<String, String>> getAllDocuments() {
        return userDocuments;
    }
}
