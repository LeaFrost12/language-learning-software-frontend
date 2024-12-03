package com.model;

import java.util.Collections;
import java.util.List;

/**
 * Question interface for various types of questions in lessons.
 *
 * @author Cody Miller and madeleine McBride
 */
public interface Question {

    String toString();

    QuestionType getQuestionType();

    boolean run(User user);

    default String getQuestionText() {
        return "No question text available.";
    }

    default String getCorrectAnswer() {
        return "No correct answer available.";
    }

    default List<String> getChoices() {
        return Collections.emptyList(); // Empty list for non-choice-based questions
    }

    default List<String> getWordBankText() {
        return Collections.emptyList(); // Empty list for non-word-bank-based questions
    }

    default boolean checkAnswer(String userAnswer) {
        return false; // Default implementation to ensure compatibility
    }
}
