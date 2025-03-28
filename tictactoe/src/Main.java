import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> board = createBoard();
        List<String> moves = playerMoves();
        List<String> names = playerNames();
        playGame(board, names, moves);
        if (checkWinner(board) == "None"){
            System.out.println("Draw");
        }
        System.out.println();
        displayBoard(board);
        System.out.println("Game Over");
    }

    public static List<String> createBoard() {
        ArrayList<String> board = new ArrayList<String>();
        int num = 0;
        for (int row = 0; row < 7; row++) {
            String out = "";
            StringBuilder outline = new StringBuilder(out);
            for (int column = 0; column < 19; column++) {
                if (column % 6 == 0 || row % 2 == 0) {
                    outline.append('#');
                } else if (column % 6 != 0 && row % 2 != 0 && column % 3 == 0) {
                    num++;
                    outline.append(Integer.valueOf(num));
                } else {
                    outline.append(' ');
                }
            }
            board.add(outline.toString());
        }
        return board;
    }


    public static void displayBoard(List<String> board) {
        for (String i : board) {
            System.out.println(i);
        }
    }

    public static List<String> playerNames() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Human");
        names.add("Computer");
        return names;
    }

    public static List<String> playerMoves() {
        ArrayList<String> moves = new ArrayList<String>();
        for (int i = 1; i < 10; i++) {
            moves.add(Integer.toString(i));
        }
        return moves;
    }


    public static String computerPlay(List<String> moves) {
        Random random = new Random();
        int length = moves.size();
        int idx = random.nextInt(length);
        String move = moves.get(idx);
        moves.remove(idx);
        return move;
    }


    public static String humanPlay(List<String> moves) {
        while (true) {
            System.out.println("Choose your next move");
            for (String move : moves) {
                System.out.println(move);
            }
            System.out.println("Enter your next move");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().strip();
            if (choice.length() > 0 && moves.contains(choice)) {
                int idx = moves.indexOf(choice);
                moves.remove(idx);
                return choice;
            }

        }
    }

    public static void addMoveToBoard(char pawn, List<String> board, String move) {
        for (String word : board) {
            if (word.contains(move)) {
                char moveChar = move.charAt(0);
                String newWord = word.replace(moveChar, pawn);
                int idx = board.indexOf(word);
                board.set(idx, newWord);
            }
        }
    }


    public static String checkWinner(List<String> board) {

        String pawn = "None";
        // Check rows
        int idxRowMove1 = 3;
        int idxRowMove2 = 9;
        int idxRowMove3 = 15;
        for (int i = 0; i < board.size(); i++) {
            if (i % 2 != 0) {
                if (board.get(i).charAt(idxRowMove1) == board.get(i).charAt(idxRowMove2) && board.get(i).charAt(idxRowMove2) == board.get(i).charAt(idxRowMove3)) {
                    char move = board.get(i).charAt(idxRowMove1);
                    return String.valueOf(move);

                }
            }
        }

        // Check columns
        ArrayList<Integer> rows = new ArrayList<Integer>();
        rows.add(idxRowMove1);
        rows.add(idxRowMove2);
        rows.add(idxRowMove3);

        for (int row : rows){
            if (board.get(1).charAt(row) == board.get(3).charAt(row) && board.get(3).charAt(row) == board.get(5).charAt(row)) {
                char move = board.get(1).charAt(row);
                return String.valueOf(move);
            }
        }


        // Check diagonals
        if (board.get(1).charAt(idxRowMove1) == board.get(3).charAt(idxRowMove2) && board.get(5).charAt(idxRowMove3) == board.get(3).charAt(idxRowMove2)) {
            char move = board.get(1).charAt(idxRowMove1);
            return String.valueOf(move);
        }


        if (board.get(1).charAt(idxRowMove3) == board.get(3).charAt(idxRowMove2) && board.get(3).charAt(idxRowMove2) == board.get(5).charAt(idxRowMove1)) {
            char move = board.get(1).charAt(idxRowMove3);
            return String.valueOf(move);

        }

        return pawn;

    }

    public static void playGame(List <String> board, List<String> names, List<String> moves){
        int turns = 0;
        int turnIdx = 0;

        while (turns <= 9) {
            System.out.println();
            System.out.println("You : X         Computer : O");
            System.out.println();
            displayBoard(board);
            if (turnIdx == 2) {
                turnIdx = 0;
            }
            String currentPlayer = names.get(turnIdx);
            System.out.println();
            humanVsComputer(moves, board, currentPlayer);
            turnIdx++;
            turns --;

            String isWinner = checkWinner(board);
            if (isWinner != "None"){
                System.out.println(isWinner + " WON!!!");
                break;
            }

        }
    }

    public static void humanVsComputer(List<String> moves, List<String> board, String currentPlayer){
        if (currentPlayer.contains("Computer")) {
            try {
                System.out.println("Computer is thinking........");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
            String move = computerPlay(moves);
            addMoveToBoard('O', board, move);
        } else {
            String move = humanPlay(moves);
            addMoveToBoard('X', board, move);
        }

    }
}