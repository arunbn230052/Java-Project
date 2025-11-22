import java.util.*;

public class OnlineExam {

    static Scanner sc = new Scanner(System.in);
    static HashMap<String, String> loginData = new HashMap<>();
    static int score = 0;

    public static void main(String[] args) {

        // Create login credentials
        loginData.put("arun", "12345");
        loginData.put("user", "pass");

        System.out.println("====== ONLINE EXAMINATION SYSTEM ======");
        if (login()) {
            System.out.println("Login Successful!\n");
            menu();
        } else {
            System.out.println("Invalid Login! Exiting...");
        }
    }

    // Login Method
    public static boolean login() {
        System.out.print("Enter Username: ");
        String uname = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        return loginData.containsKey(uname) && loginData.get(uname).equals(pass);
    }

    // Menu
    public static void menu() {
        int choice;

        do {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Start Exam");
            System.out.println("2. View Score");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: exam(); break;
                case 2: System.out.println("Your Score: " + score + "/5"); break;
                case 3: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid Choice!");
            }
        } while (choice != 3);
    }

    // Exam Logic
    public static void exam() {
        score = 0;
        System.out.println("\n===== EXAM STARTED =====\n");

        String[] questions = {
                "1. Which keyword is used to inherit a class in Java?",
                "2. Which of these is not a Java primitive type?",
                "3. Java is a ____ language?",
                "4. Which OOP concept binds code and data together?",
                "5. Which method is the entry point of Java?"
        };

        String[][] options = {
                {"A. implements", "B. extends", "C. super", "D. this"},
                {"A. int", "B. float", "C. boolean", "D. string"},
                {"A. Compiled", "B. Interpreted", "C. Both", "D. None"},
                {"A. Encapsulation", "B. Abstraction", "C. Inheritance", "D. Polymorphism"},
                {"A. start()", "B. main()", "C. run()", "D. execute()"}
        };

        char[] answers = {'B','D','C','A','B'};

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);

            for (int j = 0; j < options[i].length; j++) {
                System.out.println(options[i][j]);
            }

            System.out.print("Your answer: ");
            char ans = sc.next().toUpperCase().charAt(0);

            if (ans == answers[i]) {
                System.out.println("✔ Correct!\n");
                score++;
            } else {
                System.out.println("✖ Wrong! Correct answer: " + answers[i] + "\n");
            }
        }

        System.out.println("===== EXAM FINISHED =====");
        System.out.println("Your Final Score: " + score + "/5");
    }
}
