package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DealOrNoDealGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ask the user if they want to enable debug mode
        System.out.println("$$$$$$$$ Welcome to DEAL OR NO DEAL $$$$$$$$");
        System.out.print("Please press D/d for a debugging session or press any other key to start the game: ");
        char debugChoice = scanner.next().charAt(0);

        boolean debugMode = (debugChoice == 'D' || debugChoice == 'd');

        // Initialize the list of suitcases
        List<Suitcase> suitcases = initializeSuitcases(debugMode, random);
        int playerChosenSuitcase = getPlayerChosenSuitcase(scanner, debugMode);

        // Create the game board and user interface objects
        GameBoard gameBoard = new GameBoard(suitcases, playerChosenSuitcase);
        UserInterface ui = new UserInterface(scanner);

        while (!gameBoard.isGameOver()) {
            // Display the available suitcases to the player
            ui.displayAvailableSuitcases(gameBoard.getAvailableSuitcases());

            int chosenCase = ui.askUserForCase();

            if (!gameBoard.isValidChoice(chosenCase)) {
                System.out.println("Invalid choice. Please choose an available suitcase.");
                continue;
            }

            // Open the chosen suitcase and display its content
            gameBoard.openSuitcase(chosenCase);
            ui.displayEliminatedSuitcase(chosenCase, suitcases.get(chosenCase - 1).getValue());

            if (gameBoard.getCurrentRound() % 2 == 0) {
                // Calculate and display the bank's offer to the player
                double bankOffer = gameBoard.calculateBankOffer();
                ui.displayBankOffer(bankOffer);

                char dealChoice = ui.askDealOrNoDeal();
                if (dealChoice == 'Y' || dealChoice == 'y') {
             // Player accepted the bank's offer, display the end game result and exit the loop
                    ui.displayEndGameResult(bankOffer);
                    break;
                }
            }
        }

        if (ui.askSwitchCases()) {
       // Player chose to switch cases, perform the switch
            gameBoard.switchPlayerCases();
        }

        // Get the player's final result and display it
        int playerFinalResult = gameBoard.getPlayerFinalResult();
        System.out.println("Opening suitcase number " + gameBoard.getPlayerChosenSuitcase() + ".");
        System.out.println("Congratulations, you won $" + playerFinalResult + ".");

        // Display the game over message
        System.out.println("GAME OVER");
        System.out.println("$$$$$$$$ Thank you for playing DEAL OR NO DEAL $$$$$$$$");
        System.out.println("Please try again at another time, bye bye!");
    }

    private static List<Suitcase> initializeSuitcases(boolean debugMode, Random random) {
        List<Suitcase> suitcases = new ArrayList<>();
        List<Integer> values;

        if (debugMode) {
            // In debug mode, initialize suitcases with predetermined values
            values = new ArrayList<>();
            for (int i = 1; i <= 26; i++) {
                values.add(i * 100);
            }
        } else {
            // In non-debug mode, generate random values
            values = generateRandomValues(random);
        }

        for (int i = 0; i < 26; i++) {
            if (values.isEmpty()) {
                // Shuffle the values and start over if they run out.
                values = generateRandomValues(random);
            }

            int value = values.remove(random.nextInt(values.size()));
            suitcases.add(new Suitcase(i + 1, value));
        }

        return suitcases;
    }

    // Generates a list of random values for suitcases
    private static List<Integer> generateRandomValues(Random random) {
        List<Integer> values = new ArrayList<>();

        // Add various dollar values to the list
        values.add(1);
        values.add(5);
        values.add(10);
        values.add(25);
        values.add(50);
        values.add(75);
        values.add(100);
        values.add(200);
        values.add(300);
        values.add(400);
        values.add(500);
        values.add(750);
        values.add(1000);
        values.add(5000);
        values.add(10000);
        values.add(25000);
        values.add(50000);
        values.add(75000);
        values.add(100000);
        values.add(200000);
        values.add(300000);
        values.add(400000);
        values.add(500000);
        values.add(750000);
        values.add(1000000);

        return values;
    }

    private static int getPlayerChosenSuitcase(Scanner scanner, boolean debugMode) {
        int chosenSuitcase;
        while (true) {
            if (debugMode) {
                System.out.print("Please choose your suitcase 1-26 with your price (Debug Mode): ");
            } else {
                System.out.print("Please choose your suitcase 1-26 with your price: ");
            }

            if (scanner.hasNextInt()) {
                chosenSuitcase = scanner.nextInt();
                if (chosenSuitcase >= 1 && chosenSuitcase <= 26) {
                    break;
                } else {
                    System.out.println("This number is out of range, please try again.");
                }
            } else {
                System.out.println("Please type in a number!");
                scanner.next();
            }
        }
        System.out.println("You chose number " + chosenSuitcase + ".");
        return chosenSuitcase;
    }
}

class Suitcase {
    private final int number;
    private final int value;

    public Suitcase(int number, int value) {
        this.number = number;
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}

class GameBoard {
    private final List<Suitcase> suitcases;
    private final int playerChosenSuitcase;
    private final boolean[] eliminatedSuitcases;
    private int currentRound;

    public GameBoard(List<Suitcase> suitcases, int playerChosenSuitcase) {
        this.suitcases = suitcases;
        this.playerChosenSuitcase = playerChosenSuitcase;
        this.eliminatedSuitcases = new boolean[26];
        this.currentRound = 1;
    }

    public List<Suitcase> getAvailableSuitcases() {
        List<Suitcase> availableSuitcases = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (!eliminatedSuitcases[i]) {
                availableSuitcases.add(suitcases.get(i));
            }
        }
        return availableSuitcases;
    }

    public int getPlayerChosenSuitcase() {
        return playerChosenSuitcase;
    }

    public boolean isGameOver() {
        return currentRound > 9 || (currentRound == 9 && eliminatedSuitcases[24]);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public boolean isValidChoice(int chosenCase) {
        return chosenCase >= 1 && chosenCase <= 26 && !eliminatedSuitcases[chosenCase - 1];
    }


    public void openSuitcase(int chosenCase) {
        eliminatedSuitcases[chosenCase - 1] = true; // Subtract 1 to match the array index with the suitcase number
        currentRound++;
    }



    public double calculateBankOffer() {
        double remainingValue = 0;
        for (int i = 0; i < 26; i++) {
            if (!eliminatedSuitcases[i]) {
                remainingValue += suitcases.get(i).getValue();
            }
        }
        double expectedValue = (remainingValue / getAvailableSuitcases().size()) * currentRound / 10;
        return Math.round(expectedValue * 100.0) / 100.0;
    }

    public void switchPlayerCases() {
        int temp = eliminatedSuitcases.length - playerChosenSuitcase;
        eliminatedSuitcases[playerChosenSuitcase - 1] = false;
        eliminatedSuitcases[temp] = true;
    }

    public int getPlayerFinalResult() {
        if (currentRound == 10) {
            return suitcases.get(playerChosenSuitcase - 1).getValue();
        }
        return 0;
    }
}

class UserInterface {
    private final Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public int askUserForCase() {
        int chosenCase;
        while (true) {
            System.out.print("Pick a suitcase to eliminate from the game: ");
            if (scanner.hasNextInt()) {
                chosenCase = scanner.nextInt();
                if (chosenCase >= 1 && chosenCase <= 26) {
                    break;
                } else {
                    System.out.println("This number is out of range, please try again.");
                }
            } else {
                System.out.println("Please type in a number!");
                scanner.next();
            }
        }
        return chosenCase;
    }

    public void displayAvailableSuitcases(List<Suitcase> availableSuitcases) {
        System.out.print("[");
        for (Suitcase suitcase : availableSuitcases) {
            System.out.print(suitcase.getNumber() + " ");
        }
        System.out.println("]");
    }

    public void displayEliminatedSuitcase(int suitcaseNumber, int value) {
        System.out.println("Case " + suitcaseNumber + " was eliminated.");
        System.out.println("It contains $" + value + ".");
        System.out.println();
    }

    public void displayBankOffer(double offer) {
        System.out.println("The bank offers you $" + offer + ".");
    }

    public char askDealOrNoDeal() {
        char choice;
        while (true) {
            System.out.print("Do you accept the offer? Please press Y/y for YES and N/n for NO: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y' || choice == 'N' || choice == 'n') {
                break;
            } else {
                System.out.println("Please type in a valid character.");
            }
        }
        if (choice == 'Y' || choice == 'y') {
            System.out.println("You decided to end the game.");
        }
        return choice;
    }

    public boolean askSwitchCases() {
        char choice;
        while (true) {
            System.out.print("The suitcase with your price was number " +
                    "1.\nSuitcase number 26 is the last one remaining.\n" +
                    "Now we give you a chance to switch to this suitcase.\n" +
                    "Do you want to switch suitcases?\n" +
                    "Please press Y/y for YES and N/n for NO: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y' || choice == 'N' || choice == 'n') {
                break;
            }
        }
        return (choice == 'Y' || choice == 'y');
    }

    public void displayEndGameResult(double offer) {
        System.out.println("You decided to end the game.");
        System.out.println("Congratulations, you won $" + offer + ".");
    }
}
