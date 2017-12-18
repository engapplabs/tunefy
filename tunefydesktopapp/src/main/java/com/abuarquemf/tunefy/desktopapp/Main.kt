package com.abuarquemf.tunefy.desktopapp

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = APP_NAME
        primaryStage.isResizable = false

        val root = FXMLLoader.load<Parent>(javaClass.getResource("layouts/sample.fxml"))
        primaryStage.scene = Scene(root, 300.0, 275.0)
        primaryStage.show()

        primaryStage.show()
    }

    companion object {

        val APP_NAME = "Tunefy"

        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}

