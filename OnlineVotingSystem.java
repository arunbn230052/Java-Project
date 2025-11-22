import java.util.*;

class OnlineVotingSystem {

    static Scanner sc = new Scanner(System.in);

    // Admin credentials
    static String adminUser = "admin";
    static String adminPass = "12345";

    // Voter details
    static HashMap<String, String> voters = new HashMap<>();  
    static HashSet<String> votedUsers = new HashSet<>();     

    // Candidates and votes
    static LinkedHashMap<String, Integer> candidates = new LinkedHashMap<>();


    public static void main(String[] args) {

        // Sample voters
        voters.put("arun", "1111");
        voters.put("user1", "2222");
        voters.put("user2", "3333");

        // Default candidates
        candidates.put("Candidate A", 0);
        candidates.put("Candidate B", 0);
        candidates.put("Candidate C", 0);

        System.out.println("====== ONLINE VOTING SYSTEM ======");

        while (true) {
            System.out.println("\n1. Admin Login");
            System.out.println("2. Voter Login");
            System.out.println("3. Exit");
            System.out.print("Choose Option: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    voterLogin();
                    break;
                case 3:
                    System.out.println("Exiting System...");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


    //  Admin Login
    public static void adminLogin() {
        sc.nextLine();
        System.out.print("Admin Username: ");
        String user = sc.nextLine();

        System.out.print("Admin Password: ");
        String pass = sc.nextLine();

        if (user.equals(adminUser) && pass.equals(adminPass)) {
            System.out.println("Admin Login Successful!");
            adminMenu();
        } else {
            System.out.println("Incorrect Admin Credentials!");
        }
    }


    //  Admin Menu
    public static void adminMenu() {
        while (true) {
            System.out.println("\n------ ADMIN PANEL ------");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. View Results");
            System.out.println("4. Logout");
            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    addCandidate();
                    break;

                case 2:
                    viewCandidates();
                    break;

                case 3:
                    showResults();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


    //  Add Candidate
    public static void addCandidate() {
        sc.nextLine();
        System.out.print("Enter Candidate Name: ");
        String name = sc.nextLine();

        candidates.put(name, 0);
        System.out.println("Candidate Added Successfully!");
    }


    // 📋 View Candidates
    public static void viewCandidates() {
        System.out.println("\n----- Candidate List -----");
        int i = 1;
        for (String c : candidates.keySet()) {
            System.out.println(i + ". " + c);
            i++;
        }
    }


    // 👤 Voter Login
    public static void voterLogin() {
        sc.nextLine();
        System.out.print("Enter Voter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Voter Password: ");
        String pass = sc.nextLine();

        if (voters.containsKey(user) && voters.get(user).equals(pass)) {

            if (votedUsers.contains(user)) {
                System.out.println("⚠ You already voted! Cannot vote again.");
            } else {
                System.out.println("Login Successful!");
                castVote(user);
            }

        } else {
            System.out.println("Incorrect voter credentials!");
        }
    }


    // Cast Vote
    public static void castVote(String username) {

        System.out.println("\n----- Candidates -----");
        int i = 1;
        List<String> list = new ArrayList<>(candidates.keySet());

        for (String c : list) {
            System.out.println(i + ". " + c);
            i++;
        }

        System.out.print("Choose Candidate Number: ");
        int vote = sc.nextInt();

        if (vote >= 1 && vote <= list.size()) {
            String candidate = list.get(vote - 1);
            candidates.put(candidate, candidates.get(candidate) + 1);

            votedUsers.add(username);
            System.out.println("Vote Submitted Successfully!");
        } else {
            System.out.println("Invalid Choice!");
        }
    }


   
    public static void showResults() {
        System.out.println("\n------ Voting Results ------");

        int maxVotes = -1;
        String winner = "";

        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue() + " votes");

            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        System.out.println("\n WINNER: " + winner + " with " + maxVotes + " votes");
    }
}
