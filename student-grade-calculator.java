import java.util.*;
import java.io.*;

public class GradeCalculator {
    public static void main(String[] args) {
        List<String[]> studentData = new ArrayList<>();
        File f = new File("data.txt");
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    studentData.add(line.split(","));
                }
            } catch (IOException e) {
                System.out.println("Error reading file.");
                return;
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("How many students? ");
            int n = sc.nextInt(); sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter name and 5 scores (comma separated): ");
                String line = sc.nextLine();
                studentData.add(line.split(","));
            }
        }

        System.out.println("\nName\t\tAverage\tGrade");
        System.out.println("-----------------------------");
        for (String[] s : studentData) {
            String name = s[0];
            int sum = 0;
            for (int i = 1; i < s.length; i++) sum += Integer.parseInt(s[i].trim());
            double avg = sum / (s.length - 1.0);
            String grade = getGrade(avg);
            System.out.printf("%-15s %.1f\t%s\n", name, avg, grade);
        }
    }

    private static String getGrade(double avg) {
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }
}
