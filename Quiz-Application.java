import java.io.*;
import java.util.*;

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect(int answer) { return answer == correctAnswer; }
}

public class QuizApp {
    public static void main(String[] args) {
        List<Question> questions = loadQuestions("questions.txt");
        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }
        Collections.shuffle(questions);
        Scanner sc = new Scanner(System.in);
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ". " + q.getQuestion());
            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                System.out.println((j + 1) + ". " + opts[j]);
            }
            System.out.print("Your answer (1-4): ");
            int ans = sc.nextInt();
            if (q.isCorrect(ans - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong. Correct was: " + (q.getCorrectAnswer() + 1));
            }
        }
        System.out.println("\nYour final score: " + score + "/" + questions.size());
        sc.close();
    }

    private static List<Question> loadQuestions(String filename) {
        List<Question> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 6) continue;
                String qText = parts[0];
                String[] opts = {parts[1], parts[2], parts[3], parts[4]};
                int correct = Integer.parseInt(parts[5]) - 1;
                list.add(new Question(qText, opts, correct));
            }
        } catch (IOException e) {
            System.out.println("Error loading questions.");
        }
        return list;
    }
}
