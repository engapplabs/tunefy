package com.abuarquemf.tunefy.musicmanager.controllers

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

internal class Controller {

    @FXML
    private lateinit var loginField: TextField

    @FXML lateinit var passwordField: PasswordField

    @FXML
    fun signInAction(event: Event) {
        val givenLogin = loginField.getText().toString()
        val givenPassword = passwordField.getText().toString()

        if(givenLogin.equals("") || givenPassword.equals("")) {
            //TODO
        } else {
            //TODO
        }
    }
}
