package com.abuarquemf.tunefy.musicmanager

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        try {
            primaryStage.icons.add(Image(Main.javaClass.getResourceAsStream("images/icon.png")))
            val root = FXMLLoader.load<Parent>(javaClass.getResource("layouts/main_layout.fxml"))
            primaryStage.scene = Scene(root)
            primaryStage.title = Main.APP_NAME
            primaryStage.isResizable = false
            primaryStage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {

        val APP_NAME = "Tunefy"

        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
