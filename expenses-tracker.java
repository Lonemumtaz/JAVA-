import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Expense {
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(String description, double amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return date + " | " + description + " | ₹" + String.format("%.2f", amount) + " | " + category;
    }

    public String toCSV() {
        return description + "," + amount + "," + category + "," + date;
    }

    public static Expense fromCSV(String line) {
        String[] p = line.split(",");
        return new Expense(p[0], Double.parseDouble(p[1]), p[2], LocalDate.parse(p[3]));
    }
}

public class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();
    private static final String FILE = "expenses.csv";

    public static void main(String[] args) {
        new ExpenseTracker().run();
    }

    private void run() {
        loadExpenses();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All");
            System.out.println("3. Filter by Category");
            System.out.println("4. Filter by Month");
            System.out.println("5. Total by Category");
            System.out.println("6. Export to CSV");
            System.out.println("7. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();
            switch (ch) {
                case 1 -> addExpense(sc);
                case 2 -> viewAll();
                case 3 -> filterByCategory(sc);
                case 4 -> filterByMonth(sc);
                case 5 -> totalByCategory();
                case 6 -> saveExpenses();
                case 7 -> { saveExpenses(); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void addExpense(Scanner sc) {
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Amount: ");
        double amt = sc.nextDouble(); sc.nextLine();
        System.out.print("Category: ");
        String cat = sc.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        expenses.add(new Expense(desc, amt, cat, date));
        System.out.println("Added.");
    }

    private void viewAll() {
        if (expenses.isEmpty()) System.out.println("No expenses.");
        else expenses.forEach(System.out::println);
    }

    private void filterByCategory(Scanner sc) {
        System.out.print("Category: ");
        String cat = sc.nextLine();
        expenses.stream().filter(e -> e.getCategory().equalsIgnoreCase(cat)).forEach(System.out::println);
    }

    private void filterByMonth(Scanner sc) {
        System.out.print("Year-Month (YYYY-MM): ");
        String ym = sc.nextLine();
        expenses.stream().filter(e -> e.getDate().toString().startsWith(ym)).forEach(System.out::println);
    }

    private void totalByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Expense e : expenses) {
            totals.merge(e.getCategory(), e.getAmount(), Double::sum);
        }
        totals.forEach((cat, sum) -> System.out.println(cat + ": ₹" + String.format("%.2f", sum)));
    }

    private void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Expense e : expenses) pw.println(e.toCSV());
            System.out.println("Saved to " + FILE);
        } catch (IOException e) {
            System.out.println("Error saving.");
        }
    }

    private void loadExpenses() {
        File f = new File(FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                expenses.add(Expense.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading.");
        }
    }
}