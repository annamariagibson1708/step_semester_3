import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {


    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};
        int totalRounds = 5;


        String[] playerHistory = new String[totalRounds];
        String[] computerHistory = new String[totalRounds];
        String[] resultsHistory = new String[totalRounds];

        int wins = 0, losses = 0, draws = 0;

        System.out.println("--- Welcome to the College Coding Arcade ---");

        for (int i = 0; i < totalRounds; i++) {
            System.out.print("\nRound " + (i + 1) + " - Enter your move (Rock, Paper, Scissors): ");
            String playerMove = scanner.nextLine().trim();

            while (!playerMove.equalsIgnoreCase("Rock") &&
                    !playerMove.equalsIgnoreCase("Paper") &&
                    !playerMove.equalsIgnoreCase("Scissors")) {
                System.out.print("Invalid move! Please enter Rock, Paper, or Scissors: ");
                playerMove = scanner.nextLine().trim();
            }

            String computerMove = moves[random.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            playerHistory[i] = playerMove;
            computerHistory[i] = computerMove;
            resultsHistory[i] = result;

            System.out.println("Computer chose: " + computerMove + " -> " + result);
        }

        System.out.println("FINAL SUMMARY TABLE (After " + totalRounds + " rounds)\n");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("Round %d — Player: %-8s Computer: %-8s | %s\n",
                    (i + 1), playerHistory[i], computerHistory[i], resultsHistory[i]);
        }

        double winPercentage = ((double) wins / totalRounds) * 100;
        System.out.printf("Final Statistics: Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n",
                wins, losses, draws, winPercentage);

        scanner.close();
    }
}
