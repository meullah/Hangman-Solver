# Hanngman-Solver
Hangman is a popular children 2-player game, where one player selects a word, phrase or sentence and the other player guesses this by making a number of single letter guesses. 
Each time the guessing player makes an incorrect guess (either letter or the wholeword/phrase/sentence), a part is added to a diagram (usually a hangman). 
The game ends when either the guessing player guesses all the letters of the word correctly, guesses the word/phrase/sentence correctly, or the diagram is complete. 
In this assignment you will implement algorithms for the guessing player, that can solve hangman games and its variants.

## Background

In Hangman, the guessing player is given a word, phrase or sentence to guess. The length of the word, phrase and sentence are given, and the guessing player makes a number of single letter guesses to guess that unknown word, phrase and sentence. If a guess is correct, the guessed letter in the unknown word will be revealed. If the guess is incorrect, a piece is added to a drawing (typically a hangman), and if the hangman drawing is complete, the guessing player loses. If all the letters of the word etc are correctly guessed and revealed, then the guessing player wins. For more details about Hangman, please see
https://en.wikipedia.org/wiki/Hangman_(game). Example app to see Hangman in action: https://thewordsearch.com/hangman

### Hangman Strategies 

#### Random Guessing Strategy 
This is the most basic strategy, but a good way to start the consideration of designing a good Hangman guessing algorithm. In this strategy, the algorithm makes random guesses of letters that it hasn’t guessed before. Consider it as sampling without replacement type of guessing. 

#### Dictionary Aware Statistics Strategy 
This is a more advanced strategy. When we know the dictionary of words that Hangman is generated from, we can compute statistics per letter of who many words they appear in. We then guess according to the letter that appears in most words in the dictionary, as that, if words are uniformly sampled from the dictionary, are most likely to result in a correct letter guess. Subsequently, the set of possible words are narrowed down according to each correct guess. From the remaining possible words, we compute the requency statistics again and select the letter that is more frequent among the remaining possible words.

## Hangman Variants
### Two word Hangman
In this variant, instead of guessing one word, we guess two words, both from a known dictionary.
### Wheel of Fortune
In the Wheel of Fortune variant (yes, same as the popular TV show https://en.wikipedia.org/wiki/heel_of_Fortune_(Australian_game_show)), instead of guessing a word, we guess a phrase or sentence. The previous dictionary aware approach may not work in this case - we want you to think about why that might be so, and devise an approach that can solve this Wheel of Fortune variant faster than the previously mentioned strategies.
