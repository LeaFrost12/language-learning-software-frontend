package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.narration.Narrator;

/**
 * MultipleChoice class is a type of question where the user will be given a
 * question and four to five possible answers to choose from for that question.
 *
 * @author Cody Miller
 */
public class MultipleChoice implements Question {

    private Word word;
    private String answer;
    private String question;
    private ArrayList<String> choices;
    private int correctIndex;

    /**
     * Constructor for the MultipleChoice class, creates a random question based
     * off a list of testable words.
     *
     * @param words ArrayList of the lesson's quizzable words.
     */
    public MultipleChoice(ArrayList<Word> words) {
        this.choices = new ArrayList<>();

        if (words.isEmpty()) {
            return;
        }

        Collections.shuffle(words);

        this.word = words.get(0);
        this.answer = words.get(0).getForeignWord();
        this.question = words.get(0).getExampleSentence();
        this.choices.add(answer);

        for (int i = 1; i < Math.min(words.size(), 4); i++) {
            choices.add(words.get(i).getForeignWord());
        }

        Collections.shuffle(this.choices);
        correctIndex = choices.indexOf(answer);
    }

    /**
     * Constructor for the MultipleChoice class, creates a random question based
     * on the seed.
     *
     * @param words ArrayList of the lesson's quizzable words.
     * @param seed  Random seed.
     */
    public MultipleChoice(ArrayList<Word> words, int seed) {
        this.choices = new ArrayList<>();

        if (words.isEmpty()) {
            return;
        }

        Random rand = new Random(seed);
        Collections.shuffle(words, rand);

        this.word = words.get(0);
        this.answer = words.get(0).getForeignWord();
        this.question = words.get(0).getExampleSentence();
        this.choices.add(answer);

        for (int i = 1; i < Math.min(words.size(), 4); i++) {
            choices.add(words.get(i).getForeignWord());
        }

        Collections.shuffle(this.choices, rand);
        correctIndex = choices.indexOf(answer);
    }

    /**
     * Checks if the user's answer is correct.
     *
     * @param userAnswer The user's answer.
     * @return true if the answer is correct, false otherwise.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        if (answer.equalsIgnoreCase(userAnswer.trim())) {
            return true;
        }
        try {
            return correctIndex == Integer.parseInt(userAnswer) - 1;
        } catch (NumberFormatException e) {
            return false;
        }
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
     * Returns the question text.
     *
     * @return The question text.
     */
    @Override
    public String getQuestionText() {
        StringBuilder questionText = new StringBuilder(question).append("\nChoices:\n");
        for (int i = 0; i < choices.size(); i++) {
            questionText.append(i + 1).append(". ").append(choices.get(i)).append("\n");
        }
        return questionText.toString();
    }

    /**
     * Runs the question in the terminal to ask the user for their answer.
     *
     * @return true if they get the question right, false if they get the
     * question wrong.
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
            user.addProblemWord(word);
        }

        return correct;
    }

    /**
     * Runs the question without user input (for testing).
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
            user.addProblemWord(word);
        }

        return correct;
    }

    /**
     * Returns the enumeration of the question type.
     *
     * @return QuestionType.MULTIPLE_CHOICE.
     */
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MULTIPLE_CHOICE;
    }

    /**
     * Returns a string representation of the question and choices.
     *
     * @return The question and choices as a string.
     */
    @Override
    public String toString() {
        return getQuestionText();
    }

    @Override
    public List<String> getChoices() {
        return choices;
    }
}