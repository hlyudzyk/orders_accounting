package app.client;

import app.client.requestcontrollers.UserRequestController;
import java.time.LocalDate;
import java.util.Scanner;
import services.json.JsonSerializer;

public class ConsoleUI {

    private final UserRequestController userController;
    private final Scanner scanner;

    public ConsoleUI(UserRequestController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    registerOrLogin();
                    break;
                case 2:
                    if (userController.isAuthorized()) {
                        displayUserMenu();
                        handleUserMenuChoice(getUserChoice());
                    } else {
                        System.out.println("Authorization required. Please log in or register.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("===== Main Menu =====");
        System.out.println("1. Register or Login");
        System.out.println("2. User Menu");
        System.out.println("3. Exit");
    }

    private void displayUserMenu() {
        System.out.println("===== User Menu =====");
        System.out.println("1. Request New Order");
        System.out.println("2. Order History");
        System.out.println("3. Logout");
    }

    private void registerOrLogin() {
        System.out.println("===== Register or Login =====");


        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = getUserChoice();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        switch (choice) {
            case 1:
                userController.registerAuthorizable(username, password, LocalDate.now());
                break;
            case 2:
                userController.loginAuthorizable(username, password);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void handleUserMenuChoice(int choice) {
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter location from: ");
                String locationFrom = scanner.nextLine();
                System.out.print("Enter location to: ");
                String locationTo = scanner.nextLine();
                userController.requestNewOrder(locationFrom, locationTo);
                break;
            case 2:
                userController.requestHistory();
                break;
            case 3:
                System.out.println("Logging out...");
                userController.setAuthorized(false);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        JsonSerializer serializer = new JsonSerializer(); // Assuming you have a JsonSerializer instance
        UserRequestController userController = new UserRequestController(serializer);
        ConsoleUI consoleUI = new ConsoleUI(userController);
        consoleUI.start();
    }
}
