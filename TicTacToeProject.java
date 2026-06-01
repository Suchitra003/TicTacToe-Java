import java.util.Scanner;

public class TicTacToeProject {

    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    static int scoreX = 0;
    static int scoreO = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Player 1 Name (X): ");
        String player1 = sc.nextLine();

        System.out.print("Enter Player 2 Name (O): ");
        String player2 = sc.nextLine();

        boolean playAgain = true;

        while (playAgain) {

            initializeBoard();
            currentPlayer = 'X';

            boolean gameOver = false;

            while (!gameOver) {

                printBoard();

                String playerName =
                        (currentPlayer == 'X') ? player1 : player2;

                System.out.println(
                        playerName + " (" + currentPlayer + ") Turn");

                System.out.print("Enter Row (0-2): ");
                int row = sc.nextInt();

                System.out.print("Enter Column (0-2): ");
                int col = sc.nextInt();

                if (row < 0 || row > 2 ||
                    col < 0 || col > 2 ||
                    board[row][col] != ' ') {

                    System.out.println("Invalid Move!");
                    continue;
                }

                board[row][col] = currentPlayer;

                if (checkWinner()) {

                    printBoard();

                    System.out.println(
                            playerName + " Wins!");

                    if (currentPlayer == 'X')
                        scoreX++;
                    else
                        scoreO++;

                    gameOver = true;

                } else if (isBoardFull()) {

                    printBoard();
                    System.out.println("Match Draw!");

                    gameOver = true;

                } else {

                    currentPlayer =
                            (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.println("\n===== SCOREBOARD =====");
            System.out.println(player1 + " : " + scoreX);
            System.out.println(player2 + " : " + scoreO);

            System.out.print(
                    "\nPlay Again? (yes/no): ");

            sc.nextLine();
            String choice = sc.nextLine();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nThanks for Playing!");
        sc.close();
    }

    static void initializeBoard() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                board[i][j] = ' ';
            }
        }
    }

    static void printBoard() {

        System.out.println("\n  0   1   2");

        for (int i = 0; i < 3; i++) {

            System.out.print(i + " ");

            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j]);

                if (j < 2)
                    System.out.print(" | ");
            }

            System.out.println();

            if (i < 2)
                System.out.println(" ---+---+---");
        }

        System.out.println();
    }

    static boolean checkWinner() {

        for (int i = 0; i < 3; i++) {

            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2])
                return true;

            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i])
                return true;
        }

        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2])
            return true;

        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0])
            return true;

        return false;
    }

    static boolean isBoardFull() {

        for (char[] row : board) {

            for (char cell : row) {

                if (cell == ' ')
                    return false;
            }
        }

        return true;
    }
}