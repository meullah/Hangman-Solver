package solver;

import java.util.*;
import java.lang.System;

/**
 * Dictionary aware guessing strategy for Hangman. (task B)
 * You'll need to complete the implementation of this.
 *
 * @author Jeffrey Chan, RMIT 2020
 */
public class DictAwareSolver extends HangmanSolver
{
    private  Set<String> wordsCollection;
    private Random rand = new Random();
    private char[] usedAlphabets;
    private byte iterator;


    /**
     * Constructor.
     *
     * @param dictionary Dictionary of words that the guessed words are drawn from.
     */
    public DictAwareSolver(Set<String> dictionary) {
        //initialization
        wordsCollection = new HashSet<String>();
        wordsCollection = dictionary;
        usedAlphabets = new char[26];
        iterator = 0;

    } // end of DictAwareSolver()


    @Override
    public void newGame(int[] wordLengths, int maxIncorrectGuesses)
    {
        /**
         *  extract words of given lengths
         */

        Set<String> temp = new HashSet<String>();
        for(byte i=0; i<wordLengths.length;i++){
            for(String word:wordsCollection){
                if(word.length()==wordLengths[i])
                    temp.add(word);
            }
        }
        wordsCollection = temp;
    } // end of newGame()


    @Override
    public char makeGuess() {
        /**
         * select a random word from extracted wordsCollection
         * also select a random char from it to guess,
         * if that character is already guessed
         * then guess the char again
         */





        int rand_index = rand.nextInt(wordsCollection.size());
        String selectedWord = randomWord(rand_index);
        char guessedChar = selectRandomChar(selectedWord);

//        don't used already guessed character

        if(alreadyGuessed(guessedChar)){
            do{
                rand_index = rand.nextInt(wordsCollection.size());
                selectedWord = randomWord(rand_index);
                guessedChar = selectRandomChar(selectedWord);
            }while (alreadyGuessed(guessedChar));
        }

        usedAlphabets[iterator]=guessedChar;
        iterator++;

        return guessedChar;
    } // end of makeGuess()


    @Override
    public void guessFeedback(char c, Boolean bGuess, ArrayList< ArrayList<Integer> > lPositions)
    {

        /**
         * using the feedback filter the words using
         * index and guessed character
         * this will further narrow our searching list (i.e wordCollection)
         * */
        if(bGuess){
            Set<String> temp = new HashSet<String>();
            for(ArrayList<Integer> positions: lPositions){
                if(!(positions==null)){
                    for(Integer index:positions) {
                        for(String word:wordsCollection){
                            if(word.indexOf(c) == index){
                                temp.add(word);
                            }
                        }
                    }
                }
            }
            wordsCollection = temp;
        }

    } // end of guessFeedback()

    private String randomWord(int randInt){
        int i=0;
        for(String word : wordsCollection)
        {
            if (i == randInt)
                return word;
            i++;
        }
        return null; // it will never return null
    }

    private char selectRandomChar(String s){
        int index = rand.nextInt(s.length());
        return s.charAt(index);
    }
    private boolean alreadyGuessed(char c){
        for(char ch: usedAlphabets){
            if (c == ch){
                return true;
            }
        }
        return false;
    }

} // end of class DictAwareSolver
