import java.util.*;

public class SmartHealthPredictionSystem {
    static Scanner sc = new Scanner(System.in);

    static HashMap<String, List<String>> diseaseMap = new HashMap<>();

    public static void main(String[] args) {

        // Preloaded disease data
        diseaseMap.put("Flu", Arrays.asList("fever", "cold", "headache"));
        diseaseMap.put("Malaria", Arrays.asList("fever", "vomiting", "shivering"));
        diseaseMap.put("Covid-19", Arrays.asList("fever", "cough", "breathing problem"));
        diseaseMap.put("Dengue", Arrays.asList("fever", "joint pain", "rash"));

        while (true) {
            System.out.println("\n====== SMART HEALTH PREDICTION SYSTEM ======");
            System.out.println("1. Predict Disease");
            System.out.println("2. Admin Panel");
            System.out.println("3. Exit");
            System.out.print("Choose Option: ");

            int choice = getInt();

            switch (choice) {
                case 1:
                    predictDisease();
                    break;
                case 2:
                    adminMenu();
                    break;
                case 3:
                    System.out.println("Exiting System...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Prevent InputMismatchException
    static int getInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    // Predict Disease Logic
    static void predictDisease() {
        sc.nextLine();
        System.out.print("\nEnter symptoms separated by comma: ");
        String input = sc.nextLine().toLowerCase();

        List<String> userSymptoms = Arrays.asList(input.split(","));

        boolean found = false;

        System.out.println("\nPossible Diseases:");
        for (String disease : diseaseMap.keySet()) {
            List<String> symptoms = diseaseMap.get(disease);

            int match = 0;
            for (String s : userSymptoms) {
                if (symptoms.contains(s.trim())) {
                    match++;
                }
            }

            if (match >= 2) { // if at least 2 symptoms match
                System.out.println("- " + disease);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No prediction found. Please consult a doctor.");
        }
    }

    // Admin Menu
    static void adminMenu() {
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();

        if (!pass.equals("admin123")) {
            System.out.println("Wrong Password!");
            return;
        }

        while (true) {
            System.out.println("\n------ ADMIN PANEL ------");
            System.out.println("1. Add Disease");
            System.out.println("2. View All Diseases");
            System.out.println("3. Back");
            System.out.print("Choose Option: ");

            int opt = getInt();

            switch (opt) {
                case 1:
                    addDisease();
                    break;
                case 2:
                    viewDisease();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }

    static void addDisease() {
        sc.nextLine();
        System.out.print("Enter Disease Name: ");
        String name = sc.nextLine();

        System.out.print("Enter symptoms separated by comma: ");
        String sym = sc.nextLine();

        List<String> symList = Arrays.asList(sym.toLowerCase().split(","));
        diseaseMap.put(name, symList);

        System.out.println("Disease Added Successfully!");
    }

    static void viewDisease() {
        System.out.println("\nExisting Diseases:");
        for (String d : diseaseMap.keySet()) {
            System.out.println(d + " → " + diseaseMap.get(d));
        }
    }
}
