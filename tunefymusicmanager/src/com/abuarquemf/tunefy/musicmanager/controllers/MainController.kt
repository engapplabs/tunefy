package com.abuarquemf.tunefy.musicmanager.controllers

import com.abuarquemf.tunefy.musicmanager.Main
import com.abuarquemf.tunefy.musicmanager.configuration.LoginHandler
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.stage.Stage

internal class MainController {

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
                try {
                    (event.source as Node).scene.window.hide()
                    val mainSource = FXMLLoader.load<Parent>(javaClass
                            .getResource("../layouts/manager_layout.fxml"))
                    val mainStage = Stage()
                    mainStage.icons.add(Image(javaClass.getResourceAsStream("../images/icon.png")))
                    mainStage.title = Main.APP_NAME
                    mainStage.scene = Scene(mainSource)
                    mainStage.isResizable = false
                    mainStage.show()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            } else {
                infoLabel.text = "Login or password incorrect."
            }
        }
    }
}
