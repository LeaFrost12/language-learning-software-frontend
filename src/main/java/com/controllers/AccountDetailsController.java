package com.controllers;

import com.model.User;
import com.model.UserList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AccountDetailsController {

    @FXML
    private Label lbl_first_name;

    @FXML
    private Label lbl_last_name;

    @FXML
    private Label lbl_username;

    @FXML
    private Label lbl_phone_number;

    @FXML
    private Label lbl_email;

    @FXML
    private ImageView whaleImage;

    public AccountDetailsController() {

        // Retrieve the current user from UserList
        UserList userList = UserList.getInstance();
        User currentUser = userList.getCurrentUser();

        // Setting each label field to user's info
        lbl_first_name.setText(currentUser.getFirstName());
        lbl_last_name.setText(currentUser.getLastName());
        lbl_username.setText(currentUser.getUsername());
        lbl_phone_number.setText(currentUser.getPhoneNumber());
        lbl_email.setText(currentUser.getEmail());
    }

}
