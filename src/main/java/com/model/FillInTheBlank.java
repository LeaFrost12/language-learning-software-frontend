package com.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.narration.Narrator;

/**
 * FillInTheBlank class is a type of question where the user will be given a
 * sentence with an empty space, and has to fill in the blank with the correct
 * answer.
 *
 * @author Cody Miller
 */
public class FillInTheBlank implements Question {

    private String answer;
    private String question;
    private Word answerWord;

    /**
     * Constructor for the FillInTheBlank class, creates a random question based
     * off of a list of testable words.
     *
     * @param words ArrayList of the lesson's quizzable words.
     */
    public FillInTheBlank(ArrayList<Word> words) {
        if (words.isEmpty()) {
            System.out.println("Hint: hello");

            this.answerWord = new Word("hola", "hello");
            this.answer = "hola";
            this.question = "____, mi nombre es Lea.";
        } else {
            Random rand = new Random();
            this.answerWord = words.get(rand.nextInt(words.size()));

            System.out.println("Hint: " + answerWord.getTranslatedWord());

            this.answer = answerWord.getForeignWord();
            this.question = answerWord.getExampleSentence();
        }
    }

    /**
     * Constructor for the FillInTheBlank class, creates a random question based
     * on the seed.
     *
     * @param words ArrayList of the lesson's quizzable words.
     * @param seed  Random seed.
     */
    public FillInTheBlank(ArrayList<Word> words, int seed) {
        if (words.isEmpty()) {
            System.out.println("Hint: hello");

            this.answerWord = new Word("hola", "hello");
            this.answer = "hola";
            this.question = "____, mi nombre es Lea.";
        } else {
            Random rand = new Random(seed);
            this.answerWord = words.get(rand.nextInt(words.size()));

            if (this.answerWord != null) {
                System.out.println("Hint: " + answerWord.getTranslatedWord());

                this.answer = answerWord.getForeignWord();
                this.question = answerWord.getExampleSentence();
            }
        }
    }

    /**
     * Returns the type of the question.
     *
     * @return QuestionType.FILL_IN_THE_BLANK.
     */
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.FILL_IN_THE_BLANK;
    }

    /**
     * Returns the question text for display.
     *
     * @return The question text with a blank to fill.
     */
    @Override
    public String getQuestionText() {
        return question;
    }

    /**
     * Checks if the user's answer is correct.
     *
     * @param userAnswer The user's input answer.
     * @return true if the answer matches the correct answer, false otherwise.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        return this.answer.equalsIgnoreCase(userAnswer.trim());
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
     * @param user The user answering the question.
     * @return true if the user answers correctly, false otherwise.
     */
    @Override
    public boolean run(User user) {
        // Display and narrate question
        System.out.println(getQuestionText());
        Narrator.playSound(getQuestionText());

        System.out.print("Enter your answer:\n>");

        // Get user answer and check
        Scanner scan = new Scanner(System.in);
        String userAnswer = scan.nextLine();
        boolean correct = checkAnswer(userAnswer);

        // Tell user whether they got it right
        if (correct) {
            System.out.println("\nYou're right!!! Nice job!!\n");
        } else {
            System.out.println("\nYou're wrong... the right answer was " + getCorrectAnswer() + "\n");
            user.addProblemWord(answerWord);
        }

        return correct;
    }

    /**
     * Runs the question without asking for user input (used for testing purposes).
     *
     * @param user       The user answering the question.
     * @param userAnswer The answer provided for testing.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean run(User user, String userAnswer) {
        System.out.println(getQuestionText());

        boolean correct = checkAnswer(userAnswer);

        if (correct) {
            System.out.println("\nYou're right!!! Nice job!!\n");
        } else {
            System.out.println("\nYou're wrong... the right answer was " + getCorrectAnswer() + "\n");
            user.addProblemWord(answerWord);
        }

        return correct;
    }

    @Override
    public String toString() {
        return "Fill in the blank: " + getQuestionText();
    }
}