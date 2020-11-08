import java.util.*;
import java.io.*;
public class Game
{
    public static String printArrayList(ArrayList<String> arr)
    {
        String s = "";
        for(int i = 0; i < arr.size(); i++)
        {
            if(i == arr.size() - 1)
                s += arr.get(i);
            else
                s += arr.get(i) + ", ";
        }

        return s;
    }

    public static void main(String args[]) throws Exception
    {
        Scanner scan = new Scanner(new File("Fast Food Places.txt"));
        ArrayList<Word> fastFoodPlaces = new ArrayList<>();
        while(scan.hasNextLine())
        {
            fastFoodPlaces.add(new Word(scan.nextLine()));
        }

        int lives = 8;
        int level = 1;
        System.out.printf("%30s", "Greasy Guessing Game" + "\n");
        System.out.printf("%35s", "__________________________" + "\n" + "\n");

        while(fastFoodPlaces.size() > 0)
        {
            Word food = fastFoodPlaces.get(0);
            String foodName = food.getName();

            int numberOfLettersRemaining = food.letterCount();
            ArrayList<String> incorrectGuesses = new ArrayList<String>();
            ArrayList<String> correctGuesses = new ArrayList<String>();

            System.out.println("Level " + level + ":");
            System.out.print("________" + "\n\n");

            System.out.println("This word contains " + food.letterCount() + " letters." + "\n");
            while(numberOfLettersRemaining >= 0)
            {
                if(lives == 0)
                {
                    break;
                }
                if(numberOfLettersRemaining == 0)
                {
                    for(int i = 0; i < foodName.length(); i++)
                    {
                        if(correctGuesses.contains(foodName.substring(i, i+1).toUpperCase()) || foodName.substring(i, i+1).equals(" ") || foodName.substring(i, i+1).equals("-") || foodName.substring(i, i+1).equals("'"))
                        {
                            System.out.printf("%3s", foodName.substring(i, i+1));
                        }
                        else
                        {
                            System.out.printf("%3s", "*");
                        }
                    }
                    System.out.println("\n" + "\n" + "Level " + level + " completed." + "\n\n");
                    level++;
                    fastFoodPlaces.remove(0);
                    break;
                }

                for(int i = 0; i < foodName.length(); i++)
                {
                    if(correctGuesses.contains(foodName.substring(i, i+1).toUpperCase()) || foodName.substring(i, i+1).equals(" ") || foodName.substring(i, i+1).equals("-") || foodName.substring(i, i+1).equals("'"))
                    {
                        System.out.printf("%3s", foodName.substring(i, i+1));
                    }
                    else
                    {
                        System.out.printf("%3s", "*");
                    }
                }
                System.out.println("\n" + "\n" + "Guess a letter: ");
                Scanner input = new Scanner(System.in);
                String guess = input.nextLine();

                if(guess.length() != 1)
                {
                    System.out.println("Your guess has an invalid number of characters. Type only one letter." + "\n");
                    continue;
                }
                if(correctGuesses.contains(guess.toUpperCase()) || correctGuesses.contains(guess.toUpperCase()))
                {
                    System.out.println("You have already guessed that letter. Try again." + "\n");
                    continue;
                }
                int result = food.containsLetter(food, guess);

                if(result == 0)
                {
                    lives--;
                    incorrectGuesses.add(guess.toUpperCase());
                    System.out.println("Your guess was incorrect");
                    System.out.println("Lives Remaining: " + lives);

                    System.out.println("Letters Remaining: " + numberOfLettersRemaining);
                    System.out.println("Incorrect letters: " + printArrayList(incorrectGuesses) + "\n");
                }
                if(result >= 1)
                {
                    numberOfLettersRemaining -= result;
                    correctGuesses.add(guess.toUpperCase());
                    System.out.println("Your guess was correct");
                    System.out.println("Lives Remaining: " + lives);

                    System.out.println("Letters Remaining: " + numberOfLettersRemaining);
                    System.out.println("Incorrect letters: " + printArrayList(incorrectGuesses) + "\n");
                }

            }
            if(lives == 0)
            {
                System.out.println("You ran out of lives." + "\n" + "The word was: " + foodName + "\n" + "GAME OVER");
                break;
            }
        }
        if(fastFoodPlaces.size() == 0)
        {
            System.out.println("Congratulations! You have completed the game!");
        }

    }
}