import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 class SimpleLoginAuthentication {

    private static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    boolean isLoggedIn = loginUser(scanner);
                    if (isLoggedIn) {
                        accessSecuredPage();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();

        // Hash password before storing
        String hashedPassword = hashPassword(password);
        users.put(username, hashedPassword);
        System.out.println("Registration successful!");
    }

    private static boolean loginUser(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();

        if (users.containsKey(username)) {
            String hashedPassword = users.get(username);
            String enteredPassword = hashPassword(password);

            if (hashedPassword.equals(enteredPassword)) {
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Login failed. Incorrect username or password.");
        return false;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void accessSecuredPage() {
        System.out.println("Welcome to the secured page!");
        // Add your secured page content here
    }
}
