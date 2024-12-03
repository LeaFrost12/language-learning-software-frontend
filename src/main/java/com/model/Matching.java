package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.narration.Narrator;

public class Matching implements Question {
    private HashMap<String, String> answers;
    private ArrayList<String> englishWords;
    private ArrayList<String> foreignWords;
    private ArrayList<Word> originalWords;

    public Matching(ArrayList<Word> words) {
        englishWords = new ArrayList<>();
        foreignWords = new ArrayList<>();
        answers = new HashMap<>();
        originalWords = new ArrayList<>();

        Collections.shuffle(words);

        for (int i = 3; i >= 0; i--) { // Populate answers, english, and foreign word lists/maps
            Word word = words.get(i);
            englishWords.add(word.getTranslatedWord());
            foreignWords.add(word.getForeignWord());
            answers.put(word.getTranslatedWord(), word.getForeignWord());
            originalWords.add(word);
        }

        Collections.shuffle(englishWords);
        Collections.shuffle(foreignWords);
    }

    public Matching(ArrayList<Word> words, int seed) {
        if (words.size() < 2) {
            System.out.println("Words input doesn't have enough words in it");
        } else {
            Random rand = new Random(seed);

            englishWords = new ArrayList<>();
            foreignWords = new ArrayList<>();
            answers = new HashMap<>();
            originalWords = new ArrayList<>();

            Collections.shuffle(words, rand);

            int otherWords = words.size() < 4 ? words.size() - 1 : 3;

            for (int i = otherWords; i >= 0; i--) {
                Word word = words.get(i);
                englishWords.add(word.getTranslatedWord());
                foreignWords.add(word.getForeignWord());
                answers.put(word.getTranslatedWord(), word.getForeignWord());
                originalWords.add(word);
            }

            Collections.shuffle(englishWords, rand);
            Collections.shuffle(foreignWords, rand);
        }
    }

    public boolean checkAnswer(HashMap<String, String> userAnswers) {
        for (String key : answers.keySet()) {
            if (!userAnswers.get(key).equals(answers.get(key))) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, String> getAnswers() {
        return answers;
    }

    public ArrayList<String> getEnglishWords() {
        return englishWords;
    }

    public ArrayList<String> getForeignWords() {
        return foreignWords;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String word : foreignWords) {
            returnString += "," + word;
        }
        for (String word : englishWords) {
            returnString += "," + word;
        }
        return returnString;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MATCHING;
    }

    @Override
    public boolean run(User user) {
        for (int i = 0; i < englishWords.size(); i++) {
            System.out.println(englishWords.get(i) + "\t\t\t" + foreignWords.get(i));
            Narrator.playSound(foreignWords.get(i));
        }

        for (int i = 0; i < englishWords.size(); i++) {
            boolean correct = answerPart(i, user);
            if (!correct) {
                System.out.println("\nYou got that match wrong! The right answer was " + answers.get(englishWords.get(i)) + "\n");
                return false;
            }
        }

        System.out.println("\nYou got these matches correct!!!!! You're amazing!\n");
        return true;
    }

    private boolean answerPart(int cycle, User user) {
        System.out.print(englishWords.get(cycle) + " -> ");
        Scanner scan = new Scanner(System.in);
        String userAnswer = scan.nextLine();

        if (!answers.get(englishWords.get(cycle)).equalsIgnoreCase(userAnswer)) {
            user.addProblemWord(originalWords.get(cycle));
            return false;
        }
        return true;
    }

    public boolean answerPart(int cycle, User user, String userAnswer) {
        System.out.print(englishWords.get(cycle) + " -> ");

        if (!answers.get(englishWords.get(cycle)).equalsIgnoreCase(userAnswer)) {
            user.addProblemWord(originalWords.get(cycle));
            return false;
        }
        return true;
    }

    @Override
    public String getQuestionText() {
        StringBuilder questionText = new StringBuilder("Match the following:\n");
        for (String englishWord : englishWords) {
            questionText.append(englishWord).append(" - ???\n");
        }
        return questionText.toString();
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        // This would need to parse the user's answer and compare it to `answers`
        return false; // Placeholder implementation
    }

    @Override
    public String getCorrectAnswer() {
        StringBuilder correctAnswer = new StringBuilder();
        for (String key : answers.keySet()) {
            correctAnswer.append(key).append(" - ").append(answers.get(key)).append("\n");
        }
        return correctAnswer.toString();
    }
}