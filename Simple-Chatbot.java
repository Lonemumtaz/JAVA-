import java.io.*;
import java.util.*;

public class ChatBot {
    private static final String FILE = "chatbot_knowledge.txt";
    private Map<String, String> knowledge = new HashMap<>();

    public static void main(String[] args) {
        new ChatBot().start();
    }

    public ChatBot() {
        knowledge.put("hi", "Hello! How can I help you?");
        knowledge.put("hello", "Hi there!");
        knowledge.put("how are you", "I'm just a program, but I'm doing great!");
        knowledge.put("what is your name", "I'm ChatBot 1.0");
        knowledge.put("bye", "Goodbye! Have a nice day.");
        loadKnowledge();
    }

    private void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ChatBot: Type 'exit' to quit. Type 'teach' to add a response.");
        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().toLowerCase().trim();
            if (input.equals("exit")) break;
            if (input.equals("teach")) {
                System.out.print("Enter keyword: ");
                String key = sc.nextLine().toLowerCase().trim();
                System.out.print("Enter response: ");
                String resp = sc.nextLine();
                knowledge.put(key, resp);
                saveKnowledge();
                System.out.println("Learning completed.");
                continue;
            }
            boolean found = false;
            for (String key : knowledge.keySet()) {
                if (input.contains(key)) {
                    System.out.println("ChatBot: " + knowledge.get(key));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("ChatBot: I don't understand that. Teach me? (type 'teach')");
            }
        }
        sc.close();
    }

    private void saveKnowledge() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, String> e : knowledge.entrySet()) {
                pw.println(e.getKey() + "|" + e.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving knowledge.");
        }
    }

    private void loadKnowledge() {
        File f = new File(FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2) knowledge.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error loading knowledge.");
        }
    }
}
