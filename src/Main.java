import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class Main {
    private static ArrayList<Admin> users = new ArrayList<>();

    public static void main(String[] args) {
        users.add(new Admin("user1", "password1"));
        users.add(new Admin("user2", "password2"));
        String[] codepemain = {"202310370311060"};

        Scanner scanner = new Scanner(System.in);
        boolean isLogin = false;

        while (!isLogin) {
            printLoginMenu();

            int choice = getUserChoice(scanner);
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    isLogin = true;
                    break;
                case 2:
                    studentLogin(scanner, codepemain);
                    isLogin = true;
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem login Library.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        // Close the scanner outside the loop
        scanner.close();
    }

    private static void printLoginMenu() {
        System.out.println("\nSilakan login sebagai Admin atau Pemain:");
        System.out.println("1. Admin");
        System.out.println("2. Pemain");
        System.out.println("3. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return -1; // Return an invalid choice
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (isValidAdmin(username, password)) {
            System.out.println("Successful Login as Admin!");
        } else {
            System.out.println("User not found. Please try again.");
        }
    }

    private static void studentLogin(Scanner scanner, String[] codepemain) {
        System.out.print("Enter your Code: ");
        String code = scanner.nextLine();
        if (isValidNIM(code, 15) && isValidPemain(code, codepemain)) {
            System.out.println("Successful Login as PLayer!");
        } else {
            System.out.println("User not found. Please try again.");
        }
    }

    private static boolean isValidAdmin(String username, String password) {
        for (Admin user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidNIM(String code, int length) {
        return code.length() == length;
    }

    private static boolean isValidPemain(String nim, String[] codepemain) {
        for (String validNIM : codepemain) {
            if (validNIM.equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
