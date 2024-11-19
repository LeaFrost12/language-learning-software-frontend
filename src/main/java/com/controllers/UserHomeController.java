package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.model.*;

public class UserHomeController implements Initializable {
    @FXML private Label lbl_title;
    private LanguageSystemFacade facade;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageSystemFacade.getInstance();
        user = facade.getCurrentUser();
        
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
        
    }   
    
}
