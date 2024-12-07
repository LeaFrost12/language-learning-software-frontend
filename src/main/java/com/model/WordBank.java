package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.narration.Narrator;

/**
 * WordBank class is a type of question where the user will be given a sentence
 * with an empty space, and has to choose a word from the bank that correctly
 * fills the space.
 *
 * @author Cody Miller
 */
public class WordBank implements Question {

    private String answer;
    private String question;
    private ArrayList<Word> wordBank;

    /**
     * Constructor for the WordBank class, creates a random question based off
     * a list of testable words.
     *
     * @param words ArrayList of the lesson's quizzable words.
     */
    public WordBank(ArrayList<Word> words) {
        Random rand = new Random();

        if (words.isEmpty()) {
            this.wordBank = new ArrayList<>();
            this.wordBank.add(new Word("hola", "hello"));
            this.answer = "hola";
            this.question = "____, mi nombre es Lea.";
        } else {
            Collections.shuffle(words, rand);
            Word answerWord = words.get(0);
            this.answer = answerWord.getForeignWord();
            this.question = answerWord.getExampleSentence();
            this.wordBank = new ArrayList<>(words);
            this.wordBank.add(answerWord);
            Collections.shuffle(this.wordBank, rand);
        }
    }

    /**
     * Constructor for the WordBank class, creates a random question based off of a seed.
     *
     * @param words ArrayList of the lesson's quizzable words.
     * @param seed  Random seed.
     */
    public WordBank(ArrayList<Word> words, int seed) {
        Random rand = new Random(seed);

        if (words.isEmpty()) {
            this.wordBank = new ArrayList<>();
            this.wordBank.add(new Word("hola", "hello"));
            this.answer = "hola";
            this.question = "____, mi nombre es Lea.";
        } else {
            Collections.shuffle(words, rand);
            Word answerWord = words.get(0);
            this.answer = answerWord.getForeignWord();
            this.question = answerWord.getExampleSentence();
            this.wordBank = new ArrayList<>(words);
            this.wordBank.add(answerWord);
            Collections.shuffle(this.wordBank, rand);
        }
    }

    /**
     * Returns the text of the question, including the word bank choices.
     *
     * @return The question text.
     */
    @Override
    public String getQuestionText() {
        StringBuilder questionText = new StringBuilder(question);
        return questionText.toString();
    }

    /**
     * Checks if the user's answer is correct.
     *
     * @param userAnswer The user's answer.
     * @return true if the answer is correct, false otherwise.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }

    /**
     * Returns the correct answer for the question.
     *
     * @return The correct answer.
     */
    @Override
    public String getCorrectAnswer() {
        return answer;
    }

    /**
     * Runs the question in the terminal to ask the user for their answer.
     *
     * @return true if the user gets the question right, false otherwise.
     */
    @Override
    public boolean run(User user) {
        System.out.println(getQuestionText());
        Narrator.playSound(question);

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your answer:\n> ");
        String userAnswer = scan.nextLine();
        boolean correct = checkAnswer(userAnswer);

        if (correct) {
            System.out.println("\nYou're right!!! Nice job!!\n");
        } else {
            System.out.println("\nYou're wrong... the right answer was " + getCorrectAnswer() + "\n");
            addProblemWordToUser(user);
        }

        return correct;
    }

    /**
     * Runs the question without requiring terminal input, for testing purposes.
     *
     * @param user       The user answering the question.
     * @param userAnswer The user's answer for testing.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean run(User user, String userAnswer) {
        System.out.println(getQuestionText());
        boolean correct = checkAnswer(userAnswer);

        if (correct) {
            System.out.println("\nYou're right!!! Nice job!!\n");
        } else {
            System.out.println("\nYou're wrong... the right answer was " + getCorrectAnswer() + "\n");
            addProblemWordToUser(user);
        }

        return correct;
    }

    /**
     * Adds the correct word to the user's problem word list.
     *
     * @param user The user.
     */
    private void addProblemWordToUser(User user) {
        for (Word word : wordBank) {
            if (word.getForeignWord().equalsIgnoreCase(answer)) {
                user.addProblemWord(word);
                break;
            }
        }
    }

    /**
     * Returns the enumeration of the question type.
     *
     * @return QuestionType.WORD_BANK.
     */
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.WORD_BANK;
    }

    /**
     * Returns a string representation of the question and word bank.
     *
     * @return The question and word bank as a string.
     */
    @Override
    public String toString() {
        return getQuestionText();
    }
    @Override
    public List<String> getWordBankText() {
        List<String> wordBankTexts = new ArrayList<>();
        for (Word word : wordBank) {
            wordBankTexts.add(word.getForeignWord());
        }
        return wordBankTexts;
    }
}