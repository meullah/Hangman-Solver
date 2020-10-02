package solver;

import java.util.*;

/**
 * Guessing strategy for two word Hangman. (task C)
 * You'll need to complete the implementation of this.
 *
 * @author Jeffrey Chan, RMIT 2020
 */
public class TwoWordHangmanGuessSolver extends HangmanSolver
{

    private  Set<String> word1_collection;
    private  Set<String> word2_collection;
    private  Set<String> wordsCollection;
    private Random rand = new Random();
    private char[] usedAlphabets;
    private char[] word1;
    private char[] word2;
    private int[] word_lengths;
    private byte iterator;
    /**
     * Constructor.
     *
     * @param dictionary Dictionary of words that the guessed words are drawn from.
     */
    public TwoWordHangmanGuessSolver(Set<String> dictionary) {
        /***
         * initialization
         */
        word1_collection = new HashSet<String>();
        word2_collection = new HashSet<String>();
        wordsCollection = new HashSet<String>();
        wordsCollection = dictionary;
        usedAlphabets = new char[27];
        iterator = 0;
    } // end of TwoWordHangmanGuessSolver()


    @Override
    public void newGame(int[] wordLengths, int maxIncorrectGuesses) {
        word1 = new char[wordLengths[0]];
        word2 = new char[wordLengths[1]];

        Set<String> temp = new HashSet<String>();
        for(byte i=0; i<wordLengths.length;i++){
            for(String word:wordsCollection){
                if(word.length()==wordLengths[i]){
                    if(i==0){
                        word1_collection.add(word);
                    }
                    else {
                        word2_collection.add(word);
                    }
                }
            }
        }

    } // end of newGame()


    @Override
    public char makeGuess() {
        String selectedWord = getRandomWord();
        char guessedChar = selectRandomChar(selectedWord);

//        don't used already guessed character

        if(alreadyGuessed(guessedChar)){
            do{
                selectedWord = getRandomWord();
                guessedChar = selectRandomChar(selectedWord);
            }while (alreadyGuessed(guessedChar));
        }

        usedAlphabets[iterator]=guessedChar;
        iterator++;

        return guessedChar;
//        return 't';

    } // end of makeGuess()


    @Override
    public void guessFeedback(char c, Boolean bGuess, ArrayList< ArrayList<Integer> > lPositions)
    {
        System.out.println("character: "+c+" "+lPositions);
        if(bGuess){
            byte i = 0; // represents word
            for(ArrayList<Integer> list:lPositions){
                if(!(list==null)){
                    for(Integer index:list){
                        if(i==0){
                            word1[index]=c;
                        }
                        else {
                            word2[index]=c;
                        }
                    }
                }
                i++;
            }

            Set<String> temp = new HashSet<String>();
            temp.addAll(word1_collection);
            for(byte j=0;j<word1.length;j++){
                if(word1[j]!='\0'){
                    for (String word:word1_collection){
                        if(word.charAt(j)!=word1[j]){
                            temp.remove(word);
                        }
                    }
                }
            }
            word1_collection=temp;

            Set<String> temp2 = new HashSet<String>();

            temp2.addAll(word2_collection);
            for(byte j=0;j<word2.length;j++){
                if(word2[j]!='\0'){
                    for (String word:word2_collection){
                        if(word.charAt(j)!=word2[j]){
                            temp2.remove(word);
                        }
                    }
                }
            }

            word2_collection=temp2;
        }

    } // end of guessFeedback()

    private String getRandomWord(){
        int i=0;
        // for word 0
        if(rand.nextInt(2)==0){
            int randInt = rand.nextInt(word1_collection.size());
            for(String word : word1_collection)
            {
                if (i == randInt)
                    return word;
                i++;
            }
        }
        // for word 2
        else{
            int randInt = rand.nextInt(word2_collection.size());
            for(String word : word2_collection)
            {
                if (i == randInt)
                    return word;
                i++;
            }
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


} // end of class TwoWordHangmanGuessSolver
