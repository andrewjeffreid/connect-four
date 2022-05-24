import java.util.Scanner;

public class ConnectFour {
    public static void main(String[] args) {

        /** Setup ConnectFour Board */
        GameBoard game = new GameBoard();
        game.initializeBoard();

        /** Select Game Mode */
        Scanner input = new Scanner(System.in);
        int mode;
        System.out.print("Select your mode: Enter 1 for 2 players, 2 for playing against the CPU or 0 to quit.");
        mode = input.nextInt();

        /** Play */
        if (mode == 1) {
            twoPlayer(game, input);

        } else if (mode == 2) {
            CPU(game, input);
        }

        input.close();
    }

    /** Multiplayer Mode */
    public static void twoPlayer(GameBoard game, Scanner input) {

        int numMoves = 0;
        int player1FalseMoves = 0;
        int player2FalseMoves = 0;
        int player1ConsecutiveFalseMoves = 0;
        int player2ConsecutiveFalseMoves = 0;

        while (true) {

            game.printBoard();

            // board is filled
            if (numMoves == 42) {
                System.out.println("Game Over! It is tie");
                break;

            } else if (numMoves % 2 == 0) {
                player2ConsecutiveFalseMoves = 0;
                System.out.println("player 1 turn: ");
                int x = input.nextInt();
                if (validate(x)) {

                    // Make move
                    int y = game.move(x, 'x');

                    // User enters occupied tile
                    if (y < 0) {
                        System.out.println("incorrect entry, please try again");
                        player1FalseMoves += 1;
                        player1ConsecutiveFalseMoves += 1;
                        if (checkFalseMoves(player1ConsecutiveFalseMoves, player1FalseMoves)) {
                            System.out.println("player 1 forfeits the game due to reaching maximum incorrect entries");
                            break;
                        }
                        continue;
                    }

                    // check for a winner
                    if (game.validateVertical(x, y, 'x') || game.validateHorizontal(x, y, 'x') || game.validateDiagonalLeft(x, y, 'x') || game.validateDiagonalRight(x, y, 'x')) {
                        System.out.println("Game Over! Player 1 Wins");
                        break;
                    }
                    numMoves += 1;

                // allow user to exit
                } else if (x == 9) {
                    System.out.println("Game Over! Player 1 forfeits to Player 2");
                    break;

                // user enters integer outside of 0 and 6
                } else {
                    System.out.println("incorrect entry, please try again");
                    player1FalseMoves += 1;
                    player1ConsecutiveFalseMoves += 1;
                    if (checkFalseMoves(player1ConsecutiveFalseMoves, player1FalseMoves)) {
                        System.out.println("player 1 forfeits the game due to reaching maximum incorrect entries");
                        break;
                    }
                    continue;
                }
            } else if (numMoves % 2 != 0) {
                player1ConsecutiveFalseMoves = 0;
                System.out.println("player 2 turn: ");
                int x = input.nextInt();
                if (validate(x)) {

                    // Make move
                    int y = game.move(x, 'o');

                    // User enters occupied tile
                    if (y < 0) {
                        System.out.println("incorrect entry, please try again");
                        player2FalseMoves += 1;
                        player2ConsecutiveFalseMoves += 1;
                        if (checkFalseMoves(player2ConsecutiveFalseMoves, player2FalseMoves)) {
                            System.out.println("player 2 forfeits the game due to reaching maximum incorrect entries");
                            break;
                        }
                        continue;
                    }

                    // check for a winner
                    if (game.validateVertical(x, y, 'o') || game.validateHorizontal(x, y, 'o') || game.validateDiagonalLeft(x, y, 'o') || game.validateDiagonalRight(x, y, 'o')) {
                        System.out.println("Game Over! Player 2 Wins");
                        break;
                    }
                    numMoves += 1;

                // allow user to exit
                } else if (x == 9) {
                    System.out.println("Game Over! Player 2 forfeits to Player 1");
                    break;

                // user enters integer outside of 0 and 6
                } else {
                    System.out.println("incorrect entry, please try again");
                    player2FalseMoves += 1;
                    player2ConsecutiveFalseMoves += 1;
                    if (checkFalseMoves(player2ConsecutiveFalseMoves, player2FalseMoves)) {
                        System.out.println("player 2 forfeits the game due to reaching maximum incorrect entries");
                        break;
                    }
                    continue;
                }
            }
        }
    }

    /** CPU Mode */
    public static void CPU(GameBoard game, Scanner input) {

        int numMoves = 0;
        int player1FalseMoves = 0;
        int player1ConsecutiveFalseMoves = 0;
        int previousMove = 0;

        while (true) {

            game.printBoard();

            if (numMoves == 42) {
                System.out.println();
                break;
            } else if (numMoves % 2 == 0) {
                System.out.println("player 1 turn: ");
                int x = input.nextInt();
                if (validate(x)) {

                    // Make move
                    int y = game.move(x, 'x');
                    previousMove = x;

                    // User enters occupied tile
                    if (y < 0) {
                        System.out.println("incorrect entry, please try again");
                        player1FalseMoves += 1;
                        player1ConsecutiveFalseMoves += 1;
                        if (checkFalseMoves(player1ConsecutiveFalseMoves, player1FalseMoves)) {
                            System.out.println("player 1 forfeits the game due to reaching maximum incorrect entries");
                            break;
                        }
                        continue;
                    }

                    // check for a winner
                    if (game.validateVertical(x, y, 'x') || game.validateHorizontal(x, y, 'x') || game.validateDiagonalLeft(x, y, 'x') || game.validateDiagonalRight(x, y, 'x')) {
                        System.out.println("Game Over! Player 1 Wins");
                        break;
                    }
                    numMoves += 1;

                // allow user to exit
                } else if (x == 9) {
                    System.out.println("Game Over! Player 1 forfeits to CPU");
                    break;

                // user enters integer outside of 0 and 6
                } else {
                    System.out.println("incorrect entry, please try again");
                    player1FalseMoves += 1;
                    player1ConsecutiveFalseMoves += 1;
                    if (checkFalseMoves(player1ConsecutiveFalseMoves, player1FalseMoves)) {
                        System.out.println("player 1 forfeits the game due to reaching maximum incorrect entries");
                        break;
                    }
                    continue;
                }
                
            /** CPU Move */
            } else if (numMoves % 2 != 0) {
                player1ConsecutiveFalseMoves = 0;
                System.out.println("trying to win the game");

                int check4CPU = game.check4(game.possibleMoves(), 'c');
                int check4Player = game.check4(game.possibleMoves(), 'x');
                int x;
                int y;

                // check if CPU can win
                if (0 <= check4CPU && check4CPU <= 6) {
                    x = check4CPU;
                    y = game.move(check4CPU, 'c');

                // check if player can win
                } else if (0 <= check4Player && check4Player <= 6) {
                    x = check4Player;
                    y = game.move(check4Player, 'c');

                // default block
                } else {
                    x = previousMove + (-1 + (int)(Math.random() * 3));

                    // account for edge cases
                    if (x < 0) {
                        x += 1;
                    } else if (x > 6) {
                        x -= 1;
                    }

                    // make default blocking move
                    y = game.move(x, 'c');

                    // make random move
                    if (y < 0) {
                        y = game.move((int)(Math.random() * 6), 'c');
                        if (y < 0) {
                            continue;
                        }
                        continue;
                    }
                }
                numMoves += 1;

                if (game.validateVertical(x, y, 'c') || game.validateHorizontal(x, y, 'c') || game.validateDiagonalLeft(x, y, 'c') || game.validateDiagonalRight(x, y, 'c')) {
                    game.printBoard();
                    System.out.println("Game Over! CPU Wins");
                    break;
                }
            }
        }
    }

    /** Validate input */
    public static boolean validate(int x) {
        boolean bool = false;
        if (x >= 0 && x <= 6) {
            bool = true;
        }
        return bool;
    }

    public static boolean checkFalseMoves(int consecutiveFalseMoves, int FalseMoves) {
        boolean bool = false;
            if (consecutiveFalseMoves >= 3 || FalseMoves >= 5) {
                bool = true;
            }
        return bool;
    }
}

// two player game
// CPU 
