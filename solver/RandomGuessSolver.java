package solver;

import java.util.*;
import java.lang.System;

/**
 * Random guessing strategy for Hangman. (task A)
 * You'll need to complete the implementation of this.
 *
 * @author Jeffrey Chan, RMIT 2020
 */
public class RandomGuessSolver extends HangmanSolver
{
    private  char[] alphabets = new char[]{'\'','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] usedAlphabets = new char[26];
    private Random rand = new Random();
//    private Set<String> dict = new HashSet<String>();
    private byte iterator = 0;

    /**
     * Constructor.
     *
     * @param dictionary Dictionary of words that the guessed words are drawn from.
     */
    public RandomGuessSolver(Set<String> dictionary) {
//        dict = dictionary;
    } // end of RandomGuessSolver()


    @Override
    public void newGame(int[] wordLengths, int maxIncorrectGuesses) {
        // Implement me!
    } // end of newGame()


    @Override
    public char makeGuess() {
        // will generate random integers from 0 to 25
        int int_random = rand.nextInt(26);
        char guessedAlphabet = alphabets[int_random];

        // don't use the same alphabet again
        if (alreadyGuessed(guessedAlphabet) ==true){
            do{
                int_random = rand.nextInt(26);
                guessedAlphabet = alphabets[int_random];
            }while (alreadyGuessed(guessedAlphabet));
        }
        usedAlphabets[iterator]=guessedAlphabet;
        iterator++;

        return guessedAlphabet;

    } // end of makeGuess()


    @Override
    public void guessFeedback(char c, Boolean bGuess, ArrayList< ArrayList<Integer> > lPositions)
    {
//        System.out.println("guess feedback  " +c + "  position " + lPositions + "boolean guess "+ bGuess);
        // Implement me!
    } // end of guessFeedback()

    private boolean alreadyGuessed(char c){

        for(char ch: usedAlphabets){
            if (c == ch){
                return true;
            }
        }
        return false;
    }

} // end of class RandomGuessSolver
