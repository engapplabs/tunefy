package com.abuarquemf.tunefy.musicmanager.controllers

import com.abuarquemf.tunefy.musicmanager.configuration.LoginHandler
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

internal class Controller {

    @FXML
    private lateinit var loginField: TextField

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var infoLabel: Label

    @FXML
    fun signInAction(event: Event) {
        val givenLogin = loginField.getText().toString()
        val givenPassword = passwordField.getText().toString()

        if(givenLogin.equals("") || givenPassword.equals("")) {
            infoLabel.text = "Fill all spaces."
        } else {
            if(LoginHandler.validateLogin(givenLogin, givenPassword)) {
                println("OK")
            } else {
                infoLabel.text = "Login or password incorrect."
            }
        }
    }
}
