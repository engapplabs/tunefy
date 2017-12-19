package com.abuarquemf.tunefy.desktopapp

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import javafx.stage.StageStyle

class Main : Application() {

    private var xOffset: Double = 0.0
    private var yOffset: Double  = 0.0

    override fun start(primaryStage: Stage) {
        primaryStage.title = APP_NAME
        primaryStage.isResizable = false
        primaryStage.initStyle(StageStyle.TRANSPARENT)

        primaryStage.icons.add(Image(javaClass.getResourceAsStream("images/icon.png")))

        val root = FXMLLoader.load<Parent>(javaClass.getResource("layouts/main_layout.fxml"))

        root.onMousePressed = object : javafx.event.EventHandler<MouseEvent> {
            override fun handle(event: MouseEvent) {
                xOffset = event.sceneX
                yOffset = event.sceneY
            }
        }

        //move around here
        root.onMouseDragged = javafx.event.EventHandler<MouseEvent> { event ->
            primaryStage.x = event.screenX - xOffset
            primaryStage.y = event.screenY - yOffset
        }

        val scene = Scene(root)
        scene.fill = javafx.scene.paint.Color.TRANSPARENT
        primaryStage.scene = scene
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

