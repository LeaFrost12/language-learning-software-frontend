package com.controllers;

import java.io.IOException;
import java.util.List;

import com.language.App;
import com.model.Lesson;
import com.model.Question;
import com.model.User;
import com.model.UserList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LessonController {
    @FXML
    private Label lessonTitleLabel;

    @FXML
    private Label questionLabel;

    @FXML
    private TextField answerField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private User currentUser;
    private Lesson currentLesson;

    public void initialize() {
        // Retrieve the current user and lesson
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();

        if (currentUser != null) {
            currentLesson = currentUser.getCurrentLesson();

            if (currentLesson != null) {
                lessonTitleLabel.setText(currentLesson.getLessonName());
                questions = currentLesson.getQuestions();

                if (questions != null && !questions.isEmpty()) {
                    displayQuestion();
                } else {
                    questionLabel.setText("No questions available in this lesson.");
                }
            } else {
                lessonTitleLabel.setText("No current lesson found.");
            }
        } else {
            lessonTitleLabel.setText("User not logged in.");
        }
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText(question.getQuestionText());
        answerField.clear();
        feedbackLabel.setText("");
        nextButton.setDisable(true);
    }

    @FXML
    private void onSubmitClicked() {
        String userAnswer = answerField.getText().trim();
        Question question = questions.get(currentQuestionIndex);

        if (userAnswer.isEmpty()) {
            feedbackLabel.setText("Please enter an answer.");
            return;
        }

        boolean isCorrect = question.checkAnswer(userAnswer);
        if (isCorrect) {
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + question.getCorrectAnswer());
        }

        nextButton.setDisable(false);
    }

    @FXML
    private void onNextQuestionClicked() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            feedbackLabel.setText("You have completed the lesson!");
            questionLabel.setText("");
            answerField.setDisable(true);
            nextButton.setDisable(true);
        }
    }
}

