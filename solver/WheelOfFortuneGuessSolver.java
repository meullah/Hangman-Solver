package solver;

import java.util.*;


/**
 * Guessing strategy for Wheel of Fortune Hangman variant. (task D)
 * You'll need to complete the implementation of this.
 *
 * @author Jeffrey Chan, RMIT 2020
 */
public class WheelOfFortuneGuessSolver extends HangmanSolver
{
    private  Set<String> wordsCollection;
    private Random rand = new Random();
    private char[] usedAlphabets;
    private int[] wordLengths;
    private byte iterator;
    private char[] alphabets;

    /**
     * Constructor.
     *
     * @param dictionary Dictionary of words that the guessed words are drawn from.
     */
    public WheelOfFortuneGuessSolver(Set<String> dictionary) {
        // initialization
        wordsCollection = new HashSet<String>();
        wordsCollection = dictionary;
        usedAlphabets = new char[27];
        iterator = 0;
    } // end of WheelOfFortuneGuessSolver()


    @Override
    public void newGame(int[] _wordLengths, int maxTries) {
        Set<String> temp = new HashSet<String>();
        String letters="";

        // Extract words from dictionary having lengths equal to given words length

        for(byte i=0; i<_wordLengths.length;i++){
            for(String word:wordsCollection){
                if(word.length()==_wordLengths[i]){
                    temp.add(word);
                    letters+=word;
                }
            }
        }
        // save extracted words to our wordsCollection

        wordsCollection = temp;
        wordLengths = _wordLengths;

        // extract unique characters used in those words.
        Set<Character> in = new HashSet<Character>();
        for(char c : letters.toCharArray()){
            in.add(c);
        }
        letters="";
        for(char ch:in){
            letters+=ch;
        }
        alphabets = letters.toCharArray();
    } // end of setWordLength()


    @Override
    public char makeGuess() {
        int int_random = rand.nextInt(alphabets.length);
        char guessedAlphabet = alphabets[int_random];

        // don't use the same alphabet again
        if (alreadyGuessed(guessedAlphabet) ==true){
            do{
                int_random = rand.nextInt(alphabets.length);
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

} // end of class WheelOfFortuneGuessSolver
