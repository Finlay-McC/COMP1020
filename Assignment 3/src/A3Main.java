import java.util.Scanner;

/**
 * A3Main
 * Players compete to connect N of their own signals horizontally, vertically, or diagonally on a
 * rectangular wall
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-11-20
 */
public class A3Main {
    /**
     * Main method for the program
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter players,rows,columns,N:");
        boolean setupComplete = false;
        int[] setup = new int[4];
        String input;
        String result;
        while (!setupComplete) {
            input = sc.nextLine();
            result = parseSetup(input, setup);
            if (result != null) {
                System.out.println("Invalid setup.");
                System.out.println(result);
            } else {
                System.out.println("Setup complete.");
                setupComplete = true;
            }
        }
        int numPlayers = setup[0];
        int[][] wall = new int[setup[1]][setup[2]];
        int n = setup[3];
        printWall(wall);
        boolean won = false;
        int row;
        while (!won) {
            for (int player = 1; player <= numPlayers; player++) {
                boolean valid = false;
                while (!valid) {
                    System.out.println("Player " + player + " row:");
                    if (!sc.hasNextLine()) {
                        break;
                    }
                    input = sc.nextLine();
                    try {
                        row = Integer.parseInt(input);
                    } catch (NumberFormatException noe) {
                        System.out.println("Invalid row number.");
                        continue;
                    }
                    if (row > wall.length || row <= 0) {
                        System.out.println("Invalid row number.");
                        continue;
                    }
                    valid = placeSignal(wall, player, row);
                }
                printWall(wall);
                for (int i = 1; i <= numPlayers; i++) {
                    if (checkWin(wall, i, n)) {
                        System.out.println("Player " + i + " wins!");
                        won = true;
                    }
                }
                if (won) {
                    break;
                }
            }
        }
        if (won) {
            int[] ranks = rankPlayers(wall, numPlayers);
            System.out.println("Leaderboard:");
            for (int i = 0; i < ranks.length; i++) {
                System.out.println("Player " + (i + 1) + ": " + ranks[i]);
            }

            System.out.println("Game over.");
        } else {
            System.out.println("Input ended.");
        }
    }

    /**
     * Sets up the game
     * @param input The input from the user
     * @param result The result of the setup
     * @return Any errors that occurred during setup, or null if setup was successful
     */
    public static String parseSetup(String input, int[] result) {
        String[] parts = input.split(",",-1);
        int[] values = new int[4];
        if (parts.length != 4) {
            return "Invalid input format, expected: 4, actual: " + parts.length;
        }
        try {
            for (int i = 0; i < parts.length; i++) {
                values[i] = Integer.parseInt(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            return "Invalid number format, please enter only numbers.";
        }

        if (values[0] <= 1 || values[0] > 9) {
            return "Invalid input, there can be 2 to 9 players.";
        }
        if (values[1] < 3 || values[1] > 12) {
            return "Invalid input, there can be 3 to 12 rows.";
        }
        if (values[2] < 3 || values[2] > 25) {
            return "Invalid input, there can be 3 to 25 columns.";
        }
        if (values[3] > values[2] || values[3] > values[1] || values[3] < 3) {
            return "Invalid input, the N value must be less than or equal to the max size of the board.";
        }
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i];
        }
        return null;
    }

    /**
     * Prints the wall to the console
     * @param wall The wall to print
     */
    public static void printWall(int[][] wall) {
        int currentRow = 1;
        System.out.print("   ");
        for (int i = 0; i < wall[0].length; i++) {
            System.out.printf("%3d", i + 1);
        }
        System.out.println();
        for (int[] row : wall) {
            System.out.printf("%3d",currentRow++);
            for (int value : row) {
                if (value == 0) {
                    System.out.print("  .");
                } else {
                    System.out.printf("%3d", value);
                }
            }
            System.out.println();
        }
    }

    /**
     * Places a signal on the wall
      * @param wall The wall to place the signal on
     * @param player The player placing the signal
     * @param row The row to place the signal on
     * @return True if the signal was placed, false otherwise
     */
    public static boolean placeSignal(int[][] wall, int player, int row) {
        int[] rowValues = wall[row - 1];
        boolean placed = false;
        for (int i = 0; i < rowValues.length; i++) {
            if (rowValues[i] == 0) {
                rowValues[i] = player;
                placed = true;
                break;
            }
        }
        if (!placed) {
            for (int i = 0; i < rowValues.length; i++) {
                if (i == rowValues.length - 1) {
                    rowValues[i] = player;
                    placed = true;
                    break;
                } else {
                    rowValues[i] = rowValues[i + 1];
                }
            }
        }
        return placed;
    }

    /**
     * Checks if a player has won the game
     * @param wall The wall to check
     * @param player The player to check
     * @param n The N value to check
     * @return
     */
    public static boolean checkWin(int[][] wall, int player, int n) {
        if (player < 1 || n < 3) {
            return false;
        }
        boolean won = false;
        int currentRun;
        int rows = wall.length;
        int columns = wall[0].length;
        for (int sum = 0; sum <= rows + columns - 2; sum++) {
            currentRun = 0;
            for (int r = 0; r < rows; r++) {
                int c = sum - r;
                if (c >= 0 && c < columns) {
                    if (wall[r][c] == player) {
                        currentRun++;
                    } else {
                        currentRun = 0;
                    }
                    if (currentRun == n) {
                        won = true;
                        break;
                    }
                }
            }
        }
        if (!won) {
            for (int diff = -(columns - 1); diff <= rows - 1; diff++) {
                currentRun = 0;
                for (int r = 0; r < rows; r++) {
                    int c = r - diff;
                    if (c >= 0 && c < columns) {
                        if (wall[r][c] == player) {
                            currentRun++;
                        } else {
                            currentRun = 0;
                        }
                        if (currentRun == n) {
                            won = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!won) {
            for (int[] row : wall) {
                currentRun = 0;
                for (int value : row) {
                    if (value == player) {
                        currentRun++;
                    } else {
                        currentRun = 0;
                    }
                    if (currentRun == n) {
                        won = true;
                        break;
                    }
                }
            }
        }
        if (!won) {
            for (int i = 0; i < columns; i++) {
                currentRun = 0;
                for (int j = 0; j < rows; j++) {
                    if (wall[j][i] == player) {
                        currentRun++;
                    } else {
                        currentRun = 0;
                    }
                    if (currentRun == n) {
                        won = true;
                    }
                }
            }
        }
        return won;
    }

    /**
     * Ranks players based on the number of signals they have placed
     * @param wall The wall to rank players on
     * @param numPlayers The number of players in the game
     * @return An array of player ranks, where the index + 1 corresponds to the rank
     */
    public static int[] rankPlayers(int[][] wall, int numPlayers) {
        int[] counts = new int[numPlayers];
        int[] sorted;
        int[] ranks = new int[numPlayers];
        for (int[] row : wall) {
            for (int value : row) {
                if (value != 0) {
                    if (value <= numPlayers) {
                        counts[value - 1]++;
                    }
                }
            }
        }
        sorted = counts.clone();
        for (int i = 0; i < sorted.length; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[i] < sorted[j]) {
                    int temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        for (int i = 0; i < sorted.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] == sorted[i]) {
                    ranks[i] = j + 1;
                    break;
                }
            }
        }
        return ranks;
    }
}