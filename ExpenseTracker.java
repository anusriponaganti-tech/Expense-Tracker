import java.util.ArrayList;
import java.util.Scanner;

class Expense {

    int id;
    String date;
    String category;
    String description;
    double amount;

    Expense(int id, String date, String category,
            String description, double amount) {

        this.id = id;
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    void display() {

        System.out.println(
            "ID: " + id +
            " | Date: " + date +
            " | Category: " + category +
            " | Description: " + description +
            " | Amount: ₹" + amount
        );
    }
}

public class ExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int nextId = 1;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("          EXPENSE TRACKER");
            System.out.println("====================================");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Search Expense");
            System.out.println("6. Total Expenses");
            System.out.println("7. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    updateExpense();
                    break;

                case 4:
                    deleteExpense();
                    break;

                case 5:
                    searchExpense();
                    break;

                case 6:
                    totalExpenses();
                    break;

                case 7:
                    System.out.println("Thank you for using Expense Tracker!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ------------------------------------
    // ADD EXPENSE
    // ------------------------------------

    static void addExpense() {

        System.out.println("\n--- Add Expense ---");

        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter amount: ");

        double amount;

        try {
            amount = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid amount!");
            return;
        }

        Expense expense = new Expense(
            nextId,
            date,
            category,
            description,
            amount
        );

        expenses.add(expense);

        nextId++;

        System.out.println("Expense added successfully!");
    }

    // ------------------------------------
    // VIEW EXPENSES
    // ------------------------------------

    static void viewExpenses() {

        System.out.println("\n--- All Expenses ---");

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        for (Expense expense : expenses) {
            expense.display();
        }
    }

    // ------------------------------------
    // UPDATE EXPENSE
    // ------------------------------------

    static void updateExpense() {

        System.out.println("\n--- Update Expense ---");

        System.out.print("Enter Expense ID: ");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid ID!");
            return;
        }

        Expense expense = findExpense(id);

        if (expense == null) {
            System.out.println("Expense not found!");
            return;
        }

        System.out.print("Enter new date: ");
        expense.date = sc.nextLine();

        System.out.print("Enter new category: ");
        expense.category = sc.nextLine();

        System.out.print("Enter new description: ");
        expense.description = sc.nextLine();

        System.out.print("Enter new amount: ");

        try {
            expense.amount =
                Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid amount!");
            return;
        }

        System.out.println("Expense updated successfully!");
    }

    // ------------------------------------
    // DELETE EXPENSE
    // ------------------------------------

    static void deleteExpense() {

        System.out.println("\n--- Delete Expense ---");

        System.out.print("Enter Expense ID: ");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid ID!");
            return;
        }

        Expense expense = findExpense(id);

        if (expense == null) {
            System.out.println("Expense not found!");
            return;
        }

        expenses.remove(expense);

        System.out.println("Expense deleted successfully!");
    }

    // ------------------------------------
    // SEARCH EXPENSE
    // ------------------------------------

    static void searchExpense() {

        System.out.println("\n--- Search Expense ---");

        System.out.print("Enter category: ");

        String category = sc.nextLine();

        boolean found = false;

        for (Expense expense : expenses) {

            if (expense.category.equalsIgnoreCase(category)) {

                expense.display();

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "No expenses found for this category."
            );
        }
    }

    // ------------------------------------
    // TOTAL EXPENSE
    // ------------------------------------

    static void totalExpenses() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        System.out.println("\n----------------------------");
        System.out.println("Total Expenses: ₹" + total);
        System.out.println("----------------------------");
    }

    // ------------------------------------
    // FIND EXPENSE
    // ------------------------------------

    static Expense findExpense(int id) {

        for (Expense expense : expenses) {

            if (expense.id == id) {
                return expense;
            }
        }

        return null;
    }
}