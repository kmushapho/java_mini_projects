import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> code = Code();
        System.out.println("Welcome to mastermind");
        PlayGame(code);

    }

    public static List<Integer> Code() {
//        create a list of game playable nums
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nums.add(i);
        }
        int length = nums.size();

//        create the four_digit code
        Random random = new Random();
        List<Integer> game_code = new ArrayList<>();
        while (game_code.size() != 4) {
            int idx = random.nextInt(length);
            int num = nums.get(idx);
            if (!game_code.contains(num)) {
                game_code.add(num);
            }
        }
        return game_code;
    }


    public static String UserGuess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine().strip();
            if (guess.length() == 4) {
                int count = 0;
                for (int i =0 ; i < 4; i++){
                    if (Character.isDigit(guess.charAt(i))){
                        count += 1;
                    }
                }
                if (count == 4){
                    return guess;
                }
            }
        }

    }


    public  static  int IsCorrect(String guess, List<Integer> code){
        StringBuilder guess_list = new StringBuilder(guess);
        int count = 0;
        for (int i = 0; i < guess_list.length(); i ++){
            if (Character.getNumericValue(guess_list.charAt(i) )== code.get(i)){
                count += 1;
            }
        }
        return count;
    }

    public static int IsCorrectish(String guess, List<Integer> code) {
        StringBuilder guess_list = new StringBuilder(guess);
        int count = 0;
        for (int i = 0; i < guess_list.length(); i++) {
            if (Character.getNumericValue(guess_list.charAt(i)) != code.get(i) && code.contains(Character.getNumericValue(guess_list.charAt(i)))) {
                count += 1;
            }
        }
        return  count;
    }

    public static void PlayGame(List<Integer> code){
        int attempts = 6;
        while ( attempts != 0){
            String guess = UserGuess();
            int guessedCorrect = IsCorrect(guess,code);
            int guessedCorrectish = IsCorrectish(guess, code);
            System.out.println("Correctly guess :" + guessedCorrect);
            System.out.println("Incorrectly guessed but correctish :" + guessedCorrectish);

            if (guessedCorrect == 4){
                IsWinner(true, code);
                break;
            }

            else {
                attempts -= 1;
                System.out.println("You have " + attempts + " left.");
            }
        }
        IsWinner(false, code);
    }

    public static void IsWinner(boolean guessedCorrect, List<Integer> code){
        if (guessedCorrect){
            System.out.println("You WOn!!!!!!!");
        }
        else {
            System.out.println("You lost the word was :" + code);
        }
    }
}
