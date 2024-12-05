package com.controllers;

import com.model.User;
import com.model.UserList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AccountDetailsController {

    @FXML
    private Label txt_first_name;

    @FXML
    private Label txt_last_name;

    @FXML
    private Label txt_username;

    @FXML
    private Label txt_phone_number;

    @FXML
    private Label txt_email;

    @FXML
    private ImageView whaleImage;

    public AccountDetailsController() {

        // Retrieve the current user from UserList
        UserList userList = UserList.getInstance();
        User currentUser = userList.getCurrentUser();

        // Setting each label field to user's info
        txt_first_name.setText(currentUser.getFirstName());
        txt_last_name.setText(currentUser.getLastName());
        txt_username.setText(currentUser.getUsername());
        txt_phone_number.setText(currentUser.getPhoneNumber());
        txt_email.setText(currentUser.getEmail());
    }

}
