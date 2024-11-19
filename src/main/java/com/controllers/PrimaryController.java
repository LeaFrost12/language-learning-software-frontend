package com.controllers;

import java.io.IOException;

import com.narration.Narrator;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narrator.playSound("Hola Mundo");
        //App.setRoot("secondary");
    }
}
