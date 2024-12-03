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

        // Shuffle words once and then populate the lists
        Collections.shuffle(words);
        for (int i = 0; i < Math.min(4, words.size()); i++) {
            Word word = words.get(i);
            englishWords.add(word.getTranslatedWord());
            foreignWords.add(word.getForeignWord());
            answers.put(word.getTranslatedWord(), word.getForeignWord());
        }
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
        StringBuilder sb = new StringBuilder("Match the following pairs:\n");
        for (String english : answers.keySet()) {
            sb.append(english).append(" -> ???\n");
        }
        return sb.toString();
    }


    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MATCHING;
    }

    @Override
    public boolean run(User user) {
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Match the following:");
        for (int i = 0; i < englishWords.size(); i++) {
            System.out.println((i + 1) + ". " + englishWords.get(i) + " -> " + foreignWords.get(i));
        }
    
        System.out.println("\nEnter your matches in the format '1 -> 3', one per line:");
        HashMap<String, String> userAnswers = new HashMap<>();
        for (int i = 0; i < englishWords.size(); i++) {
            System.out.print("Match for " + englishWords.get(i) + ": ");
            String userInput = scan.nextLine();
            String[] parts = userInput.split("->");
            if (parts.length == 2) {
                String english = englishWords.get(Integer.parseInt(parts[0].trim()) - 1);
                String foreign = foreignWords.get(Integer.parseInt(parts[1].trim()) - 1);
                userAnswers.put(english, foreign);
            }
        }
    
        boolean correct = checkAnswer(userAnswers);
        if (correct) {
            System.out.println("All matches are correct!");
        } else {
            System.out.println("Some matches are incorrect.");
        }
        return correct;
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