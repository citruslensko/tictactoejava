package tictactoe;
import java.util.*;

public class Main {
    // if final, it cannot be changed.
    // if static, you can call it everywhere without creating an instance of a class.
    // if private, it needs to have a getter method because it cannot be accessed outside.
    // if private and static, can be called only by a class with its default value that cannot be changed.
    // if public, usually no limitations and anyone can access it.
    static class Player {
        private String name = "Shawn";
        private String piece;
        private String name2;
        private String piece2;

        public Player(String name, String piece, String name2, String piece2) {
            this.name = name;
            this.piece = piece;
            this.name2 = name2;
            this.piece2 = piece2;
        }

        public String getName() {
            return name;
        }

        public String getPiece() {
            return piece;
        }

        public String getName2() {
            return name2;
        }

        public String getPiece2() {
            return piece2;
        }

        public String winner(String currentPiece) {
            for (int a = 0; a < 8; a++) {
                String lineGroup = null;
                // switch statements
                switch (a) {
                    // all of this cases are the possible format where the tic-tac-toe winner can be formulized
                    // all cases are added into the line, which was a string. Everytime the program
                    // restarts, the line scans for the current value in the field array.
                    case 0: // Horizontal First
                        lineGroup = field[0] + field[1] + field[2];
                        break;
                    case 1: // Horizontal Middle
                        lineGroup = field[3] + field[4] + field[5];
                        break;
                    case 2: // Horizontal Last
                        lineGroup = field[6] + field[7] + field[8];
                        break;
                    case 3: // Vertical First
                        lineGroup = field[0] + field[3] + field[6];
                        break;
                    case 4: // Vertical Middle
                        lineGroup = field[1] + field[4] + field[7];
                        break;
                    case 5: // Vertical Last
                        lineGroup = field[2] + field[5] + field[8];
                        break;
                    case 6: // Diagonal From Left
                        lineGroup = field[0] + field[4] + field[8];
                        break;
                    case 7: // Diagonal From Right
                        lineGroup = field[2] + field[4] + field[6];
                        break;
                }
                // return the piece if it is three in a row.
                if (lineGroup.equals(piece + piece + piece)) {
                    return piece;
                } else if (lineGroup.equals(piece2 + piece2 + piece2)) {
                    return piece2;
                }
            }
            // For the Draw Part
            // Scans how many are numbers still in the field. This code works by exiting the null
            // game and to return a draw if there are no more positions.
            for (int a = 0; a < 9; a++) {
                // This section means that if the field still has its ORIGINAL field number with
                // it, break it and
                // continue the game and do not continue the for loop.
                if (Arrays.asList(field).contains(String.valueOf(a + 1))) 
                {
                    break;
                }
                // returns draw if everything is either filled but still has no winner. A is 8
                // here, because its the highest value in the 3x3.
                // a increments whenever a specific field is not a number
                else if (a == 8) {
                    return "draw";
                }
            }
            // final turn of the piece sign
            System.out.println("Current Turn: " + currentPiece);
            return null;
        }

        public void result(boolean robot) {
            if (robot == true) {
                if (winner(piece) == piece2) {
                    System.out.println(
                            name2 + " won. Robots will now take over the human race in the future.");
                    System.out.println();
                } else {
                    // if the human won the game againts an AI
                    System.out.println(
                            name + " won. However, he won at the cost of his loneliness.");
                    System.out.println();
                }
            } else {
                // This case happens only if the enemy was both humans
                if (winner(piece) == piece2) {
                    System.out.println(
                            name2 + " won. Shame on the house of " + name + ".");
                    System.out.println();
                } else {
                    // if the human won the game againts an AI
                    System.out.println(
                            name + " won. Shame on the house of " + name2 + ".");
                    System.out.println();
                }
            }
        }

    }
    static String[] field;
    static String piece;
    static void printPlayingField() {
        int i = 1;
        // do-while for the sake of having a do while in the code.
        do {
            System.out.println();
            System.out.println(" " + field[0] + "  "
                    + field[1] + "  " + field[2]
                    + " ");
            System.out.println(" " + field[3] + "  "
                    + field[4] + "  " + field[5]
                    + " ");
            System.out.println(" " + field[6] + "  "
                    + field[7] + "  " + field[8]
                    + " ");
            System.out.println();
            i++;
        } while (i <= 1);
    }
    static void startGame(boolean game){
        Scanner sc = new Scanner(System.in);
        while (game) {
            String name = "";
            String name2 = "";
            String pieceDecoy = "";
            String pieceDecoy2 = "";
            field = new String[9];
            for (int a = 0; a < 9; a++) {
                // asigning a specific string into an index of the field array
                field[a] = String.valueOf(a + 1);
            }
            // assignment for the robot enemy
            System.out.println();
            String winner = null;
            boolean robot = true;
            Random random = new Random();
            System.out.println("Tic Tac Toe is a game to play when you're bored.");
            System.out.print("Are you playing with a friend? (Y/N): ");
            char answer = sc.next().charAt(0);
            sc.nextLine();
            printPlayingField();
            if (answer == 'Y' || answer == 'y') {
                System.out.println(answer);
                robot = false;
                System.out.print("What is your name: ");
                name = sc.nextLine();
                System.out.print("What is your playing piece: ");
                pieceDecoy = sc.nextLine();
                System.out.print("What is your friend's name: ");
                name2 = sc.nextLine();
                ;
                System.out.print("What is your friend's piece: ");
                pieceDecoy2 = sc.nextLine();
            } else if (answer == 'N' || answer == 'n') {
                System.out.print("What is your name: ");
                name = sc.nextLine();
                System.out.print("What is your playing piece: ");
                pieceDecoy = sc.next();
                name2 = "Robot";
                pieceDecoy2 = "R";
            }
            Player player = new Player(name, pieceDecoy, name2, pieceDecoy2);
            String piece = player.getPiece();
            System.out.println("Current Turn: " + piece);
            // while loop
            while (winner == null) {
                int playerInput;
                try {
                    if (robot == true && piece == player.getPiece2()) {
                        playerInput = random.nextInt(9);
                    } else {
                        playerInput = sc.nextInt();
                    }
                    // If player's input was not greater than 0 or less than or equal to 9
                    if (!(playerInput > 0 && playerInput <= 9)) {
                        if (robot == false) {
                            System.out.println("Number is not allowed. Re-enter it.");
                        } else if (robot == false && piece == player.getPiece2()) {
                            System.out.println("Number is not allowed. Re-enter it.");
                        }
                        // Jumping statements
                        continue;
                    }
                } catch (InputMismatchException e) {
                    // important to avoid loop.
                    sc.next();
                    if (robot == false) {
                        System.out.println("Value is not allowed. Only numbers(1-9) is allowed.");
                        playerInput = random.nextInt(9);
                    } else if (robot == false && piece == player.getPiece()) {
                        System.out.println("Value is not allowed. Only numbers(1-9) is allowed.");
                    }
                    // If no problem, continue to next part
                    continue;
                }
                // If the position is still a number, then, take that position and enter the
                // current piece
                if (field[playerInput - 1].equals(
                        String.valueOf(playerInput))) {
                    field[playerInput - 1] = piece;
                    // assuming that the position is filled with the current piece,
                    // if the current piece is an X, make the piece an O
                    if (piece == player.getPiece()) {
                        piece = player.getPiece2();
                    }
                    // else if, the piece was an O, make it an X
                    else {
                        piece = player.getPiece();
                    }
                    // print the playing field again
                    printPlayingField();
                    // check if there is a winner
                    winner = player.winner(piece);
                }
                else {
                    if (robot == false) {
                        System.out.println("Position Filled. Enter another position:");
                    } else if (robot == true && piece == player.getPiece()) {
                        System.out.println("Position Filled. Enter another position:");
                    }
                }
            }
            if (player.winner(piece) == "draw") {
                System.out.println("Draw.");
                game = false;
                System.out.println();
            } else {
                // print for the winner
                player.result(robot);
                game = false;
                System.out.println();
            }
        }
        if(!game){
            System.out.print("Are you ready to play Again? (Y/N): ");
            char isPlayAgain = sc.next().toUpperCase().charAt(0);
            if(isPlayAgain == 'Y'){
                game = true;
                startGame(game);
            }else{
                game = false;
            }
        }
        sc.close();
    }


    // start of the code
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you ready to play? (Y/N): ");
        char isPlay = sc.next().toUpperCase().charAt(0);
        boolean game = true;
        if(isPlay == 'Y'){
            startGame(game);
        }else{
            game = false;
        }
        sc.close();
    }
}
