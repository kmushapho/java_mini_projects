import java.util.List;
import  java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String [] words = {"elephant", "adventure", "chocolate", "sunshine", "mountain", "freedom", "computer", "calendar"};
//        create pick the random word for the game
        Random random = new Random();
        int length = words.length;
        int random_idx = random.nextInt(length);
        String game_word = words[random_idx];
        int word_length = game_word.length();

//        create display for game
        List<Character> display = new ArrayList<>();
        for (int idx = 0; idx < word_length; idx++){
            display.add('_');
        }

        int attempts = 6;

        String nw_word = game_word;
         while (attempts != 0 && !IsGuessedFully(display)){
            char guess = GetUserGuess();
            if (IsCorrect(guess, nw_word)){
                display = UpdateDisplay(display, nw_word, guess);
                System.out.println(display);
                nw_word = UpdateWord(nw_word, guess);
            }
            else {
                attempts = IsIncorrect(attempts);
            }
        }

         IsWinner(display, game_word);
    }

    public static char GetUserGuess() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char letter = ' ';
        while (loop) {
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine().strip().toLowerCase();
            if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                 letter = guess.charAt(0);
                 loop = false;
            }
        }
        return letter;
    }

    public static boolean IsCorrect(char guess, String word){
        if (word.contains(String.valueOf((guess)))){
            return true;
        }
        return false;
    }

    public static int IsIncorrect(int attempts){
        System.out.println("Guess is incorrect!");
        System.out.println("You have " + attempts + " left");

        return attempts - 1;
    }

    public static List<Character> UpdateDisplay(List<Character> display, String word, char guess){
        for (int i = 0; i < display.size(); i++){
            if (word.charAt(i) == guess){
                display.set(i, guess);
            }
        }
        return display;
    }

    public  static  String UpdateWord(String word, char guess){
        StringBuilder nw_word = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == guess){
                nw_word.setCharAt(i, '@');
            }
        }
        return  nw_word.toString();
    }

    public  static  boolean IsGuessedFully(List<Character> display) {
        for (char c : display) {
            if (c == '_') {
                return false;
            }

        }
        return true;
    }

    public  static void IsWinner(List<Character> display, String game_word) {
        boolean won = IsGuessedFully(display);
        if (won){
            System.out.println("You Won");
        }
        else {
            System.out.println("You lost, the word was "+ game_word);
        }
    }


}