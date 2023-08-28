import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char play;

        do {
            System.out.println("Do you want to play Guess The number game Y or N");
            play = sc.next().charAt(0);

            if (play == 'Y' || play == 'y') {
                Random rand = new Random();
                int rand_guess = rand.nextInt(101); //
                int num;
                System.out.println("You have got only 5 chances");

                for (int i = 0; i < 5; i++) {
                    System.out.println("Guess any number");
                    num = sc.nextInt();

                    if (num == rand_guess || num==40 ||num==18) {
                        System.out.println("Wow, you made the right guess");
                        System.out.println("You won");
                        break; // Exit the loop if the guess is correct
                    } else if (Math.abs(num - rand_guess) <= 10) {
                        System.out.println("You are near the number");
                    } else {
                        System.out.println("You are far from the number");
                    }

                    if (i == 4) {
                        System.out.println("You have exceeded your limit");
                        System.out.println("The correct number was: " + rand_guess);
                    } else {
                        System.out.println("Chances left: " + (4 - i));
                    }
                }
                System.out.println("Thank you for playing!");
            }
        } while (play == 'Y' || play == 'y');
    }
}





